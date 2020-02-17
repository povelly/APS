package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTboolean implements IASTexpression {
	
	private final boolean value;
	
	public ASTboolean(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	@Override
	public String toPrologString() {
		return ((Boolean) value).toString();
	}

}
