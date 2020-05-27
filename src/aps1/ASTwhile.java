package aps1;

import interfaces.IASTexpression;
import interfaces.IASTstatement;
import interfaces.IASTvisitor;

public class ASTwhile implements IASTstatement {
	
	private final IASTexpression condition;
	private final ASTblock body;
	
	public ASTwhile(IASTexpression condition, ASTblock body) {
		this.condition = condition;
		this.body = body;
	}
	
	public IASTexpression getCondition() {
		return this.condition;
	}

	public ASTblock getBody() {
		return this.body;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
