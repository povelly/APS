package aps0.ast;

import aps0.interfaces.IASTargument;

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

}
