package parser;

import java.io.*;

import aps0.interfaces.IASTnode;

public class PrologTerm {

	public static void main(String args[]) {
		Parser yyparser;
		IASTnode prog;
		try {
			yyparser = new Parser(new InputStreamReader(new FileInputStream(args[0])));
			yyparser.yyparse();
			prog = (IASTnode) yyparser.yyval.obj;

			if (prog != null)
				System.out.println(prog.toPrologString());
			else
				System.out.println("Null");
		} catch (FileNotFoundException e) {
			System.out.println("\nImpossible de lire le/les fichier(s)\n" + e.getMessage() + "\n");
		}
	}

}