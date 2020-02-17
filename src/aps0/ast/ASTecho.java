package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTecho implements IASTexpression {
	
	private final IASTexpression expr;
	
	public ASTecho(IASTexpression expr) {
		this.expr = expr;
	}
	
	public IASTexpression getExpr() {
		return this.expr;
	}

	@Override
	public String toPrologString() {
		return "echo(" + expr.toPrologString() + ")";
	}

}
