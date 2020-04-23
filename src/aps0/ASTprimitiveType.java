package aps0;

import interfaces.IASTtype;

public enum ASTprimitiveType implements IASTtype {
	
	INTEGER("int"), BOOLEAN("boolean"), VOID("void");
	private String name;

	ASTprimitiveType(String name) {
		this.name = name;
	}
	
	@Override
	public String asString() {
		return this.name;
	}

}
