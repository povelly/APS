package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

public class ASTlambda implements IASTexpression {

	protected final ArrayList<ASTarg> args;
	protected final IASTexpression expr;

	public ASTlambda(ArrayList<ASTarg> args, IASTexpression expr) {
		this.args = args;
		this.expr = expr;
	}

	public ArrayList<ASTarg> getArgs() {
		return this.args;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	// @Override
	// public String toPrologString() {
	// String s = "funAbs [";
	// for (ASTarg arg : args)
	// s += arg.toPrologString() +", ";
	// s += "] " + expr.toPrologString();
	// return s;
	// }

	@Override
	public String toPrologString() {
		String s = "lambda([";
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
