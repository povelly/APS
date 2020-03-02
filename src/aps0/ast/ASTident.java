package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTident implements IASTexpression {
	
	private final String name;

	public ASTident(String name) {
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