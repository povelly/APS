package aps0;

import java.util.List;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interpreter.ExpressionEvaluator;

public class ASTclosure implements IASTexpression {
	
	private final IASTexpression expr;
	private final List<IASTexpression> args;
	
	public ASTclosure(IASTexpression expr, List<IASTexpression> args) {
		this.expr = expr;
		this.args = args;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}
	
	public List<IASTexpression> getArgs() {
		return this.args;
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
