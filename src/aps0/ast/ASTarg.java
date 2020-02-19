package aps0.ast;

import aps0.interfaces.IASTnode;

public class ASTarg implements IASTnode {
	
	private final ASTid name;
	private final ASTtypes types;
	
	public ASTarg(ASTid name, ASTtypes types) {
		this.name = name;
		this.types = types;
	}
	
	public ASTid getName() {
		return this.name;
	}
	
	public ASTtypes getTypes() {
		return this.types;
	}

	@Override
	public String toPrologString() {
		return name.toPrologString() + ":" + types.toPrologString();
	}
	
}
