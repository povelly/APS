package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;

public class ASTboolean implements IASTexpression{
	
	private final boolean value;
	
	public ASTboolean(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
