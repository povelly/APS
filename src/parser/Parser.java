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
  import aps0.ast.*;
  import aps0.interfaces.*;
  import aps1.ast.*;
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
    0,    1,    2,    2,    2,    3,    3,    3,    3,    4,
    5,    5,    6,    6,    6,    7,    7,    8,    9,    9,
    9,    9,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,   10,   10,   10,   10,   10,   10,   11,   11,
};
final static short yylen[] = {                            2,
    1,    3,    1,    3,    3,    2,    3,    4,    3,    3,
    1,    3,    1,    1,    5,    1,    3,    3,    4,    7,
    8,    3,    1,    1,    1,    1,    6,    5,    5,    5,
    5,    5,    5,    4,    5,    5,    4,    4,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   23,   24,    0,    0,
   25,   26,    6,    0,    0,    0,    0,    2,    0,    0,
   14,   13,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   22,    7,    0,    0,    9,    5,    4,    0,    0,   19,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    8,    0,
    0,    0,    0,   10,   12,   37,   34,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   40,   38,   18,   17,
    0,    0,    0,   32,   33,   35,   36,   28,   29,   30,
   31,    0,   15,   20,    0,   27,   21,
};
final static short yydgoto[] = {                          2,
    3,   11,   12,   38,   39,   58,   59,   54,   13,   76,
   77,
};
final static short yysindex[] = {                      -261,
 -201,    0,    0, -248, -250, -147, -247, -241, -147, -147,
 -242, -240, -236, -244, -244, -237,    0,    0, -235, -178,
    0,    0,    0, -244, -147, -229, -229,    0, -201, -201,
    0,    0, -244, -147, -218, -244, -222, -243, -217, -147,
 -147, -147, -147, -147, -147, -147, -147, -147, -147, -147,
    0,    0, -201, -229,    0,    0,    0, -221, -223,    0,
 -235, -213, -244, -235, -147, -215, -147, -147, -147, -147,
 -147, -147, -147, -147, -147, -147, -214, -211,    0, -244,
 -244, -210, -235,    0,    0,    0,    0, -209, -208, -207,
 -205, -191, -190, -189, -188, -147,    0,    0,    0,    0,
 -187, -147, -184,    0,    0,    0,    0,    0,    0,    0,
    0, -183,    0,    0, -147,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -180,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -172,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -177,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -173,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -22,    0,    0,  -51,   -9,   14,  -25,    0,   -6,
  -21,
};
final static int YYTABLESIZE=129;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         23,
    1,   55,   26,   27,   34,   35,   56,   57,   15,   82,
   14,   24,   85,   50,   51,   31,   32,   25,   52,   33,
   28,   36,   16,   37,   64,   29,   62,   60,   79,   30,
   78,  103,   53,   66,   67,   68,   69,   70,   71,   72,
   73,   74,   75,   61,   63,   65,   81,   80,   83,   87,
   98,   99,  102,   84,   97,  104,  105,  106,   86,  107,
   88,   89,   90,   91,   92,   93,   94,   95,   96,    4,
    5,  101,    6,  108,  109,  110,  111,  113,  115,   17,
   18,  116,    3,   19,    7,   20,    8,    9,   10,  112,
   11,   39,   16,  100,    0,  114,   21,   22,   40,   41,
   42,   43,   44,   45,   46,   47,   48,    0,  117,    0,
   17,   18,    0,   49,   19,    0,   20,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   21,   22,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
  262,   27,    9,   10,   14,   15,   29,   30,  259,   61,
  259,  259,   64,   20,   24,  260,  261,  259,   25,  264,
  263,  259,  273,  259,  268,  266,   36,   34,   54,  266,
   53,   83,  262,   40,   41,   42,   43,   44,   45,   46,
   47,   48,   49,  262,  267,  263,  270,  269,  262,  265,
  265,  263,  263,   63,   76,  265,  265,  265,   65,  265,
   67,   68,   69,   70,   71,   72,   73,   74,   75,  271,
  272,   81,  274,  265,  265,  265,  265,  265,  263,  258,
  259,  265,  263,  262,  286,  264,  288,  289,  290,   96,
  263,  265,  270,   80,   -1,  102,  275,  276,  277,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,  115,   -1,
  258,  259,   -1,  292,  262,   -1,  264,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  275,  276,
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

//#line 141 "parser.y"

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
//#line 323 "Parser.java"
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
//#line 76 "parser.y"
{ yyval.obj = new ASTarg(new ASTident(val_peek(2).sval), new ASTtypes((IASTtype)val_peek(0).obj)); }
break;
case 11:
//#line 80 "parser.y"
{ ArrayList<ASTarg> r = new ArrayList<ASTarg>();
r.add((ASTarg)val_peek(0).obj);
yyval.obj = r; }
break;
case 12:
//#line 83 "parser.y"
{ ((ArrayList<ASTarg>)val_peek(0).obj).add((ASTarg)val_peek(2).obj); java.util.Collections.reverse((ArrayList<ASTarg>)val_peek(0).obj); yyval.obj = val_peek(0).obj; }
break;
case 13:
//#line 86 "parser.y"
{ yyval.obj = ASTprimitiveType.BOOLEAN; }
break;
case 14:
//#line 87 "parser.y"
{ yyval.obj = ASTprimitiveType.INTEGER; }
break;
case 15:
//#line 88 "parser.y"
{ yyval.obj = new ASTfunctionType((ASTtypes)val_peek(3).obj, (IASTtype)val_peek(1).obj); }
break;
case 16:
//#line 91 "parser.y"
{ yyval.obj = new ASTtypes((IASTtype)val_peek(0).obj); }
break;
case 17:
//#line 92 "parser.y"
{ yyval.obj = new ASTtypes((IASTtype)val_peek(2).obj, (ASTtypes)val_peek(0).obj); }
break;
case 18:
//#line 95 "parser.y"
{ yyval.obj = new ASTblock((List<IASTcommand>)val_peek(1).obj); }
break;
case 19:
//#line 98 "parser.y"
{ yyval.obj = new ASTconst(new ASTident(val_peek(2).sval), new ASTtypes((IASTtype)val_peek(1).obj), (IASTexpression)val_peek(0).obj); }
break;
case 20:
//#line 99 "parser.y"
{ yyval.obj = new ASTfun(new ASTident(val_peek(5).sval), new ASTtypes((IASTtype)val_peek(4).obj), (ArrayList<ASTarg>)val_peek(2).obj, (IASTexpression)val_peek(0).obj); }
break;
case 21:
//#line 100 "parser.y"
{yyval.obj = new ASTfunRec(new ASTident(val_peek(5).sval), new ASTtypes((IASTtype)val_peek(4).obj), (ArrayList<ASTarg>)val_peek(2).obj, (IASTexpression) val_peek(0).obj ); }
break;
case 22:
//#line 101 "parser.y"
{ yyval.obj = new ASTvar(new ASTident(val_peek(1).sval), new ASTtypes((IASTtype)val_peek(0).obj)); }
break;
case 23:
//#line 105 "parser.y"
{ yyval.obj = new ASTnum(val_peek(0).ival); }
break;
case 24:
//#line 106 "parser.y"
{ yyval.obj = new ASTident(val_peek(0).sval); }
break;
case 25:
//#line 107 "parser.y"
{ yyval.obj = new ASTboolean(true); }
break;
case 26:
//#line 108 "parser.y"
{ yyval.obj = new ASTboolean(false); }
break;
case 27:
//#line 109 "parser.y"
{ yyval.obj = new ASTif((IASTexpression)val_peek(3).obj, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 28:
//#line 110 "parser.y"
{ yyval.obj = new ASToperation(Operator.ADD, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 29:
//#line 111 "parser.y"
{ yyval.obj = new ASToperation(Operator.SUB, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 30:
//#line 112 "parser.y"
{ yyval.obj = new ASToperation(Operator.MUL, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 31:
//#line 113 "parser.y"
{ yyval.obj = new ASToperation(Operator.DIV, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 32:
//#line 114 "parser.y"
{ yyval.obj = new ASToperation(Operator.AND, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 33:
//#line 115 "parser.y"
{ yyval.obj = new ASToperation(Operator.OR, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 34:
//#line 116 "parser.y"
{ yyval.obj = new ASToperation(Operator.NOT, (IASTexpression)val_peek(1).obj); }
break;
case 35:
//#line 117 "parser.y"
{ yyval.obj = new ASToperation(Operator.EQ, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 36:
//#line 118 "parser.y"
{ yyval.obj = new ASToperation(Operator.LT, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 37:
//#line 119 "parser.y"
{ yyval.obj = new ASTlambda((ArrayList<ASTarg>) val_peek(2).obj, (IASTexpression) val_peek(0).obj);}
break;
case 38:
//#line 120 "parser.y"
 { yyval.obj = new ASTapplication((IASTexpression)val_peek(2).obj, (ArrayList<IASTexpression>)val_peek(1).obj); }
break;
case 39:
//#line 133 "parser.y"
{ List<IASTexpression> r = new ArrayList<IASTexpression>();
r.add((IASTexpression)val_peek(0).obj);
yyval.obj = r; }
break;
case 40:
//#line 136 "parser.y"
{ List<IASTexpression> r = new ArrayList<IASTexpression>(Arrays.asList((IASTexpression)val_peek(1).obj));
r.addAll((List<IASTexpression>)val_peek(0).obj);
yyval.obj = r; }
break;
//#line 644 "Parser.java"
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
