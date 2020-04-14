//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "parser.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.Arrays;
  import interfaces.*;
  import aps0.*;
  import aps1.*;
//#line 25 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NL=257;
public final static short NUM=258;
public final static short IDENT=259;
public final static short INTEGER=260;
public final static short BOOLEAN=261;
public final static short LBRA=262;
public final static short RBRA=263;
public final static short LPAR=264;
public final static short RPAR=265;
public final static short PV=266;
public final static short DP=267;
public final static short VG=268;
public final static short STAR=269;
public final static short ARROW=270;
public final static short CONST=271;
public final static short FUN=272;
public final static short REC=273;
public final static short ECHO=274;
public final static short TRUE=275;
public final static short FALSE=276;
public final static short NOT=277;
public final static short AND=278;
public final static short OR=279;
public final static short EQ=280;
public final static short LT=281;
public final static short PLUS=282;
public final static short MINUS=283;
public final static short TIMES=284;
public final static short DIV=285;
public final static short VAR=286;
public final static short PROC=287;
public final static short SET=288;
public final static short IFBLOCK=289;
public final static short WHILE=290;
public final static short CALL=291;
public final static short IF=292;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    3,    3,    3,    3,    3,
    4,    5,    5,    6,    6,    6,    7,    7,    8,    9,
    9,    9,    9,    9,    9,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   11,   11,
};
final static short yylen[] = {                            2,
    1,    3,    1,    3,    3,    2,    3,    4,    3,    3,
    3,    1,    3,    1,    1,    5,    1,    3,    3,    4,
    7,    8,    3,    6,    7,    1,    1,    1,    1,    6,
    5,    5,    5,    5,    5,    5,    4,    5,    5,    4,
    4,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   26,   27,
    0,    0,   28,   29,    6,    0,    0,    0,    0,    0,
    0,    0,    2,    0,    0,   15,   14,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   23,    0,    0,    7,    0,
    0,    9,    0,   10,    5,    4,    0,    0,   20,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    8,   43,
    0,    0,    0,    0,   11,   13,   40,   37,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   41,    0,    0,
   19,   18,    0,    0,    0,   35,   36,   38,   39,   31,
   32,   33,   34,    0,   24,    0,   16,   21,    0,   30,
   25,   22,
};
final static short yydgoto[] = {                          2,
    3,   13,   14,   43,   44,   67,   68,   61,   15,   63,
   64,
};
final static short yysindex[] = {                      -250,
 -184,    0,    0, -241, -253, -249, -240, -248, -235, -249,
 -249, -230, -233, -231, -228, -211, -211, -223,    0,    0,
 -207, -165,    0,    0,    0, -211, -203, -204, -249, -202,
 -202, -249,    0, -184, -184,    0,    0, -211, -249, -201,
 -211, -205, -199, -198, -249, -249, -249, -249, -249, -249,
 -249, -249, -249, -249, -249,    0, -207, -196,    0, -184,
 -202,    0, -249,    0,    0,    0, -189, -206,    0, -207,
 -195, -211, -207, -249, -183, -249, -249, -249, -249, -249,
 -249, -249, -249, -249, -181, -182, -207, -178,    0,    0,
 -211, -211, -177, -207,    0,    0,    0,    0, -176, -174,
 -173, -170, -167, -156, -144, -143, -249,    0, -202, -163,
    0,    0, -141, -249, -138,    0,    0,    0,    0,    0,
    0,    0,    0, -139,    0, -202,    0,    0, -249,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -135,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -134,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -209,    0,    0,    0, -140,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -32,    0,    0,  -36,   -9,   40,  -30,    0,   -6,
  -41,
};
final static int YYTABLESIZE=131;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         25,
   62,   65,   66,   30,   31,   17,   39,   40,   19,   20,
   27,    1,   21,   85,   22,   55,   56,   16,   26,   18,
   86,   90,   59,   29,   28,   23,   24,   88,   32,   33,
   89,   71,   69,   93,   34,   41,   96,   35,   75,   76,
   77,   78,   79,   80,   81,   82,   83,   84,   36,   37,
  110,   42,   38,   42,   58,   42,   42,  115,   57,   60,
   70,   72,   95,   92,   74,   87,   94,   97,   73,   99,
  100,  101,  102,  103,  104,  105,  106,  107,  125,   91,
  109,   98,  113,  108,  111,  114,    4,    5,  116,    6,
  117,  118,   19,   20,  119,  131,   21,  120,   22,  126,
  124,    7,    8,    9,   10,   11,   12,  128,  121,   23,
   24,   45,   46,   47,   48,   49,   50,   51,   52,   53,
  122,  123,  132,  127,  129,  130,   54,    3,   12,   17,
  112,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
   31,   34,   35,   10,   11,  259,   16,   17,  258,  259,
  259,  262,  262,   55,  264,   22,   26,  259,  259,  273,
   57,   63,   29,  259,  273,  275,  276,   60,  259,  263,
   61,   41,   39,   70,  266,  259,   73,  266,   45,   46,
   47,   48,   49,   50,   51,   52,   53,   54,  260,  261,
   87,  259,  264,  263,  259,  265,  266,   94,  262,  262,
  262,  267,   72,  270,  263,  262,  262,   74,  268,   76,
   77,   78,   79,   80,   81,   82,   83,   84,  109,  269,
  263,  265,   92,  265,  263,  263,  271,  272,  265,  274,
  265,  265,  258,  259,  265,  126,  262,  265,  264,  263,
  107,  286,  287,  288,  289,  290,  291,  114,  265,  275,
  276,  277,  278,  279,  280,  281,  282,  283,  284,  285,
  265,  265,  129,  265,  263,  265,  292,  263,  263,  270,
   91,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=292;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"NL","NUM","IDENT","INTEGER","BOOLEAN","LBRA","RBRA","LPAR",
"RPAR","PV","DP","VG","STAR","ARROW","CONST","FUN","REC","ECHO","TRUE","FALSE",
"NOT","AND","OR","EQ","LT","PLUS","MINUS","TIMES","DIV","VAR","PROC","SET",
"IFBLOCK","WHILE","CALL","IF",
};
final static String yyrule[] = {
"$accept : line",
"line : prog",
"prog : LBRA cmds RBRA",
"cmds : stat",
"cmds : dec PV cmds",
"cmds : stat PV cmds",
"stat : ECHO expr",
"stat : SET IDENT expr",
"stat : IFBLOCK expr block block",
"stat : WHILE expr block",
"stat : CALL IDENT exprs",
"arg : IDENT DP type",
"args : arg",
"args : arg VG args",
"type : BOOLEAN",
"type : INTEGER",
"type : LPAR types ARROW type RPAR",
"types : type",
"types : type STAR types",
"block : LBRA cmds RBRA",
"dec : CONST IDENT type expr",
"dec : FUN IDENT type LBRA args RBRA expr",
"dec : FUN REC IDENT type LBRA args RBRA expr",
"dec : VAR IDENT type",
"dec : PROC IDENT LBRA args RBRA block",
"dec : PROC REC IDENT LBRA args RBRA block",
"expr : NUM",
"expr : IDENT",
"expr : TRUE",
"expr : FALSE",
"expr : LPAR IF expr expr expr RPAR",
"expr : LPAR PLUS expr expr RPAR",
"expr : LPAR MINUS expr expr RPAR",
"expr : LPAR TIMES expr expr RPAR",
"expr : LPAR DIV expr expr RPAR",
"expr : LPAR AND expr expr RPAR",
"expr : LPAR OR expr expr RPAR",
"expr : LPAR NOT expr RPAR",
"expr : LPAR EQ expr expr RPAR",
"expr : LPAR LT expr expr RPAR",
"expr : LBRA args RBRA expr",
"expr : LPAR expr exprs RPAR",
"exprs : expr",
"exprs : expr exprs",
};

//#line 144 "parser.y"

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
//#line 336 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 45 "parser.y"
{ e = (IASTnode)val_peek(0).obj; }
break;
case 2:
//#line 48 "parser.y"
{ yyval.obj = new ASTprog((List<IASTcommand>)val_peek(1).obj); }
break;
case 3:
//#line 59 "parser.y"
{ List<IASTcommand> r = new ArrayList<IASTcommand>();
r.add((IASTcommand)val_peek(0).obj);
yyval.obj = r; }
break;
case 4:
//#line 62 "parser.y"
{ List<IASTcommand> r = new ArrayList<IASTcommand>(Arrays.asList((IASTcommand)val_peek(2).obj));
r.addAll((List<IASTcommand>)val_peek(0).obj);
yyval.obj = r; }
break;
case 5:
//#line 65 "parser.y"
{ List<IASTcommand> r = new ArrayList<IASTcommand>(Arrays.asList((IASTcommand)val_peek(2).obj));
r.addAll((List<IASTcommand>)val_peek(0).obj);
yyval.obj = r; }
break;
case 6:
//#line 70 "parser.y"
{ yyval.obj = new ASTecho((IASTexpression)val_peek(0).obj); }
break;
case 7:
//#line 71 "parser.y"
{ yyval.obj = new ASTset(new ASTident(val_peek(1).sval), (IASTexpression)val_peek(0).obj); }
break;
case 8:
//#line 72 "parser.y"
{ yyval.obj = new ASTifBlock((IASTexpression)val_peek(2).obj, (ASTblock)val_peek(1).obj, (ASTblock)val_peek(0).obj); }
break;
case 9:
//#line 73 "parser.y"
{ yyval.obj = new ASTwhile((IASTexpression)val_peek(1).obj, (ASTblock)val_peek(0).obj); }
break;
case 10:
//#line 74 "parser.y"
 { yyval.obj = new ASTcall(new ASTident(val_peek(1).sval), (List<IASTexpression>)val_peek(0).obj); }
break;
case 11:
//#line 77 "parser.y"
{ yyval.obj = new ASTarg(new ASTident(val_peek(2).sval), new ASTtypes((IASTtype)val_peek(0).obj)); }
break;
case 12:
//#line 81 "parser.y"
{ ArrayList<ASTarg> r = new ArrayList<ASTarg>();
r.add((ASTarg)val_peek(0).obj);
yyval.obj = r; }
break;
case 13:
//#line 84 "parser.y"
{ ((ArrayList<ASTarg>)val_peek(0).obj).add((ASTarg)val_peek(2).obj); java.util.Collections.reverse((ArrayList<ASTarg>)val_peek(0).obj); yyval.obj = val_peek(0).obj; }
break;
case 14:
//#line 87 "parser.y"
{ yyval.obj = ASTprimitiveType.BOOLEAN; }
break;
case 15:
//#line 88 "parser.y"
{ yyval.obj = ASTprimitiveType.INTEGER; }
break;
case 16:
//#line 89 "parser.y"
{ yyval.obj = new ASTfunctionType((ASTtypes)val_peek(3).obj, (IASTtype)val_peek(1).obj); }
break;
case 17:
//#line 92 "parser.y"
{ yyval.obj = new ASTtypes((IASTtype)val_peek(0).obj); }
break;
case 18:
//#line 93 "parser.y"
{ yyval.obj = new ASTtypes((IASTtype)val_peek(2).obj, (ASTtypes)val_peek(0).obj); }
break;
case 19:
//#line 96 "parser.y"
{ yyval.obj = new ASTblock((List<IASTcommand>)val_peek(1).obj); }
break;
case 20:
//#line 99 "parser.y"
{ yyval.obj = new ASTconst(new ASTident(val_peek(2).sval), new ASTtypes((IASTtype)val_peek(1).obj), (IASTexpression)val_peek(0).obj); }
break;
case 21:
//#line 100 "parser.y"
{ yyval.obj = new ASTfun(new ASTident(val_peek(5).sval), new ASTtypes((IASTtype)val_peek(4).obj), (ArrayList<ASTarg>)val_peek(2).obj, (IASTexpression)val_peek(0).obj); }
break;
case 22:
//#line 101 "parser.y"
{yyval.obj = new ASTfunRec(new ASTident(val_peek(5).sval), new ASTtypes((IASTtype)val_peek(4).obj), (ArrayList<ASTarg>)val_peek(2).obj, (IASTexpression) val_peek(0).obj ); }
break;
case 23:
//#line 102 "parser.y"
{ yyval.obj = new ASTvar(new ASTident(val_peek(1).sval), new ASTtypes((IASTtype)val_peek(0).obj)); }
break;
case 24:
//#line 103 "parser.y"
{ yyval.obj = new ASTproc(new ASTident(val_peek(4).sval), (ArrayList<ASTarg>) val_peek(2).obj, (ASTblock) val_peek(0).obj); }
break;
case 25:
//#line 104 "parser.y"
{ yyval.obj = new ASTproc(new ASTident(val_peek(4).sval), (ArrayList<ASTarg>) val_peek(2).obj, (ASTblock) val_peek(0).obj); }
break;
case 26:
//#line 108 "parser.y"
{ yyval.obj = new ASTnum(val_peek(0).ival); }
break;
case 27:
//#line 109 "parser.y"
{ yyval.obj = new ASTident(val_peek(0).sval); }
break;
case 28:
//#line 110 "parser.y"
{ yyval.obj = new ASTboolean(true); }
break;
case 29:
//#line 111 "parser.y"
{ yyval.obj = new ASTboolean(false); }
break;
case 30:
//#line 112 "parser.y"
{ yyval.obj = new ASTif((IASTexpression)val_peek(3).obj, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 31:
//#line 113 "parser.y"
{ yyval.obj = new ASToperation(Operator.ADD, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 32:
//#line 114 "parser.y"
{ yyval.obj = new ASToperation(Operator.SUB, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 33:
//#line 115 "parser.y"
{ yyval.obj = new ASToperation(Operator.MUL, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 34:
//#line 116 "parser.y"
{ yyval.obj = new ASToperation(Operator.DIV, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 35:
//#line 117 "parser.y"
{ yyval.obj = new ASToperation(Operator.AND, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 36:
//#line 118 "parser.y"
{ yyval.obj = new ASToperation(Operator.OR, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 37:
//#line 119 "parser.y"
{ yyval.obj = new ASToperation(Operator.NOT, (IASTexpression)val_peek(1).obj); }
break;
case 38:
//#line 120 "parser.y"
{ yyval.obj = new ASToperation(Operator.EQ, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 39:
//#line 121 "parser.y"
{ yyval.obj = new ASToperation(Operator.LT, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 40:
//#line 122 "parser.y"
{ yyval.obj = new ASTlambda((ArrayList<ASTarg>) val_peek(2).obj, (IASTexpression) val_peek(0).obj);}
break;
case 41:
//#line 123 "parser.y"
 { yyval.obj = new ASTclosure((IASTexpression)val_peek(2).obj, (ArrayList<IASTexpression>)val_peek(1).obj); }
break;
case 42:
//#line 136 "parser.y"
{ List<IASTexpression> r = new ArrayList<IASTexpression>();
r.add((IASTexpression)val_peek(0).obj);
yyval.obj = r; }
break;
case 43:
//#line 139 "parser.y"
{ List<IASTexpression> r = new ArrayList<IASTexpression>(Arrays.asList((IASTexpression)val_peek(1).obj));
r.addAll((List<IASTexpression>)val_peek(0).obj);
yyval.obj = r; }
break;
//#line 669 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
