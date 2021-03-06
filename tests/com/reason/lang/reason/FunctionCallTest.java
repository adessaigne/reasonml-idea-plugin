package com.reason.lang.reason;

import com.intellij.psi.util.PsiTreeUtil;
import com.reason.lang.BaseParsingTestCase;
import com.reason.lang.core.psi.PsiFunctionCallParams;
import com.reason.lang.core.psi.PsiLet;
import com.reason.lang.core.psi.PsiParameter;

import java.util.Collection;

@SuppressWarnings("ConstantConditions")
public class FunctionCallTest extends BaseParsingTestCase {
    public FunctionCallTest() {
        super("", "re", new RmlParserDefinition());
    }

    public void testCall() {
        PsiLet e = first(letExpressions(parseCode("let _ = string_of_int(1)")));

        PsiFunctionCallParams callParams = PsiTreeUtil.findChildOfType(e.getBinding(), PsiFunctionCallParams.class);
        Collection<PsiParameter> parameters = callParams.getParameterList();
        assertEquals(1, parameters.size());
    }

    public void testCall2() {
        PsiLet e = first(letExpressions(parseCode("let _ = Belt.Option.map(self.state.timerId^, Js.Global.clearInterval)")));

        PsiFunctionCallParams callParams = PsiTreeUtil.findChildOfType(e.getBinding(), PsiFunctionCallParams.class);
        Collection<PsiParameter> parameters = callParams.getParameterList();
        assertEquals(2, parameters.size());
    }

    public void testCall3() {
        PsiLet e = first(letExpressions(parseCode("let _ = subscriber->Topic.unsubscribe()")));

        PsiFunctionCallParams callParams = PsiTreeUtil.findChildOfType(e.getBinding(), PsiFunctionCallParams.class);
        assertEmpty(callParams.getParameterList());
    }

    public void testIssue120() {
        PsiLet e = first(letExpressions(parseCode("let _ = f(x == U.I, 1)")));

        PsiFunctionCallParams params = PsiTreeUtil.findChildOfType(e, PsiFunctionCallParams.class);
        assertSize(2, params.getParameterList());
    }
}
