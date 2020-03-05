package aps0.ast;

import aps0.interfaces.IASTargument;
import aps0.interfaces.IASTvisitor;

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
	public String toPrologString() {
		return "(" + name.toPrologString() + ", " + types.toPrologString() + ")";
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
