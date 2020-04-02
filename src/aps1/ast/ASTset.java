package aps1.ast;

import aps0.ast.ASTident;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTstatement;
import aps0.interfaces.IASTvisitor;

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
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
