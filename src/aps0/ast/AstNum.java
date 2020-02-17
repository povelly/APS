package aps0.ast;

import aps0.interfaces.IASTexpression;

public class AstNum implements IASTexpression {
	Integer val;

	public AstNum(Integer n) {
		val = n;
	}

	@Override
	public String toPrologString() {
		return ("num(" + val + ")");
	}
}