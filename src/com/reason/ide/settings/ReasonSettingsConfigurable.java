package com.reason.ide.settings;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ReasonSettingsConfigurable implements SearchableConfigurable, Configurable.NoScroll {

    private final ReasonSettings m_settings;

    private JPanel f_rootPanel;
    private TextFieldWithBrowseButton f_bsLocation;
    private JTextField f_columnWidth;
    private JCheckBox f_reformatOnSave;
    private TextFieldWithBrowseButton f_workingDir;

    public ReasonSettingsConfigurable(ReasonSettings settings) {
        m_settings = settings;
    }

    @NotNull
    @Override
    public String getId() {
        return getHelpTopic();
    }

    @NotNull
    @Nls
    @Override
    public String getDisplayName() {
        return "Reason";
    }

    @NotNull
    @Override
    public String getHelpTopic() {
        return "settings.reason";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        f_bsLocation.addBrowseFolderListener("Choose bs-platform directory: ", null, m_settings.getProject(),
                FileChooserDescriptorFactory.createSingleFolderDescriptor());

        f_workingDir.addBrowseFolderListener("Choose working directory: ", null, m_settings.getProject(),
                FileChooserDescriptorFactory.createSingleFolderDescriptor());

        return f_rootPanel;
    }

    @Override
    public void apply() {
        m_settings.location = f_bsLocation.getText().trim();
        m_settings.workingDir = f_workingDir.getText().trim();
        m_settings.refmtOnSave = f_reformatOnSave.isSelected();
        m_settings.refmtWidth = f_columnWidth.getText().trim();
    }

    @Override
    public boolean isModified() {
        boolean sameLocation = f_bsLocation.getText().equals(m_settings.location);
        boolean sameWorkingDir = f_workingDir.getText().equals(m_settings.workingDir);
        boolean sameRefmtOnSave = f_reformatOnSave.isSelected() == m_settings.refmtOnSave;
        boolean sameColWidth = f_columnWidth.getText().equals(m_settings.refmtWidth);
        return !(sameLocation && sameWorkingDir && sameRefmtOnSave && sameColWidth);
    }

    @Override
    public void reset() {
        f_bsLocation.setText(m_settings.location);
        f_workingDir.setText(m_settings.workingDir);
        f_columnWidth.setText(m_settings.refmtWidth);
        f_reformatOnSave.setSelected(m_settings.refmtOnSave);
    }

}
