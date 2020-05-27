package aps0;

import interfaces.IASTargument;
import interfaces.IASTvisitor;

public class ASTarg implements IASTargument {

	private final ASTident name;
	private final ASTtypes type;

	public ASTarg(ASTident name, ASTtypes types) {
		this.name = name;
		this.type = types;
	}

	public ASTident getName() {
		return this.name;
	}

	public ASTtypes getType() {
		return this.type;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
