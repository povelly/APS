package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;

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
	
}