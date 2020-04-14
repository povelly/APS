package aps0;

import interfaces.IASTexpression;
import interfaces.IASTstatement;
import interfaces.IASTvisitor;

public class ASTecho implements IASTstatement {
	
	private final IASTexpression expr;
	
	public ASTecho(IASTexpression expr) {
		this.expr = expr;
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
