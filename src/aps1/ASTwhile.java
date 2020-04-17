package aps1;

import interfaces.IASTexpression;
import interfaces.IASTstatement;
import interfaces.IASTvisitor;

public class ASTwhile implements IASTstatement {
	
	private final IASTexpression condition;
	private final ASTblock corps;
	
	public ASTwhile(IASTexpression condition, ASTblock corps) {
		this.condition = condition;
		this.corps = corps;
	}
	
	public IASTexpression getCondition() {
		return this.condition;
	}

	public ASTblock getCorps() {
		return this.corps;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
