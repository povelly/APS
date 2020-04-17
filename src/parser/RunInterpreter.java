package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import interfaces.IASTprogram;
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
				Interpreter interpreter = new Interpreter();
				prog.accept(interpreter, new Context());
			}
			else
				System.out.println("Null");
		} catch (FileNotFoundException e) {
			System.out.println("\nImpossible de lire le/les fichier(s)\n" + e.getMessage() + "\n");
		}
	}

}
