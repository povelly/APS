package aps0.ast;

import aps0.interfaces.IASTtype;

public enum PrimitiveType implements IASTtype {
	
	INTEGER("int"), BOOLEAN("boolean");
	private String name;

	PrimitiveType(String name) {
		this.name = name;
	}

	@Override
	public String toPrologString() {
		return this.name;
	}

}
