package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interpreter.ExpressionEvaluator;

public class ASTnum implements IASTexpression {
	
	private final Integer value;

	public ASTnum(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
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