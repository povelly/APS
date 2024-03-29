package aps1;

import interfaces.IASTexpression;
import interfaces.IASTstatement;
import interfaces.IASTvisitor;

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
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
