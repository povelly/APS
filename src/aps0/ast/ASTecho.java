package aps0.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTstatement;

public class ASTecho implements IASTstatement {
	
	private final IASTexpression expr;
	
	public ASTecho(IASTexpression expr) {
		this.expr = expr;
	}
	
	public IASTexpression getExpr() {
		return this.expr;
	}

	@Override
	public String toPrologString() {
		return "print(" + expr.toPrologString() + ")";
	}

}
