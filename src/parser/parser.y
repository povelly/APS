%{
  import java.io.*;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Arrays;
  import interfaces.*;
  import aps0.*;
  import aps1.*;
%}

%token NL /* newline */
%token <ival> NUM /* a number */
%token <sval> IDENT /* an identifier */
%token <ival> INTEGER
%token <bool> BOOLEAN /* boolean */

%token LBRA RBRA /* bracket */
%token LPAR RPAR /* parenthesis */
%token PV, DP, VG, STAR, ARROW /* ; : , * -> */
%token CONST, FUN, REC
%token ECHO /* f */
%token TRUE, FALSE, NOT, AND, OR
%token EQ, LT, PLUS MINUS TIMES DIV

%token VAR, PROC, SET, IFBLOCK, WHILE, CALL

%token IF

%type <obj> line
%type <obj> prog
%type <obj> cmds
%type <obj> stat
%type <obj> arg
%type <obj> args
%type <obj> type
%type <obj> types
%type <obj> block
%type <obj> dec
%type <obj> expr
%type <obj> exprs

%start line
%%

line : prog { e = (IASTnode)$1; }
;

prog: LBRA cmds RBRA { $$ = new ASTprog((List<IASTcommand>)$2); }
;

/*
cmds: stat { List<IASTcommand> r = new ArrayList<IASTcommand>();
r.add((IASTcommand)$1);
$$ = r; }
| dec PV cmds { ((List<IASTcommand>)$3).add((IASTcommand)$1); $$ = $3; }
| stat PV cmds { ((List<IASTcommand>)$3).add((IASTcommand)$1); $$ = $3; }
; */

cmds: stat { List<IASTcommand> r = new ArrayList<IASTcommand>();
r.add((IASTcommand)$1);
$$ = r; }
| dec PV cmds { List<IASTcommand> r = new ArrayList<IASTcommand>(Arrays.asList((IASTcommand)$1));
r.addAll((List<IASTcommand>)$3);
$$ = r; }
| stat PV cmds { List<IASTcommand> r = new ArrayList<IASTcommand>(Arrays.asList((IASTcommand)$1));
r.addAll((List<IASTcommand>)$3);
$$ = r; }
;

stat: ECHO expr { $$ = new ASTecho((IASTexpression)$2); }
| SET IDENT expr { $$ = new ASTset(new ASTident($2), (IASTexpression)$3); }
| IFBLOCK expr block block { $$ = new ASTifBlock((IASTexpression)$2, (ASTblock)$3, (ASTblock)$4); }
| WHILE expr block { $$ = new ASTwhile((IASTexpression)$2, (ASTblock)$3); }
| CALL IDENT exprs = { $$ = new ASTcall(new ASTident($2), (List<IASTexpression>)$3); }
;

arg: IDENT DP type { $$ = new ASTarg(new ASTident($1), new ASTtypes((IASTtype)$3)); }
;

args:
arg { ArrayList<ASTarg> r = new ArrayList<ASTarg>();
r.add((ASTarg)$1);
$$ = r; }
| arg VG args { ((ArrayList<ASTarg>)$3).add((ASTarg)$1); java.util.Collections.reverse((ArrayList<ASTarg>)$3); $$ = $3; }
;

type: BOOLEAN { $$ = ASTprimitiveType.BOOLEAN; }
| INTEGER { $$ = ASTprimitiveType.INTEGER; }
| LPAR types ARROW type RPAR { $$ = new ASTfunctionType((ASTtypes)$2, (IASTtype)$4); }
;

types: type { $$ = new ASTtypes((IASTtype)$1); }
| type STAR types { $$ = new ASTtypes((IASTtype)$1, (ASTtypes)$3); }
;

block: LBRA cmds RBRA { $$ = new ASTblock((List<IASTcommand>)$2); }
;

dec: CONST IDENT type expr { $$ = new ASTconst(new ASTident($2), new ASTtypes((IASTtype)$3), (IASTexpression)$4); }
| FUN IDENT type LBRA args RBRA expr { $$ = new ASTfun(new ASTident($2), new ASTtypes((IASTtype)$3), (ArrayList<ASTarg>)$5, (IASTexpression)$7); }
| FUN REC IDENT type LBRA args RBRA expr {$$ = new ASTfunRec(new ASTident($3), new ASTtypes((IASTtype)$4), (ArrayList<ASTarg>)$6, (IASTexpression) $8 ); }
| VAR IDENT type { $$ = new ASTvar(new ASTident($2), new ASTtypes((IASTtype)$3)); }
| PROC IDENT LBRA args RBRA block { $$ = new ASTproc(new ASTident($2), (ArrayList<ASTarg>) $4, (ASTblock) $6); }
| PROC REC IDENT LBRA args RBRA block { $$ = new ASTproc(new ASTident($3), (ArrayList<ASTarg>) $5, (ASTblock) $7); }
;

expr:
NUM { $$ = new ASTnum($1); }
| IDENT { $$ = new ASTident($1); }
| TRUE { $$ = new ASTboolean(true); }
| FALSE { $$ = new ASTboolean(false); }
| LPAR IF expr expr expr RPAR { $$ = new ASTif((IASTexpression)$3, (IASTexpression)$4, (IASTexpression)$5); }
| LPAR PLUS expr expr RPAR { $$ = new ASToperation(Operator.ADD, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR MINUS expr expr RPAR { $$ = new ASToperation(Operator.SUB, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR TIMES expr expr RPAR { $$ = new ASToperation(Operator.MUL, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR DIV expr expr RPAR { $$ = new ASToperation(Operator.DIV, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR AND expr expr RPAR { $$ = new ASToperation(Operator.AND, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR OR expr expr RPAR { $$ = new ASToperation(Operator.OR, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR NOT expr RPAR { $$ = new ASToperation(Operator.NOT, (IASTexpression)$3); }
| LPAR EQ expr expr RPAR { $$ = new ASToperation(Operator.EQ, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR LT expr expr RPAR { $$ = new ASToperation(Operator.LT, (IASTexpression)$3, (IASTexpression)$4); }
| LBRA args RBRA expr { $$ = new ASTlambda((ArrayList<ASTarg>) $2, (IASTexpression) $4);}
| LPAR expr exprs RPAR = { $$ = new ASTclosure((IASTexpression)$2, (ArrayList<IASTexpression>)$3); }
;

/*
exprs:
expr { List<IASTexpression> r = new ArrayList<IASTexpression>();
r.add((IASTexpression)$1);
$$ = r; }
| expr exprs { ((List<IASTexpression>)$2).add((IASTexpression)$1); $$ = $2; }
;
*/

exprs:
expr { List<IASTexpression> r = new ArrayList<IASTexpression>();
r.add((IASTexpression)$1);
$$ = r; }
| expr exprs { List<IASTexpression> r = new ArrayList<IASTexpression>(Arrays.asList((IASTexpression)$1));
r.addAll((List<IASTexpression>)$2);
$$ = r; }
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
