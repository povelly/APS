package parser;
%%
%byaccj
%{
  private Parser yyparser;
  public Yylex(java.io.Reader r, Parser yyparser) {
  this(r);
  this.yyparser = yyparser;
  }
%}
nums = -?[0-9]+
ident = [a-z][a-zA-Z0-9]*
nls = \n | \r | \r\n
%%

/* reserved symbols */
"[" { return Parser.LBRA; }
"]" { return Parser.RBRA; }
"(" { return Parser.LPAR; }
")" { return Parser.RPAR; }

";" { return Parser.PV; }
":" { return Parser.DP; }
"," { return Parser.VG; }
"*" { return Parser.STAR; }
"->" { return Parser.ARROW; }

/* declarations */
"const" { return Parser.CONST; }
"fun" { return Parser.FUN; }
"rec" { return Parser.REC; }

/* statements */
"echo" { return Parser.ECHO; }

/* primitive types */
"bool" { return Parser.BOOLEAN; }
"int" { return Parser.INTEGER; }

/* booleans */
"true" { return Parser.TRUE; }
"false" { return Parser.FALSE; }

/* operators */
"add" { return Parser.PLUS; }
"sub" { return Parser.MINUS; }
"mul" { return Parser.TIMES; }
"div" { return Parser.DIV; }

"if" { return Parser.IF; }


/* newline */
//{nls} { return 0; }//{ return Parser.NL; }
{nls} { }
/* float */

{nums} { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
  return Parser.NUM; }

{ident} { yyparser.yylval = new ParserVal(yytext());
  return Parser.IDENT;
}
/* whitespace */
[ \t]+ { }
\b { System.err.println("Sorry, backspace doesn't work"); }
/* error fallback */
[^] { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }
