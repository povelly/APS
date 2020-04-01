package aps0.ast;

import aps0.interfaces.IASTdeclaration;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

public class ASTconst implements IASTdeclaration {

	private final ASTident name;
	private final ASTtypes types;
	private final IASTexpression expr;

	public ASTconst(ASTident name, ASTtypes types, IASTexpression expr) {
		this.name = name;
		this.types = types;
		this.expr = expr;
	}

	public ASTident getName() {
		return this.name;
	}

	public ASTtypes getTypes() {
		return this.types;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
