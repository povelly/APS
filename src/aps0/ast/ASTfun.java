package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;

public class ASTfun extends AbstractNamedFun {

	public ASTfun(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		super(name, types, args, expr);
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
