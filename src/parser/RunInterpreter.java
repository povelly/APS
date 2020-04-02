package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import aps0.ast.ASTident;
import aps0.interfaces.IASTprogram;
import interpreter.Context;
import interpreter.Interpreter;

public class RunInterpreter {
	
	public static void main(String args[]) throws Exception {
		Parser yyparser;
		IASTprogram prog;
		try {
			yyparser = new Parser(new InputStreamReader(new FileInputStream(args[0])));
			yyparser.yyparse();
			prog = (IASTprogram) yyparser.yyval.obj;

			if (prog != null) {
				Interpreter interpreter = new Interpreter(new Context(new ASTident("vglobale1"), null, null));
//				prog.accept(interpreter, new Context(new ASTident("v1"), null, null)); // TODO changer context
				prog.accept(interpreter, interpreter.getGlobalVars()); // TODO changer context
			}
			else
				System.out.println("Null");
		} catch (FileNotFoundException e) {
			System.out.println("\nImpossible de lire le/les fichier(s)\n" + e.getMessage() + "\n");
		}
	}

//	public static void main(String args[]) throws Exception {
//		Parser yyparser;
//		IASTnode prog;
//		try {
//			yyparser = new Parser(new InputStreamReader(new FileInputStream(args[0])));
//			yyparser.yyparse();
//			prog = (IASTnode) yyparser.yyval.obj;
//
//			if (prog != null) {
//				Interpreter interpreter = new Interpreter(new Context(new ASTident("vglobale1"), null, null));
//				prog.accept(interpreter, new Context(new ASTident("v1"), null, null)); // TODO changer context
//			}
//			else
//				System.out.println("Null");
//		} catch (FileNotFoundException e) {
//			System.out.println("\nImpossible de lire le/les fichier(s)\n" + e.getMessage() + "\n");
//		}
//	}

}
