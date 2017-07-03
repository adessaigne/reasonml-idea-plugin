package com.reason.lang;

import com.intellij.psi.tree.IElementType;
import com.reason.psi.ReasonMLElementType;
import com.reason.psi.ReasonMLTokenType;

public interface ReasonMLTypes {

    IElementType EXTERNAL_EXPRESSION = new ReasonMLElementType("EXTERNAL_EXPRESSION");
    IElementType MODULE_EXPRESSION = new ReasonMLElementType("MODULE_EXPRESSION");
    IElementType OPEN_EXPRESSION = new ReasonMLElementType("OPEN_EXPRESSION");
    IElementType LET_EXPRESSION = new ReasonMLElementType("LET_EXPRESSION");
    IElementType TYPE_EXPRESSION = new ReasonMLElementType("TYPE_EXPRESSION");
    IElementType INCLUDE_EXPRESSION = new ReasonMLElementType("INCLUDE_EXPRESSION");

    IElementType LET_BINDING = new ReasonMLElementType("LET_BINDING");
    IElementType MODULE_NAME = new ReasonMLElementType("MODULE_NAME");
    IElementType MODULE_PATH = new ReasonMLElementType("MODULE_PATH");
    IElementType TYPE_CONSTR_NAME = new ReasonMLElementType("TYPE_CONSTR_NAME");
    IElementType SCOPED_EXPR = new ReasonMLElementType("SCOPED_EXPR");
    IElementType VALUE_NAME = new ReasonMLElementType("VALUE_NAME");

    IElementType ANDAND = new ReasonMLTokenType("ANDAND");
    IElementType ARROBASE = new ReasonMLTokenType("ARROBASE");
    IElementType ARROW = new ReasonMLTokenType("ARROW");
    IElementType AS = new ReasonMLTokenType("AS");
    IElementType AUTO_CLOSE_TAG = new ReasonMLTokenType("AUTO_CLOSE_TAG");
    IElementType BACKTICK = new ReasonMLTokenType("BACKTICK");
    IElementType CARRET = new ReasonMLTokenType("CARRET");
    IElementType CLOSE_TAG = new ReasonMLTokenType("CLOSE_TAG");
    IElementType COLON = new ReasonMLTokenType("COLON");
    IElementType COMMA = new ReasonMLTokenType("COMMA");
    IElementType COMMENT = new ReasonMLTokenType("COMMENT");
    IElementType DOT = new ReasonMLTokenType("DOT");
    IElementType ELSE = new ReasonMLTokenType("ELSE");
    IElementType EQ = new ReasonMLTokenType("EQ");
    IElementType EQEQ = new ReasonMLTokenType("EQEQ");
    IElementType EQEQEQ = new ReasonMLTokenType("EQEQEQ");
    IElementType EXCLAMATION_MARK = new ReasonMLTokenType("EXCLAMATION_MARK");
    IElementType EXTERNAL = new ReasonMLTokenType("EXTERNAL");
    IElementType FALSE = new ReasonMLTokenType("FALSE");
    IElementType FLOAT = new ReasonMLTokenType("FLOAT");
    IElementType FUN = new ReasonMLTokenType("FUN");
    IElementType FUN_BODY = new ReasonMLTokenType("FUN_BODY");
    IElementType GT = new ReasonMLTokenType("GT");
    IElementType IF = new ReasonMLTokenType("IF");
    IElementType INCLUDE = new ReasonMLTokenType("INCLUDE");
    IElementType INT = new ReasonMLTokenType("INT");
    IElementType LBRACE = new ReasonMLTokenType("LBRACE");
    IElementType LBRACKET = new ReasonMLTokenType("LBRACKET");
    IElementType LET = new ReasonMLTokenType("LET");
    IElementType LIDENT = new ReasonMLTokenType("LIDENT");
    IElementType LIST = new ReasonMLTokenType("LIST");
    IElementType LPAREN = new ReasonMLTokenType("LPAREN");
    IElementType LT = new ReasonMLTokenType("LT");
    IElementType MINUS = new ReasonMLTokenType("MINUS");
    IElementType MINUSDOT = new ReasonMLTokenType("MINUSDOT");
    IElementType MODULE = new ReasonMLTokenType("MODULE");
    //IElementType MUL = new ReasonMLTokenType("MUL");
    //IElementType MULDOT = new ReasonMLTokenType("MULDOT");
    IElementType MUTABLE = new ReasonMLTokenType("MUTABLE");
    IElementType NONE = new ReasonMLTokenType("NONE");
    IElementType OPEN = new ReasonMLTokenType("OPEN");
    IElementType OPTION = new ReasonMLTokenType("OPTION");
    IElementType PIPE = new ReasonMLTokenType("PIPE");
    IElementType PIPE_FORWARD = new ReasonMLTokenType("PIPE_FORWARD");
    IElementType PLUS = new ReasonMLTokenType("PLUS");
    IElementType PLUSDOT = new ReasonMLTokenType("PLUSDOT");
    IElementType QUESTION_MARK = new ReasonMLTokenType("QUESTION_MARK");
    IElementType QUOTE = new ReasonMLTokenType("QUOTE");
    IElementType RBRACE = new ReasonMLTokenType("RBRACE");
    IElementType RBRACKET = new ReasonMLTokenType("RBRACKET");
    IElementType REC = new ReasonMLTokenType("REC");
    IElementType RPAREN = new ReasonMLTokenType("RPAREN");
    IElementType SEMI = new ReasonMLTokenType("SEMI");
    IElementType SHARP = new ReasonMLTokenType("SHARP");
    IElementType SHORTCUT = new ReasonMLTokenType("SHORTCUT");
    IElementType SLASH = new ReasonMLTokenType("SLASH");
    IElementType SLASHDOT = new ReasonMLTokenType("SLASHDOT");
    IElementType SOME = new ReasonMLTokenType("SOME");
    IElementType STAR = new ReasonMLTokenType("STAR");
    IElementType STARDOT = new ReasonMLTokenType("STARDOT");
    IElementType STRING = new ReasonMLTokenType("STRING");
    IElementType SWITCH = new ReasonMLTokenType("SWITCH");
    IElementType TRUE = new ReasonMLTokenType("TRUE");
    IElementType TYPE = new ReasonMLTokenType("TYPE");
    IElementType UIDENT = new ReasonMLTokenType("UIDENT");
    IElementType UNDERSCORE = new ReasonMLTokenType("UNDERSCORE");
    IElementType UNIT = new ReasonMLTokenType("UNIT");

}
