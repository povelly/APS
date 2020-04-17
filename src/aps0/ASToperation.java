package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interpreter.ExpressionEvaluator;

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

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}
	
	@Override
	public <Result, Env> Result accept(ExpressionEvaluator<Result, Env> visitor, Env env)
			throws Exception {
		return visitor.visit(this, env);
	}

}
