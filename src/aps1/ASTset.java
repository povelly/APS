package aps1;

import aps0.ASTident;
import interfaces.IASTexpression;
import interfaces.IASTstatement;
import interfaces.IASTvisitor;

public class ASTset implements IASTstatement {
	
	private final ASTident var;
	private final IASTexpression value;
	
	public ASTset(ASTident var, IASTexpression value) {
		this.var = var;
		this.value = value;
	}
	
	public ASTident getVar() {
		return this.var;
	}
	
	public IASTexpression getValue() {
		return this.value;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
