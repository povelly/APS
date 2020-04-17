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
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
