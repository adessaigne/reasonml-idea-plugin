package com.reason.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.reason.lang.core.type.ORTypes;

import static com.intellij.psi.TokenType.*;
%%

%{
    public ReasonMLLexer(ORTypes types) {
        this.types = types;
    }

    private ORTypes types;
    private int tokenStartIndex;
    private CharSequence quotedStringId;
    private int commentDepth;
    private boolean inCommentString = false;

    //Store the start index of a token
    private void tokenStart() {
        tokenStartIndex = zzStartRead;
    }

    //Set the start index of the token to the stored index
    private void tokenEnd() {
        zzStartRead = tokenStartIndex;
    }
%}

%public
%class ReasonMLLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

EOL=\n|\r|\r\n
WHITE_SPACE_CHAR=[\ \t\f]|{EOL}
WHITE_SPACE={WHITE_SPACE_CHAR}+

NEWLINE=("\r"* "\n")
LOWERCASE=[a-z_]
UPPERCASE=[A-Z]
IDENTCHAR=[A-Za-z_0-9']

DECIMAL=[0-9]
DECIMAL_SEP=[0-9_]
HEXA=[0-9A-Fa-f]
HEXA_SEP=[0-9A-Fa-f_]
OCTAL=[0-7]
OCTAL_SEP=[0-7_]

DECIMAL_LITERAL={DECIMAL} {DECIMAL_SEP}*
HEXA_LITERAL="0" [xX] {HEXA} {HEXA_SEP}*
OCT_LITERAL="0" [oO] {OCTAL} {OCTAL_SEP}*
BIN_LITERAL="0" [bB] [0-1] [0-1_]*
INT_LITERAL= {DECIMAL_LITERAL} | {HEXA_LITERAL} | {OCT_LITERAL} | {BIN_LITERAL}
FLOAT_LITERAL={DECIMAL} {DECIMAL_SEP}* ("." {DECIMAL_SEP}* )? ([eE] [+-]? {DECIMAL} {DECIMAL_SEP}* )?
HEXA_FLOAT_LITERAL="0" [xX] {HEXA} {HEXA_SEP}* ("." {HEXA_SEP}* )? ([pP] [+-]? {DECIMAL} {DECIMAL_SEP}* )?
LITERAL_MODIFIER=[G-Zg-z]

ESCAPE_BACKSLASH="\\\\"
ESCAPE_SINGLE_QUOTE="\\'"
ESCAPE_LF="\\n"
ESCAPE_TAB="\\t"
ESCAPE_BACKSPACE="\\b"
ESCAPE_CR="\\r"
ESCAPE_QUOTE="\\\""
ESCAPE_DECIMAL="\\" {DECIMAL} {DECIMAL} {DECIMAL}
ESCAPE_HEXA="\\x" {HEXA} {HEXA}
ESCAPE_OCTAL="\\o" [0-3] {OCTAL} {OCTAL}
ESCAPE_CHAR= {ESCAPE_BACKSLASH} | {ESCAPE_SINGLE_QUOTE} | {ESCAPE_LF} | {ESCAPE_TAB} | {ESCAPE_BACKSPACE } | { ESCAPE_CR } | { ESCAPE_QUOTE } | {ESCAPE_DECIMAL} | {ESCAPE_HEXA} | {ESCAPE_OCTAL}

%state WAITING_VALUE
%state INITIAL
%state IN_STRING
%state IN_RML_COMMENT
%state IN_OCL_COMMENT

%%

<YYINITIAL>  {
      [^]   { yybegin(INITIAL); yypushback(1); }
}

<INITIAL> {
    {WHITE_SPACE} { return WHITE_SPACE; }

    "and"         { return types.AND; }
    "as"          { return types.AS; }
    "assert"      { return types.ASSERT; }
    "begin"       { return types.BEGIN; }
    "class"       { return types.CLASS; }
    "constraint"  { return types.CONSTRAINT; }
    "do"          { return types.DO; }
    "done"        { return types.DONE; }
    "downto"      { return types.DOWNTO; }
    "else"        { return types.ELSE; }
    "end"         { return types.END; }
    "exception"   { return types.EXCEPTION; }
    "external"    { return types.EXTERNAL; }
    "for"         { return types.FOR; }
    "fun"         { return types.FUN; }
    "function"    { return types.FUNCTION; }
    "functor"     { return types.FUNCTOR; }
    "if"          { return types.IF; }
    "in"          { return types.IN; }
    "include"     { return types.INCLUDE; }
    "inherit"     { return types.INHERIT; }
    "initializer" { return types.INITIALIZER; }
    "lazy"        { return types.LAZY; }
    "let"         { return types.LET; }
    "module"      { return types.MODULE;}
    "mutable"     { return types.MUTABLE; }
    "new"         { return types.NEW; }
    "nonrec"      { return types.NONREC; }
    "object"      { return types.OBJECT; }
    "of"          { return types.OF; }
    "open"        { return types.OPEN; }
    "or"          { return types.OR; }
    "pub"         { return types.PUB; }
    "pri"         { return types.PRI; }
    "rec"         { return types.REC; }
    "sig"         { return types.SIG; }
    "struct"      { return types.STRUCT; }
    "switch"      { return types.SWITCH; }
    "then"        { return types.THEN; }
    "to"          { return types.TO; }
    "try"         { return types.TRY; }
    "type"        { return types.TYPE; }
    "val"         { return types.VAL; }
    "virtual"     { return types.VIRTUAL; }
    "when"        { return types.WHEN; }
    "while"       { return types.WHILE; }
    "with"        { return types.WITH; }

    "mod"         { return types.MOD; }
    "land"        { return types.LAND; }
    "lor"         { return types.LOR; }
    "lxor"        { return types.LXOR; }
    "lsl"         { return types.LSL; }
    "lsr"         { return types.LSR; }
    "asr"         { return types.ASR; }

    "unit"        { return types.UNIT; }
    "ref"         { return types.REF; }
    "raise"       { return types.RAISE; }
    "method"      { return types.METHOD; }
    "private"     { return types.PRIVATE; }
    "match"       { return types.MATCH; }

    "option"    { return types.OPTION; }
    "None"      { return types.NONE; }
    "Some"      { return types.SOME; }

    "bool"      { return types.BOOL; }
    "char"      { return types.CHAR; }
    "string"    { return types.STRING; }
    "int"       { return types.INT; }
    "float"     { return types.FLOAT; }
    "list"      { return types.LIST; }
    "array"     { return types.ARRAY; }

    "false"     { return types.BOOL_VALUE; }
    "true"      { return types.BOOL_VALUE; }

    "_"   { return types.UNDERSCORE; }

    "'" ( {ESCAPE_CHAR} | . ) "'"    { return types.CHAR_VALUE; }
    {LOWERCASE}{IDENTCHAR}*          { return types.LIDENT; }
    {UPPERCASE}{IDENTCHAR}*          { return types.UIDENT; }
    {INT_LITERAL}{LITERAL_MODIFIER}? { return types.INT_VALUE; }
    ({FLOAT_LITERAL} | {HEXA_FLOAT_LITERAL}){LITERAL_MODIFIER}? { return types.FLOAT_VALUE; }
    "'"{LOWERCASE}{IDENTCHAR}*       { return types.TYPE_ARGUMENT; }
    "`"{UPPERCASE}{IDENTCHAR}*       { return types.POLY_VARIANT; }

    "\"" { yybegin(IN_STRING); tokenStart(); }
    "/*" { yybegin(IN_RML_COMMENT); commentDepth = 1; tokenStart(); }
    "(*" { yybegin(IN_OCL_COMMENT); commentDepth = 1; tokenStart(); }

    "&&"  { return types.ANDAND; }
    "##"  { return types.SHARPSHARP; }
    "::"  { return types.SHORTCUT; }
    "=>"  { return types.ARROW; }
    "->"  { return types.RIGHT_ARROW; }
    "<-"  { return types.LEFT_ARROW; }
    "|>"  { return types.PIPE_FORWARD; }
    "|."  { return types.PIPE_FIRST; }
    "</"  { return types.TAG_LT_SLASH; }
    "/>"  { return types.TAG_AUTO_CLOSE; }
    "[|"  { return types.LARRAY; }
    "|]"  { return types.RARRAY; }
    ">]"  { return types.GT_BRACKET; }
    ">}"  { return types.GT_BRACE; }
    "{<"  { return types.BRACE_LT; }
    "[<"  { return types.BRACKET_LT; }
    "[>"  { return types.BRACKET_GT; }
    "{|"  { return types.ML_STRING_OPEN; /*bs MultiLine*/ }
    "|}"  { return types.ML_STRING_CLOSE; /*bs MultiLine*/ }
    "{j|"  { return types.JS_STRING_OPEN; /*js interpolation*/ }
    "|j}"  { return types.JS_STRING_CLOSE; /*js interpolation*/ }

    "===" { return types.EQEQEQ; }
    "=="  { return types.EQEQ; }
    "="   { return types.EQ; }
    "!==" { return types.NOT_EQEQ; }
    "!="  { return types.NOT_EQ; }
    ":="  { return types.COLON_EQ; }
    ":>"  { return types.COLON_GT; }
    "<>"  { return types.DIFF; }
    "<="  { return types.LT_OR_EQUAL; }
    ">="  { return types.GT_OR_EQUAL; }
    ";;"  { return types.SEMISEMI; }

    ","   { return types.COMMA; }
    ":"   { return types.COLON; }
    ";"   { return types.SEMI; }
    "'"   { return types.QUOTE; }
    "..." { return types.DOTDOTDOT; }
    ".."  { return types.DOTDOT; }
    "."   { return types.DOT; }
    "|"   { return types.PIPE; }
    "("   { return types.LPAREN; }
    ")"   { return types.RPAREN; }
    "{"   { return types.LBRACE; }
    "}"   { return types.RBRACE; }
    "["   { return types.LBRACKET; }
    "]"   { return types.RBRACKET; }
    "@"   { return types.ARROBASE; }
    "#"   { return types.SHARP; }
    "?"   { return types.QUESTION_MARK; }
    "!"   { return types.EXCLAMATION_MARK; }
    "$"   { return types.DOLLAR; }
    "`"   { return types.BACKTICK; }
    "~"   { return types.TILDE; }
    "&"   { return types.AMPERSAND; }

    "<"  { return types.LT; }
    ">"  { return types.GT; }

    "\^"  { return types.CARRET; }
    "+."  { return types.PLUSDOT; }
    "-."  { return types.MINUSDOT; }
    "/."  { return types.SLASHDOT; }
    "*."  { return types.STARDOT; }
    "+"   { return types.PLUS; }
    "-"   { return types.MINUS; }
    "/"   { return types.SLASH; }
    "*"   { return types.STAR; }
    "%"   { return types.PERCENT; }
}

<IN_STRING> {
    "\"" { yybegin(INITIAL); tokenEnd(); return types.STRING_VALUE; }
    "\\" { NEWLINE } ([ \t] *) { }
    "\\" [\\\'\"ntbr ] { }
    "\\" [0-9] [0-9] [0-9] { }
    "\\" "o" [0-3] [0-7] [0-7] { }
    "\\" "x" [0-9a-fA-F] [0-9a-fA-F] { }
    "\\" . { }
    { NEWLINE } { }
    . { }
    <<EOF>> { yybegin(INITIAL); tokenEnd(); return types.STRING_VALUE; }
}

<IN_RML_COMMENT> {
    "/*" { if (!inCommentString) commentDepth += 1; }
    "*/" { if (!inCommentString) { commentDepth -= 1; if(commentDepth == 0) { yybegin(INITIAL); tokenEnd(); return types.COMMENT; } } }
    "\"" { inCommentString = !inCommentString; }
    . | {NEWLINE} { }
    <<EOF>> { yybegin(INITIAL); tokenEnd(); return types.COMMENT; }
}

<IN_OCL_COMMENT> {
    "(*" { if (!inCommentString) commentDepth += 1; }
    "*)" { if (!inCommentString) { commentDepth -= 1; if(commentDepth == 0) { yybegin(INITIAL); tokenEnd(); return types.COMMENT; } } }
    "\"" { inCommentString = !inCommentString; }
     . | {NEWLINE} { }
    <<EOF>> { yybegin(INITIAL); tokenEnd(); return types.COMMENT; }
}

[^] { return BAD_CHARACTER; }
