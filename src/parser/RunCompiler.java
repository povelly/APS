package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import aps0.interfaces.IASTnode;
import compiler.Compiler;

public class RunCompiler {

	public static void main(String args[]) throws Exception {
		Parser yyparser;
		IASTnode prog;
		try {
			yyparser = new Parser(new InputStreamReader(new FileInputStream(args[0])));
			yyparser.yyparse();
			prog = (IASTnode) yyparser.yyval.obj;

			if (prog != null) {
				//System.out.println(prog.toPrologString());
				
				Compiler compiler = new Compiler();
				System.out.println(prog.accept(compiler, null));
			}
			else
				System.out.println("Null");
		} catch (FileNotFoundException e) {
			System.out.println("\nImpossible de lire le/les fichier(s)\n" + e.getMessage() + "\n");
		}
	}

}