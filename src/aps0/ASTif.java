package aps0;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interpreter.ExpressionEvaluator;

public class ASTif implements IASTexpression {

	private final IASTexpression condition;
	private final IASTexpression consequence;
	private final IASTexpression alternant;

	public ASTif(IASTexpression condition, IASTexpression consequence, IASTexpression alternant) {
		this.condition = condition;
		this.consequence = consequence;
		this.alternant = alternant;
	}

	public IASTexpression getCondition() {
		return this.condition;
	}

	public IASTexpression getConsequence() {
		return this.consequence;
	}

	public IASTexpression getAlternant() {
		return this.alternant;
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
