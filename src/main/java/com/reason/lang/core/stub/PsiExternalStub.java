package com.reason.lang.core.stub;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import com.reason.lang.core.psi.PsiLet;
import org.jetbrains.annotations.NotNull;

public class PsiExternalStub extends NamedStubBase<PsiLet> {
    private final boolean m_isFunction;

    public PsiExternalStub(StubElement parent, @NotNull IStubElementType elementType, String name, boolean isFunction) {
        super(parent, elementType, name);
        m_isFunction = isFunction;
    }

    public PsiExternalStub(StubElement parent, @NotNull IStubElementType elementType, StringRef name, boolean isFunction) {
        super(parent, elementType, name);
        m_isFunction = isFunction;
    }

    public boolean isFunction() {
        return m_isFunction;
    }
}