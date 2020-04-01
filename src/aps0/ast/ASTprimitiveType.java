package aps0.ast;

import aps0.interfaces.IASTtype;

public enum ASTprimitiveType implements IASTtype {
	
	INTEGER("int"), BOOLEAN("boolean");
	private String name;

	ASTprimitiveType(String name) {
		this.name = name;
	}
	
	@Override
	public String asString() {
		return this.name;
	}

}
