package aps0;

import interfaces.IASTargument;
import interfaces.IASTvisitor;

public class ASTarg implements IASTargument {

	private final ASTident name;
	private final ASTtypes types;

	public ASTarg(ASTident name, ASTtypes types) {
		this.name = name;
		this.types = types;
	}

	public ASTident getName() {
		return this.name;
	}

	public ASTtypes getTypes() {
		return this.types;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
