/***************************/
/* FILE NAME: LEX_FILE.lex */
/***************************/

/*************/
/* USER CODE */
/*************/

import java_cup.runtime.*;

/******************************/
/* DOLLAR DOLLAR - DON'T TOUCH! */
/******************************/

%%

/************************************/
/* OPTIONS AND DECLARATIONS SECTION */
/************************************/

/*****************************************************/
/* Lexer is the name of the class JFlex will create. */
/* The code will be written to the file Lexer.java.  */
/*****************************************************/
%class Lexer
%state CMT
%state LCOM

/********************************************************************/
/* The current line number can be accessed with the variable yyline */
/* and the current column number with the variable yycolumn.        */
/********************************************************************/
%line
%column

/*******************************************************************************/
/* Note that this has to be the EXACT same name of the class the CUP generates */
/*******************************************************************************/
%cupsym TokenNames

/******************************************************************/
/* CUP compatibility mode interfaces with a CUP generated parser. */
/******************************************************************/
%cup

/****************/
/* DECLARATIONS */
/****************/
/*****************************************************************************/
/* Code between %{ and %}, both of which must be at the beginning of a line, */
/* will be copied verbatim (letter to letter) into the Lexer class code.     */
/* Here you declare member variables and functions that are used inside the  */
/* scanner actions.                                                          */
/*****************************************************************************/
%{
	/*********************************************************************************/
	/* Create a new java_cup.runtime.Symbol with information about the current token */
	/*********************************************************************************/
	private Symbol symbol(int type)               {return new Symbol(type, yyline, yycolumn);}
	private Symbol symbol(int type, Object value) {return new Symbol(type, yyline, yycolumn, value);}

	/*******************************************/
	/* Enable line number extraction from main */
	/*******************************************/
	public int getLine() { return yyline + 1; }

	/**********************************************/
	/* Enable token position extraction from main */
	/**********************************************/
	public int getTokenStartPosition() { return yycolumn + 1; }
%}

/***********************/
/* MACRO DECLARATIONS */
/***********************/
LETTER          = [A-Za-z]
DIGIT           = [0-9]
LineTerminator	= \r|\n|\r\n

// Identifiers and digits
ID              = {LETTER}({LETTER}|{DIGIT})*

// Integers
INTEGER         = 0|[1-9]{DIGIT}*

// Strings
STRING          = \"{LETTER}*\"

// White spaces
WhiteSpace      = {LineTerminator} | [ \t\f]

// Comments:
ALLOWED_NO_NL_IN_CMT    = [A-Za-z0-9 \t\f()\[\]{}\?\!\+\-\*\/\.\,;]
DISALLOWED_NO_NL_IN_CMT = [^\r\nA-Za-z0-9 \t\f()\[\]{}\?\!\+\-\*\/\.\,;]
ALLOWED_NO_NL_NO_STAR   = [A-Za-z0-9 \t\f()\[\]{}\?\!+/\.,;\-]



/******************************/
/* DOLLAR DOLLAR - DON'T TOUCH! */
/******************************/

%%

/************************************************************/
/* LEXER matches regular expressions to actions (Java code) */
/************************************************************/

/**************************************************************/
/* YYINITIAL is the state at which the lexer begins scanning. */
/* So these regular expressions will only be matched if the   */
/* scanner is in the start state YYINITIAL.                   */
/**************************************************************/

<YYINITIAL> {

/*--------------------------------------------Keywords--------------------------------------------*/
"array"             { return symbol(TokenNames.ARRAY,       "ARRAY"); }
"class"             { return symbol(TokenNames.CLASS,       "CLASS"); }
"return"            { return symbol(TokenNames.RETURN,      "RETURN"); }
"while"             { return symbol(TokenNames.WHILE,       "WHILE"); }
"if"                { return symbol(TokenNames.IF,          "IF"); }
"else"              { return symbol(TokenNames.ELSE,        "ELSE"); }
"new"               { return symbol(TokenNames.NEW,         "NEW"); }
"extends"           { return symbol(TokenNames.EXTENDS,     "EXTENDS"); }
"nil"               { return symbol(TokenNames.NIL,         "NIL"); }
"int"               { return symbol(TokenNames.TYPE_INT,    "TYPE_INT"); }
"void"              { return symbol(TokenNames.TYPE_VOID,   "TYPE_VOID"); }
"string"            { return symbol(TokenNames.TYPE_STRING, "TYPE_STRING"); }

/*--------------------------------------------Identifiers--------------------------------------------*/
{ID}			    { return symbol(TokenNames.ID,          "ID("+yytext()+")");}

/*--------------------------------------------Integers--------------------------------------------*/
"0"{DIGIT}+         { return symbol(TokenNames.ERROR,       "ERROR"); }
{INTEGER}           {
                      long num = Long.parseLong(yytext());
                      if (num < 0 || num > 32767) { return symbol(TokenNames.ERROR,       "ERROR"); }
                      return symbol(TokenNames.NUMBER, Integer.valueOf(yytext()));
                    }

/*--------------------------------------------Strings--------------------------------------------*/
{STRING}            {return symbol(TokenNames.STRING, "STRING(" + yytext() + ")");}
\"{LETTER}*         { return symbol(TokenNames.ERROR,       "ERROR"); }

/*--------------------------------------------Comments--------------------------------------------*/
"//"                { yybegin(LCOM); }
"/*"                { yybegin(CMT); }

/*--------------------------------------------Operators--------------------------------------------*/
"+"					{ return symbol(TokenNames.PLUS,        "PLUS");}
"-"					{ return symbol(TokenNames.MINUS,       "MINUS");}
"*"				    { return symbol(TokenNames.TIMES,       "TIMES");}
"/"					{ return symbol(TokenNames.DIVIDE,      "DIVIDE");}
"("					{ return symbol(TokenNames.LPAREN,      "LPAREN");}
")"					{ return symbol(TokenNames.RPAREN,      "RPAREN");}
"["					{ return symbol(TokenNames.LBRACK,      "LBRACK");}
"]"					{ return symbol(TokenNames.RBRACK,      "RBRACK");}
"{"					{ return symbol(TokenNames.LBRACE,      "LBRACE");}
"}"					{ return symbol(TokenNames.RBRACE,      "RBRACE");}
","					{ return symbol(TokenNames.COMMA,       "COMMA");}
"."					{ return symbol(TokenNames.DOT,         "DOT");}
";"					{ return symbol(TokenNames.SEMICOLON,   "SEMICOLON");}
":="                { return symbol(TokenNames.ASSIGN,      "ASSIGN"); }
"="                 { return symbol(TokenNames.EQ,          "EQ"); }
"<"                 { return symbol(TokenNames.LT,          "LT"); }
">"                 { return symbol(TokenNames.GT,          "GT"); }
{WhiteSpace}		{ }
<<EOF>>				{ return symbol(TokenNames.EOF);}
.                   { return symbol(TokenNames.ERROR,       "ERROR");}
}
<LCOM>{DISALLOWED_NO_NL_IN_CMT}+   { return symbol(TokenNames.ERROR, "ERROR"); }
<LCOM>{ALLOWED_NO_NL_IN_CMT}+      { }
<LCOM>\r|\n                        { yybegin(YYINITIAL); }
<CMT>"*/"                          { yybegin(YYINITIAL); }
<CMT>[^A-Za-z0-9 \t\r\n\f\(\)\[\]\{\}\?\!\+\-\*\/\.\,;] { return symbol(TokenNames.ERROR, "ERROR"); }
<CMT>{ALLOWED_NO_NL_NO_STAR}+      { }
<CMT>"*"                           { }
<CMT>\r|\n                         { }
<CMT><<EOF>>                       { return symbol(TokenNames.ERROR, "ERROR"); }
