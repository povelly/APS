package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;

public class ASTident implements IASTexpression {
	
	private final String name;

	public ASTident(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof ASTident))
			return false;
		return this.name.equals(((ASTident) o).getString());
	}
	
}