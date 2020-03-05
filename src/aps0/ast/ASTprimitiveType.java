package aps0.ast;

import aps0.interfaces.IASTtype;
import aps0.interfaces.IASTvisitor;

public enum ASTprimitiveType implements IASTtype {
	
	INTEGER("int"), BOOLEAN("boolean");
	private String name;

	ASTprimitiveType(String name) {
		this.name = name;
	}

	@Override
	public String toPrologString() {
		return name;
	}
	
	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
