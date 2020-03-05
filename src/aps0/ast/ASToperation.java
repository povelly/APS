package aps0.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

public class ASToperation implements IASTexpression {

	private final Operator operator;
	private final IASTexpression leftOperand;
	private final IASTexpression rightOperand;

	public ASToperation(Operator operator, IASTexpression leftOperand, IASTexpression rightOperand) {
		this.operator = operator;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	public ASToperation(Operator operator, IASTexpression operand) {
		this.operator = operator;
		this.leftOperand = operand;
		this.rightOperand = null;
	}

	public Operator getOperator() {
		return this.operator;
	}

	public IASTexpression getLeftOperand() {
		return this.leftOperand;
	}

	public IASTexpression getRightOperand() {
		return this.rightOperand;
	}

//	@Override
//	public String toPrologString() {
//		return operator.toString() + "(" + leftOperand.toPrologString() + "," + rightOperand.toPrologString()
//				+ "])";
//	}
	
	@Override
	public String toPrologString() {
		if (operator == Operator.NOT)
			return operator.getName() + "(" + leftOperand.toPrologString() + ")";
		return operator.getName() + "(" + leftOperand.toPrologString() + ", " + rightOperand.toPrologString() + ")";
	}
	
	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
