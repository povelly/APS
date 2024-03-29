package aps0;

import interfaces.IASTtype;

public enum ASTprimitiveType implements IASTtype {
	
	INTEGER("int"), BOOLEAN("bool"), VOID("void");
	private String name;

	ASTprimitiveType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
