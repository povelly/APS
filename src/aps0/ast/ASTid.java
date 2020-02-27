package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTid implements IASTexpression {
	
	private final String name;

	public ASTid(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}

	@Override
	public String toPrologString() {
		return name;
	}
}