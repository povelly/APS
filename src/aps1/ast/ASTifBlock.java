package aps1.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTstatement;
import aps0.interfaces.IASTvisitor;

public class ASTifBlock implements IASTstatement {
	
	private final IASTexpression condition;
	private final ASTblock consequence;
	private final ASTblock alternant;
	
	public ASTifBlock(IASTexpression condition, ASTblock consequence, ASTblock alternant) {
		this.condition = condition;
		this.consequence = consequence;
		this.alternant = alternant;
	}
	
	public IASTexpression getCondition() {
		return this.condition;
	}

	public ASTblock getConsequence() {
		return this.consequence;
	}

	public ASTblock getAlternant() {
		return this.alternant;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
