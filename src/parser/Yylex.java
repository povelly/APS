/* The following code was generated by JFlex 1.7.0 */

package parser;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>lexer.lex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\1\61\1\60\1\5\2\0\1\6\22\0\1\60\7\0\1\11"+
    "\1\12\1\16\1\0\1\15\1\1\2\0\12\2\1\14\1\13\2\0"+
    "\1\17\2\0\1\53\1\4\1\20\1\4\1\30\1\25\1\4\1\31"+
    "\1\55\2\4\1\57\1\4\1\22\1\21\1\54\1\4\1\27\1\23"+
    "\1\24\1\26\1\52\1\56\3\4\1\7\1\0\1\10\3\0\1\46"+
    "\1\32\1\3\1\41\1\44\1\45\2\3\1\35\2\3\1\34\1\50"+
    "\1\36\1\33\1\3\1\51\1\42\1\47\1\37\1\43\1\40\4\3"+
    "\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uff95\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\2\1\1\2\1\3\2\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\5\1\15\3\4\1"+
    "\1\4\1\15\1\16\6\0\1\3\1\17\1\20\1\3"+
    "\1\21\4\3\1\22\5\3\2\0\1\23\3\0\1\24"+
    "\1\25\1\26\1\0\1\3\1\27\1\30\2\3\1\31"+
    "\1\3\1\32\1\33\1\34\1\35\1\36\3\0\1\37"+
    "\1\40\1\41\1\42\1\43\1\3\1\44\1\0\1\45"+
    "\1\46\1\47";

  private static int [] zzUnpackAction() {
    int [] result = new int[97];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\226\0\310\0\62\0\372\0\62"+
    "\0\62\0\62\0\62\0\62\0\62\0\62\0\62\0\u012c"+
    "\0\u015e\0\u0190\0\u01c2\0\u01f4\0\u0226\0\u0258\0\u028a\0\u02bc"+
    "\0\u02ee\0\u0320\0\u0352\0\u0384\0\u03b6\0\u03e8\0\u041a\0\u044c"+
    "\0\u047e\0\u04b0\0\u04e2\0\u0514\0\u0546\0\u0578\0\62\0\62"+
    "\0\u05aa\0\u05dc\0\u060e\0\u0640\0\u0672\0\u06a4\0\u06d6\0\310"+
    "\0\310\0\u0708\0\310\0\u073a\0\u076c\0\u079e\0\u07d0\0\310"+
    "\0\u0802\0\u0834\0\u0866\0\u0898\0\u08ca\0\u08fc\0\u092e\0\62"+
    "\0\u0960\0\u0992\0\u09c4\0\62\0\62\0\62\0\u09f6\0\u0a28"+
    "\0\310\0\310\0\u0a5a\0\u0a8c\0\310\0\u0abe\0\310\0\310"+
    "\0\310\0\310\0\62\0\u0af0\0\u0b22\0\u0b54\0\62\0\62"+
    "\0\310\0\310\0\310\0\u0b86\0\62\0\u0bb8\0\62\0\310"+
    "\0\62";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[97];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\2\1\6\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\2"+
    "\1\20\2\2\1\21\1\2\1\22\1\2\1\23\1\24"+
    "\1\2\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
    "\1\34\2\5\1\35\1\36\1\37\1\40\1\41\1\5"+
    "\1\42\1\2\1\43\1\44\1\45\1\2\1\46\1\47"+
    "\64\0\1\4\14\0\1\50\44\0\1\4\61\0\3\5"+
    "\13\0\40\5\7\0\1\6\75\0\1\51\31\0\1\52"+
    "\36\0\1\53\57\0\1\54\63\0\1\55\51\0\1\56"+
    "\43\0\3\5\13\0\13\5\1\57\24\5\4\0\3\5"+
    "\13\0\22\5\1\60\15\5\4\0\3\5\13\0\17\5"+
    "\1\61\20\5\4\0\3\5\13\0\16\5\1\62\6\5"+
    "\1\63\12\5\4\0\3\5\13\0\13\5\1\64\24\5"+
    "\4\0\3\5\13\0\22\5\1\65\15\5\4\0\3\5"+
    "\13\0\13\5\1\66\24\5\4\0\3\5\13\0\15\5"+
    "\1\67\22\5\4\0\3\5\13\0\31\5\1\70\6\5"+
    "\4\0\3\5\13\0\26\5\1\71\11\5\4\0\3\5"+
    "\13\0\16\5\1\72\2\5\1\73\16\5\4\0\3\5"+
    "\13\0\23\5\1\74\14\5\4\0\3\5\13\0\23\5"+
    "\1\75\14\5\55\0\1\76\35\0\1\77\57\0\1\100"+
    "\65\0\1\101\110\0\1\46\23\0\1\102\116\0\1\103"+
    "\26\0\1\104\57\0\1\105\57\0\1\106\72\0\1\107"+
    "\32\0\3\5\13\0\13\5\1\110\24\5\4\0\3\5"+
    "\13\0\17\5\1\111\20\5\4\0\3\5\13\0\17\5"+
    "\1\112\20\5\4\0\3\5\13\0\23\5\1\113\14\5"+
    "\4\0\3\5\13\0\15\5\1\114\22\5\4\0\3\5"+
    "\13\0\20\5\1\115\17\5\4\0\3\5\13\0\14\5"+
    "\1\116\23\5\4\0\3\5\13\0\21\5\1\117\16\5"+
    "\4\0\3\5\13\0\21\5\1\120\16\5\4\0\3\5"+
    "\13\0\12\5\1\121\25\5\4\0\3\5\13\0\14\5"+
    "\1\122\23\5\31\0\1\123\53\0\1\124\115\0\1\125"+
    "\27\0\1\126\115\0\1\127\23\0\1\130\42\0\3\5"+
    "\13\0\14\5\1\131\23\5\4\0\3\5\13\0\24\5"+
    "\1\132\13\5\4\0\3\5\13\0\21\5\1\133\16\5"+
    "\4\0\3\5\13\0\27\5\1\134\10\5\22\0\1\135"+
    "\120\0\1\136\26\0\1\137\37\0\3\5\13\0\24\5"+
    "\1\140\13\5\32\0\1\141\31\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3050];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\1\11\1\1\10\11\27\1\2\11"+
    "\6\0\17\1\2\0\1\11\3\0\3\11\1\0\13\1"+
    "\1\11\3\0\2\11\4\1\1\11\1\0\1\11\1\1"+
    "\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[97];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true iff the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true iff the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
  private Parser yyparser;
  public Yylex(java.io.Reader r, Parser yyparser) {
  this(r);
  this.yyparser = yyparser;
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 174) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return 0; }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1;
            } 
            // fall through
          case 40: break;
          case 2: 
            { yyparser.yylval = new ParserVal(Integer.parseInt(yytext()));
  return Parser.NUM;
            } 
            // fall through
          case 41: break;
          case 3: 
            { yyparser.yylval = new ParserVal(yytext());
  return Parser.IDENT;
            } 
            // fall through
          case 42: break;
          case 4: 
            { 
            } 
            // fall through
          case 43: break;
          case 5: 
            { return Parser.LBRA;
            } 
            // fall through
          case 44: break;
          case 6: 
            { return Parser.RBRA;
            } 
            // fall through
          case 45: break;
          case 7: 
            { return Parser.LPAR;
            } 
            // fall through
          case 46: break;
          case 8: 
            { return Parser.RPAR;
            } 
            // fall through
          case 47: break;
          case 9: 
            { return Parser.PV;
            } 
            // fall through
          case 48: break;
          case 10: 
            { return Parser.DP;
            } 
            // fall through
          case 49: break;
          case 11: 
            { return Parser.VG;
            } 
            // fall through
          case 50: break;
          case 12: 
            { return Parser.STAR;
            } 
            // fall through
          case 51: break;
          case 13: 
            { System.err.println("Sorry, backspace doesn't work");
            } 
            // fall through
          case 52: break;
          case 14: 
            { return Parser.ARROW;
            } 
            // fall through
          case 53: break;
          case 15: 
            { return Parser.OR;
            } 
            // fall through
          case 54: break;
          case 16: 
            { return Parser.LT;
            } 
            // fall through
          case 55: break;
          case 17: 
            { return Parser.IF;
            } 
            // fall through
          case 56: break;
          case 18: 
            { return Parser.EQ;
            } 
            // fall through
          case 57: break;
          case 19: 
            { return Parser.IFBLOCK;
            } 
            // fall through
          case 58: break;
          case 20: 
            { return Parser.SET;
            } 
            // fall through
          case 59: break;
          case 21: 
            { return Parser.FUN;
            } 
            // fall through
          case 60: break;
          case 22: 
            { return Parser.REC;
            } 
            // fall through
          case 61: break;
          case 23: 
            { return Parser.INTEGER;
            } 
            // fall through
          case 62: break;
          case 24: 
            { return Parser.NOT;
            } 
            // fall through
          case 63: break;
          case 25: 
            { return Parser.DIV;
            } 
            // fall through
          case 64: break;
          case 26: 
            { return Parser.AND;
            } 
            // fall through
          case 65: break;
          case 27: 
            { return Parser.PLUS;
            } 
            // fall through
          case 66: break;
          case 28: 
            { return Parser.MINUS;
            } 
            // fall through
          case 67: break;
          case 29: 
            { return Parser.TIMES;
            } 
            // fall through
          case 68: break;
          case 30: 
            { return Parser.VAR;
            } 
            // fall through
          case 69: break;
          case 31: 
            { return Parser.CALL;
            } 
            // fall through
          case 70: break;
          case 32: 
            { return Parser.ECHO;
            } 
            // fall through
          case 71: break;
          case 33: 
            { return Parser.BOOLEAN;
            } 
            // fall through
          case 72: break;
          case 34: 
            { return Parser.TRUE;
            } 
            // fall through
          case 73: break;
          case 35: 
            { return Parser.VOID;
            } 
            // fall through
          case 74: break;
          case 36: 
            { return Parser.PROC;
            } 
            // fall through
          case 75: break;
          case 37: 
            { return Parser.CONST;
            } 
            // fall through
          case 76: break;
          case 38: 
            { return Parser.FALSE;
            } 
            // fall through
          case 77: break;
          case 39: 
            { return Parser.WHILE;
            } 
            // fall through
          case 78: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
