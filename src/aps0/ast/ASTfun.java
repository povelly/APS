package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTdeclaration;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

public class ASTfun implements IASTdeclaration {

	protected final ArrayList<ASTarg> args;
	protected final IASTexpression expr;
	protected final ASTident name;
	protected final ASTtypes types;

	public ASTfun(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		this.args = args;
		this.expr = expr;
		this.name = name;
		this.types = types;
	}

	public ArrayList<ASTarg> getArgs() {
		return this.args;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	public ASTident getName() {
		return this.name;
	}

	public ASTtypes getTypes() {
		return this.types;
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

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
