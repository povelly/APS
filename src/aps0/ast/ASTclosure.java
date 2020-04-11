package aps0.ast;

import java.util.List;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

public class ASTclosure implements IASTexpression {
	
	private final IASTexpression expr;
	private final List<IASTexpression> arguments;
	
	public ASTclosure(IASTexpression expr, List<IASTexpression> arguments) {
		this.expr = expr;
		this.arguments = arguments;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}
	
	public List<IASTexpression> getArguments() {
		return this.arguments;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}