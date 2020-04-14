package aps1;

import aps0.ASTident;
import aps0.ASTtypes;
import interfaces.IASTdeclaration;
import interfaces.IASTvisitor;

public class ASTvar implements IASTdeclaration {

	private final ASTident var;
	private final ASTtypes type;

	public ASTvar(ASTident var, ASTtypes type) {
		this.var = var;
		this.type = type;
	}

	public ASTident getVar() {
		return this.var;
	}

	public ASTtypes getType() {
		return this.type;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
