%{
  import java.io.*;
  import java.util.ArrayList;
  import java.util.List;
  import aps0.ast.*;
  import aps0.interfaces.*;
%}

%token NL /* newline */
%token <ival> NUM /* a number */
%token <sval> IDENT /* an identifier */
%token <ival> INTEGER
%token <bool> BOOLEAN /* boolean */
%token PLUS MINUS TIMES DIV /* operators */

%token LBRA RBRA /* bracket */
%token LPAR RPAR /* parenthesis */
%token PV, DP, VG, STAR, ARROW /* ; : , * -> */
%token CONST, FUN, REC
%token ECHO /* f */
%token TRUE, FALSE, NOT, AND, OR
%token EQ, LT

%token IF

%type <obj> line
%type <obj> expr
%type <obj> exprs

%type <obj> prog

%type <obj> cmds

%type <obj> stat

%type <obj> type
%type <obj> types
%type <obj> arg
%type <obj> args
%type <obj> dec

%start line
%%

/*
line:prog { prog=(IASTnode)$1; }
;
*/
line : prog { e = (IASTnode)$1; }
;

prog: LBRA cmds RBRA { $$ = new ASTprog((ASTcommands)$2); }
;

cmds: stat { $$ = new ASTcommands((IASTnode)$1, null); }
| dec PV cmds { $$ = new ASTcommands((IASTnode)$1, (ASTcommands)$3); }
| stat PV cmds { $$ = new ASTcommands((IASTnode)$1, (ASTcommands)$3); }
;

stat: ECHO expr { $$ = new ASTecho((IASTexpression)$2); }
| ECHO type { $$ = new PRINT((IASTtype)$2); } //a suppr
| ECHO types { $$ = new PRINT((ASTtypes)$2); }
| ECHO args { $$ = new PRINT_LIST((ArrayList<ASTarg>)$2); }
| ECHO dec { $$ = new PRINT((IASTdec)$2); }
;

arg: IDENT DP type { $$ = new ASTarg(new ASTid($1), new ASTtypes((IASTtype)$3)); }
;

args:
arg { ArrayList<ASTarg> r = new ArrayList<ASTarg>();
r.add((ASTarg)$1);
$$ = r; }
| arg VG args { ((ArrayList<ASTarg>)$3).add((ASTarg)$1); java.util.Collections.reverse((ArrayList<ASTarg>)$3); $$ = $3; }
;

type: BOOLEAN { $$ = PrimitiveType.BOOLEAN; }
| INTEGER { $$ = PrimitiveType.INTEGER; }
| LPAR types ARROW type RPAR { $$ = new ASTfunctionType((ASTtypes)$2, (IASTtype)$4); }
;

types: type { $$ = new ASTtypes((IASTtype)$1); }
| type STAR types { $$ = new ASTtypes((IASTtype)$1, (ASTtypes)$3); }
;

dec: CONST IDENT type expr { $$ = new ASTconst(new ASTid($2), new ASTtypes((IASTtype)$3), (IASTexpression)$4); }
| FUN IDENT type LBRA args RBRA expr { $$ = new ASTfun(new ASTid($2), new ASTtypes((IASTtype)$3), (ArrayList<ASTarg>)$5, (IASTexpression)$7); }
| FUN REC IDENT type LBRA args RBRA expr {$$ = new ASTfunRec(new ASTid($3), new ASTtypes((IASTtype)$4), (ArrayList<ASTarg>)$6, (IASTexpression) $8 ); }
;

// TODO prolog : finit par un .
// générer : typeExpr(_, true, bool)
// _ pour prendre n'importe quel contexte

expr:
NUM { $$ = new ASTnum($1); }
| IDENT { $$ = new ASTid($1); }
| TRUE { $$ = new ASTboolean(true); }
| FALSE { $$ = new ASTboolean(false); }
| LPAR IF expr expr expr RPAR { $$ = new ASTif((IASTexpression)$3, (IASTexpression)$4, (IASTexpression)$5); }
| LPAR PLUS expr expr RPAR { $$ = new ASTbinaryOperation(Operator.ADD, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR MINUS expr expr RPAR { $$ = new ASTbinaryOperation(Operator.SUB, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR TIMES expr expr RPAR { $$ = new ASTbinaryOperation(Operator.MUL, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR DIV expr expr RPAR { $$ = new ASTbinaryOperation(Operator.DIV, (IASTexpression)$3, (IASTexpression)$4); }
| LBRA args RBRA expr { $$ = new ASTfunctionnalAbstraction((ArrayList<ASTarg>) $2, (IASTexpression) $4);}
| LPAR expr exprs RPAR = { $$ = new ASTapplication((IASTexpression)$2, (ArrayList<IASTexpression>)$3); }
;
exprs:
expr { ArrayList<IASTexpression> r = new ArrayList<IASTexpression>();
r.add((IASTexpression)$1);
$$ = r; }
| expr exprs { ((ArrayList<IASTexpression>)$2).add((IASTexpression)$1); $$ = $2; }
;
%%

	public IASTnode e;
	private Yylex lexer;

	private int yylex() {
		int yyl_return = -1;
		try {
			yylval = new ParserVal(0);
			yyl_return = lexer.yylex();
		} catch (IOException e) {
			System.err.println("IO error :" + e);
		}
		return yyl_return;
	}

	public void yyerror(String error) {
		System.err.println("Error: " + error);
	}

	public Parser(Reader r) {
	lexer = new Yylex(r, this);
	}
