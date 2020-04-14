package aps0;

import java.util.ArrayList;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interfaces.IFun;

public class ASTlambda implements IASTexpression, IFun {

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

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
