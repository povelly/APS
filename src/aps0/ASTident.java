package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interpreter.ExpressionEvaluator;

public class ASTident implements IASTexpression {
	
	private final String name;

	public ASTident(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
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
	
	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof ASTident))
			return false;
		return this.name.equals(((ASTident) o).getName());
	}
	
}