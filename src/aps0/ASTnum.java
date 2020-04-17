package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interpreter.ExpressionEvaluator;

public class ASTnum implements IASTexpression {
	
	private final Integer val;

	public ASTnum(Integer val) {
		this.val = val;
	}
	
	public Integer getVal() {
		return this.val;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}
	
	@Override
	public <Result, Env, Err> Result accept(ExpressionEvaluator<Result, Env, Exception> visitor, Env env)
			throws Exception {
		return visitor.visit(this, env);
	}
	
}