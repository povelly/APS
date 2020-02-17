%{
  import java.io.*;
  import java.util.ArrayList;
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
%token PV, DP, VR, ST, AR /* ; : , * -> */
%token CONST, FUN, REC
%token ECHO /* f */
%token TRUE, FALSE, NOT, AND, OR
%token EQ, LT

%type <obj> line
%type <obj> expr
%type <obj> exprs

%type <obj> prog

%type <obj> cmds

%type <obj> stat

%type <obj> type
%type <obj> types
%type <obj> dec

%start line
%%

/*
line:prog { prog=(IASTexpression)$1; }
;
*/
line : prog { e = (IASTexpression)$1; }


prog: LBRA cmds RBRA { $$ = new ASTprog((ASTcommands)$2); }
;

cmds: stat { $$ = new ASTcommands((IASTexpression)$1, null); }
| stat PV cmds { $$ = new ASTcommands((IASTexpression)$1, (ASTcommands)$3); };

stat: ECHO expr { $$ = new ASTecho((IASTexpression)$2); }
| ECHO type { $$ = new TEST((IASTtype)$2); } //a suppr
| ECHO types { $$ = new TEST((IASTtype)$2); }
;


type: BOOLEAN { $$ = PrimitiveType.BOOLEAN; }
| INTEGER { $$ = PrimitiveType.INTEGER; }
| LPAR types AR type RPAR { $$ = new ASTfunctionType((ASTtypes)$2, (IASTtype)$4); }
;

types: type { }
| type ST types { $$ = new ASTtypes((IASTtype)$1, (ASTtypes)$3); }
;

dec: CONST IDENT type expr {}
;

expr:
NUM { $$ = new AstNum($1); }
| IDENT { $$ = new AstId($1); }
| TRUE { $$ = new ASTboolean(true); }
| FALSE { $$ = new ASTboolean(false); }
| LPAR PLUS expr expr RPAR { $$ = new ASTbinaryOperation(Operator.ADD, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR MINUS expr expr RPAR { $$ = new ASTbinaryOperation(Operator.SUB, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR TIMES expr expr RPAR { $$ = new ASTbinaryOperation(Operator.MUL, (IASTexpression)$3, (IASTexpression)$4); }
| LPAR DIV expr expr RPAR { $$ = new ASTbinaryOperation(Operator.DIV, (IASTexpression)$3, (IASTexpression)$4); }
;
exprs:
expr { ArrayList<IASTexpression> r = new ArrayList<IASTexpression>();
r.add((IASTexpression)$1);
$$ = r; }
| expr exprs { ((ArrayList<IASTexpression>)$2).add((IASTexpression)$1); $$ = $2; }
;
%%

	public IASTexpression e;
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
