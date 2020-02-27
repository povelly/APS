package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTdec;
import aps0.interfaces.IASTexpression;

public class ASTfun implements IASTdec {

	private final ASTid name;
	private final ASTtypes types;
	private final ArrayList<ASTarg> args;
	private final IASTexpression expr;

	public ASTfun(ASTid name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		this.name = name;
		this.types = types;
		this.args = args;
		this.expr = expr;
	}

	public ASTid getName() {
		return this.name;
	}

	public ASTtypes getTypes() {
		return this.types;
	}

	public ArrayList<ASTarg> getArgs() {
		return this.args;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	// @Override
	// public String toPrologString() {
	// String s = "FUN " + types.toPrologString() + " " + name.toPrologString() + "
	// (";
	// for (ASTarg arg : args)
	// s += arg.toPrologString();
	// s += ") {\n" + expr.toPrologString() + "\n}";
	// return s;
	// }

	@Override
	public String toPrologString() {
		String s = "funDef(" + name.toPrologString() + ", " + types.toPrologString() + ", [";
		for (int i = 0; i < args.size() - 1; i++)
			s += args.get(i).toPrologString() + ", ";
		s += args.get(args.size() - 1).toPrologString() + "], " + expr.toPrologString() + ")";
		return s;
	}

}
