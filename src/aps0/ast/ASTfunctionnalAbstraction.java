package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;

public class ASTfunctionnalAbstraction implements IASTexpression {

	private final ArrayList<ASTarg> args;
	private final IASTexpression expr;
	
	public ASTfunctionnalAbstraction(ArrayList<ASTarg> args, IASTexpression expr) {
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
	public String toPrologString() {
		String s = "funAbs [";
		for (ASTarg arg : args)
			s += arg.toPrologString() +", ";
		s += "] " + expr.toPrologString();
		return s;
	}

}
