package com.reason.build.bs.insight;

import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.reason.Streams;
import com.reason.build.bs.ModuleConfiguration;
import com.reason.hints.InsightManager;
import com.reason.ide.ORNotification;
import com.reason.ide.hints.InferredTypesImplementation;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class BsQueryTypesService implements ProjectComponent {

    private static final Logger LOG = Logger.getInstance("ReasonML.types");
    private final Project m_project;

    public BsQueryTypesService(Project project) {
        m_project = project;
    }

    public static BsQueryTypesService getInstance(Project project) {
        return project.getComponent(BsQueryTypesService.class);
    }

    public void types(@NotNull VirtualFile sourceFile, @NotNull String cmiPath, @NotNull InsightManager.ProcessTerminated runAfter) {
        InferredTypesImplementation result;

        String basePath = ModuleConfiguration.getBasePath(m_project, sourceFile);
        String bscPath = ModuleConfiguration.getBscPath(m_project, sourceFile);
        if (bscPath == null) {
            return;
        }

        ProcessBuilder m_bscProcessBuilder = new ProcessBuilder(bscPath, cmiPath);
        m_bscProcessBuilder.directory(new File(basePath));

        Process bsc = null;
        try {
            bsc = m_bscProcessBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(bsc.getInputStream()));
            BufferedReader errReader = new BufferedReader(new InputStreamReader(bsc.getErrorStream()));

            Streams.waitUntilReady(reader, errReader);
            StringBuilder msgBuffer = new StringBuilder();
            if (errReader.ready()) {
                errReader.lines().forEach(line -> msgBuffer.append(line).append(System.lineSeparator()));
                Notifications.Bus.notify(new ORNotification("Code lens", msgBuffer.toString(), NotificationType.ERROR));
            } else {
                reader.lines().forEach(line -> {
                    //System.out.println("»" + line + "«");
                    if (line.startsWith("type") || line.startsWith("val") || line.startsWith("module") || line.startsWith("external")) {
                        msgBuffer.append('\n');
                    }
                    msgBuffer.append(line);
                });

                String newText = msgBuffer.toString();
                //System.out.println("---\n" + newText + "\n---");

                result = new InferredTypesImplementation();

                String[] types = newText.split("\n");
                for (String type : types) {
                    result.addTypes(type);
                }

                runAfter.run(result);
            }
        } catch (Exception e) {
            LOG.error("An error occurred when reading types", e);
        } finally {
            if (bsc != null) {
                bsc.destroy();
            }
        }
    }
}
