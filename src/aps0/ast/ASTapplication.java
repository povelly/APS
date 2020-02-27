package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;

public class ASTapplication implements IASTexpression {

	private final IASTexpression expr;
	private final ArrayList<IASTexpression> exprs;

	public ASTapplication(IASTexpression expr, ArrayList<IASTexpression> exprs) {
		this.expr = expr;
		this.exprs = exprs;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	public ArrayList<IASTexpression> getExprs() {
		return this.exprs;
	}

	@Override
	public String toPrologString() {
		String s = "application(" + expr.toPrologString() + ", (";
		for (int i = 0; i < exprs.size() - 1; i++)
			s += exprs.get(i).toPrologString() + ", ";
		s += exprs.get(exprs.size() - 1).toPrologString() + "))";
		return s;
	}

}
