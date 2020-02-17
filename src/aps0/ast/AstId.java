package aps0.ast;

import aps0.interfaces.IASTexpression;

public class AstId implements IASTexpression {
	String name;

	public AstId(String x) {
		name = x;
	}

	@Override
	public String toPrologString() {
		return "var(" + name + ")";
	}
}