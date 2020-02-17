package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTbinaryOperation implements IASTexpression {

	private final Operator operator;
	private final IASTexpression leftOperand;
	private final IASTexpression rightOperand;

	public ASTbinaryOperation(Operator operator, IASTexpression leftOperand, IASTexpression rightOperand) {
		this.operator = operator;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public Operator getOperator() {
		return operator;
	}

	public IASTexpression getLeftOperand() {
		return leftOperand;
	}

	public IASTexpression getRightOperand() {
		return rightOperand;
	}

	@Override
	public String toPrologString() {
		return operator.toString() + "(" + leftOperand.toPrologString() + "," + rightOperand.toPrologString()
				+ "])";
	}

}
