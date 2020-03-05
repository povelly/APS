package aps0.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

public class ASTboolean implements IASTexpression{
	
	private final boolean value;
	
	public ASTboolean(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	@Override
	public String toPrologString() {
		return ((Boolean) value).toString();
	}
	
	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
