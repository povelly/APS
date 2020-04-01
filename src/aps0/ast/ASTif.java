package aps0.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

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
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
