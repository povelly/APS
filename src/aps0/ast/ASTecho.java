package aps0.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTstatement;
import aps0.interfaces.IASTvisitor;

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
