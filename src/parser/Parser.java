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
  import aps0.ast.*;
  import aps0.interfaces.*;
//#line 23 "Parser.java"




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
public final static short PLUS=262;
public final static short MINUS=263;
public final static short TIMES=264;
public final static short DIV=265;
public final static short LBRA=266;
public final static short RBRA=267;
public final static short LPAR=268;
public final static short RPAR=269;
public final static short PV=270;
public final static short DP=271;
public final static short VG=272;
public final static short STAR=273;
public final static short ARROW=274;
public final static short CONST=275;
public final static short FUN=276;
public final static short REC=277;
public final static short ECHO=278;
public final static short TRUE=279;
public final static short FALSE=280;
public final static short NOT=281;
public final static short AND=282;
public final static short OR=283;
public final static short EQ=284;
public final static short LT=285;
public final static short IF=286;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    4,    4,    4,    5,    5,    5,    5,    5,
    8,    9,    9,    6,    6,    6,    7,    7,   10,   10,
   10,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    2,
};
final static short yylen[] = {                            2,
    1,    3,    1,    3,    3,    2,    2,    2,    2,    2,
    3,    1,    3,    1,    1,    5,    1,    3,    4,    7,
    8,    1,    1,    1,    1,    6,    5,    5,    5,    5,
    4,    4,    1,    2,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   22,    0,   15,   14,    0,    0,   24,   25,
    6,    0,    8,    0,    9,   10,    2,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   23,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    5,    4,    0,
   19,    0,    0,   11,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   18,   13,    0,    0,   31,    0,    0,
    0,    0,    0,   34,   32,    0,    0,    0,   27,   28,
   29,   30,    0,   16,   20,    0,   26,   21,
};
final static short yydgoto[] = {                          2,
   43,   62,    3,    7,    8,   44,   45,   24,   25,    9,
};
final static short yysindex[] = {                      -262,
 -264,    0,    0, -241, -256, -143, -245, -244, -232, -253,
 -253, -219,    0, -230,    0,    0, -216, -177,    0,    0,
    0, -228,    0, -226,    0,    0,    0, -264, -264, -253,
 -202, -218, -253, -253, -230, -220,    0, -202, -202, -202,
 -202, -202, -202, -228, -214, -253, -216,    0,    0, -158,
    0, -216, -205,    0, -202, -202, -202, -202, -202, -202,
 -202, -207, -253,    0,    0, -204, -216,    0, -201, -200,
 -199, -197, -202,    0,    0, -196, -202, -193,    0,    0,
    0,    0, -194,    0,    0, -202,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0, -191,    0,    0,
    0,    0,    0, -251,    0,    0,    0,    0,    0,    0,
    0, -247,    0, -243,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -257,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -190,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   -6,    4,    0,    2,    0,   -5,   -4,    0,   -8,   84,
};
final static int YYTABLESIZE=137;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         21,
   22,   23,   11,    1,   31,   32,   15,   16,   36,   17,
    4,    5,   17,    6,   30,   23,   17,   10,   23,    7,
   12,   27,    7,   12,   51,   28,   12,   53,   54,   48,
   49,   56,   57,   58,   59,   60,   61,   29,   65,   33,
   34,   64,   35,   66,   46,   47,   55,   52,   68,   69,
   70,   71,   72,   73,   61,   13,   37,   76,   78,   63,
   67,   75,   77,   17,   74,   50,   83,   79,   80,   81,
   85,   82,   84,   86,   87,    3,   19,   20,   33,   88,
   13,   37,   15,   16,   38,   39,   40,   41,   17,   26,
   18,    0,    0,    0,    0,    0,    0,    0,    0,   13,
   37,   19,   20,   38,   39,   40,   41,   17,   42,   50,
    0,    0,    0,    0,   13,   14,   15,   16,    0,    0,
   19,   20,   17,    0,   18,    0,    0,   42,    0,    0,
    0,    4,    5,    0,    0,   19,   20,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
    6,    6,  259,  266,   10,   11,  260,  261,   17,  267,
  275,  276,  270,  278,  268,  267,  274,  259,  270,  267,
  277,  267,  270,  267,   31,  270,  270,   33,   34,   28,
   29,   38,   39,   40,   41,   42,   43,  270,   47,  259,
  271,   46,  259,   52,  273,  272,  267,  266,   55,   56,
   57,   58,   59,   60,   61,  258,  259,   63,   67,  274,
  266,  269,  267,  266,   61,  268,   73,  269,  269,  269,
   77,  269,  269,  267,  269,  267,  279,  280,  269,   86,
  258,  259,  260,  261,  262,  263,  264,  265,  266,    6,
  268,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
  259,  279,  280,  262,  263,  264,  265,  266,  286,  268,
   -1,   -1,   -1,   -1,  258,  259,  260,  261,   -1,   -1,
  279,  280,  266,   -1,  268,   -1,   -1,  286,   -1,   -1,
   -1,  275,  276,   -1,   -1,  279,  280,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=286;
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
null,null,null,"NL","NUM","IDENT","INTEGER","BOOLEAN","PLUS","MINUS","TIMES",
"DIV","LBRA","RBRA","LPAR","RPAR","PV","DP","VG","STAR","ARROW","CONST","FUN",
"REC","ECHO","TRUE","FALSE","NOT","AND","OR","EQ","LT","IF",
};
final static String yyrule[] = {
"$accept : line",
"line : prog",
"prog : LBRA cmds RBRA",
"cmds : stat",
"cmds : dec PV cmds",
"cmds : stat PV cmds",
"stat : ECHO expr",
"stat : ECHO type",
"stat : ECHO types",
"stat : ECHO args",
"stat : ECHO dec",
"arg : IDENT DP type",
"args : arg",
"args : arg VG args",
"type : BOOLEAN",
"type : INTEGER",
"type : LPAR types ARROW type RPAR",
"types : type",
"types : type STAR types",
"dec : CONST IDENT type expr",
"dec : FUN IDENT type LBRA args RBRA expr",
"dec : FUN REC IDENT type LBRA args RBRA expr",
"expr : NUM",
"expr : IDENT",
"expr : TRUE",
"expr : FALSE",
"expr : LPAR IF expr expr expr RPAR",
"expr : LPAR PLUS expr expr RPAR",
"expr : LPAR MINUS expr expr RPAR",
"expr : LPAR TIMES expr expr RPAR",
"expr : LPAR DIV expr expr RPAR",
"expr : LBRA args RBRA expr",
"expr : LPAR expr exprs RPAR",
"exprs : expr",
"exprs : expr exprs",
};

//#line 115 "parser.y"

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
//#line 299 "Parser.java"
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
//#line 49 "parser.y"
{ e = (IASTnode)val_peek(0).obj; }
break;
case 2:
//#line 52 "parser.y"
{ yyval.obj = new ASTprog((ASTcommands)val_peek(1).obj); }
break;
case 3:
//#line 55 "parser.y"
{ yyval.obj = new ASTcommands((IASTnode)val_peek(0).obj, null); }
break;
case 4:
//#line 56 "parser.y"
{ yyval.obj = new ASTcommands((IASTnode)val_peek(2).obj, (ASTcommands)val_peek(0).obj); }
break;
case 5:
//#line 57 "parser.y"
{ yyval.obj = new ASTcommands((IASTnode)val_peek(2).obj, (ASTcommands)val_peek(0).obj); }
break;
case 6:
//#line 60 "parser.y"
{ yyval.obj = new ASTecho((IASTexpression)val_peek(0).obj); }
break;
case 7:
//#line 61 "parser.y"
{ yyval.obj = new PRINT((IASTtype)val_peek(0).obj); }
break;
case 8:
//#line 62 "parser.y"
{ yyval.obj = new PRINT((ASTtypes)val_peek(0).obj); }
break;
case 9:
//#line 63 "parser.y"
{ yyval.obj = new PRINT_LIST((ArrayList<ASTarg>)val_peek(0).obj); }
break;
case 10:
//#line 64 "parser.y"
{ yyval.obj = new PRINT((IASTdec)val_peek(0).obj); }
break;
case 11:
//#line 67 "parser.y"
{ yyval.obj = new ASTarg(new ASTid(val_peek(2).sval), new ASTtypes((IASTtype)val_peek(0).obj)); }
break;
case 12:
//#line 71 "parser.y"
{ ArrayList<ASTarg> r = new ArrayList<ASTarg>();
r.add((ASTarg)val_peek(0).obj);
yyval.obj = r; }
break;
case 13:
//#line 74 "parser.y"
{ ((ArrayList<ASTarg>)val_peek(0).obj).add((ASTarg)val_peek(2).obj); java.util.Collections.reverse((ArrayList<ASTarg>)val_peek(0).obj); yyval.obj = val_peek(0).obj; }
break;
case 14:
//#line 77 "parser.y"
{ yyval.obj = PrimitiveType.BOOLEAN; }
break;
case 15:
//#line 78 "parser.y"
{ yyval.obj = PrimitiveType.INTEGER; }
break;
case 16:
//#line 79 "parser.y"
{ yyval.obj = new ASTfunctionType((ASTtypes)val_peek(3).obj, (IASTtype)val_peek(1).obj); }
break;
case 17:
//#line 82 "parser.y"
{ yyval.obj = new ASTtypes((IASTtype)val_peek(0).obj); }
break;
case 18:
//#line 83 "parser.y"
{ yyval.obj = new ASTtypes((IASTtype)val_peek(2).obj, (ASTtypes)val_peek(0).obj); }
break;
case 19:
//#line 86 "parser.y"
{ yyval.obj = new ASTconst(new ASTid(val_peek(2).sval), new ASTtypes((IASTtype)val_peek(1).obj), (IASTexpression)val_peek(0).obj); }
break;
case 20:
//#line 87 "parser.y"
{ yyval.obj = new ASTfun(new ASTid(val_peek(5).sval), new ASTtypes((IASTtype)val_peek(4).obj), (ArrayList<ASTarg>)val_peek(2).obj, (IASTexpression)val_peek(0).obj); }
break;
case 21:
//#line 88 "parser.y"
{yyval.obj = new ASTfunRec(new ASTid(val_peek(5).sval), new ASTtypes((IASTtype)val_peek(4).obj), (ArrayList<ASTarg>)val_peek(2).obj, (IASTexpression) val_peek(0).obj ); }
break;
case 22:
//#line 96 "parser.y"
{ yyval.obj = new ASTnum(val_peek(0).ival); }
break;
case 23:
//#line 97 "parser.y"
{ yyval.obj = new ASTid(val_peek(0).sval); }
break;
case 24:
//#line 98 "parser.y"
{ yyval.obj = new ASTboolean(true); }
break;
case 25:
//#line 99 "parser.y"
{ yyval.obj = new ASTboolean(false); }
break;
case 26:
//#line 100 "parser.y"
{ yyval.obj = new ASTif((IASTexpression)val_peek(3).obj, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 27:
//#line 101 "parser.y"
{ yyval.obj = new ASTbinaryOperation(Operator.ADD, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 28:
//#line 102 "parser.y"
{ yyval.obj = new ASTbinaryOperation(Operator.SUB, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 29:
//#line 103 "parser.y"
{ yyval.obj = new ASTbinaryOperation(Operator.MUL, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 30:
//#line 104 "parser.y"
{ yyval.obj = new ASTbinaryOperation(Operator.DIV, (IASTexpression)val_peek(2).obj, (IASTexpression)val_peek(1).obj); }
break;
case 31:
//#line 105 "parser.y"
{ yyval.obj = new ASTfunctionnalAbstraction((ArrayList<ASTarg>) val_peek(2).obj, (IASTexpression) val_peek(0).obj);}
break;
case 32:
//#line 106 "parser.y"
 { yyval.obj = new ASTapplication((IASTexpression)val_peek(2).obj, (ArrayList<IASTexpression>)val_peek(1).obj); }
break;
case 33:
//#line 109 "parser.y"
{ ArrayList<IASTexpression> r = new ArrayList<IASTexpression>();
r.add((IASTexpression)val_peek(0).obj);
yyval.obj = r; }
break;
case 34:
//#line 112 "parser.y"
{ ((ArrayList<IASTexpression>)val_peek(0).obj).add((IASTexpression)val_peek(1).obj); yyval.obj = val_peek(0).obj; }
break;
//#line 588 "Parser.java"
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
