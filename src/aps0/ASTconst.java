package aps0;

import interfaces.IASTdeclaration;
import interfaces.IASTexpression;
import interfaces.IASTvisitor;

public class ASTconst implements IASTdeclaration {

	private final ASTident name;
	private final ASTtypes type;
	private final IASTexpression expr;

	public ASTconst(ASTident name, ASTtypes types, IASTexpression expr) {
		this.name = name;
		this.type = types;
		this.expr = expr;
	}

	public ASTident getName() {
		return this.name;
	}

	public ASTtypes getType() {
		return this.type;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}
}
