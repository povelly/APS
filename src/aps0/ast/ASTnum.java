package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTnum implements IASTexpression {
	
	private final Integer val;

	public ASTnum(Integer val) {
		this.val = val;
	}
	
	public Integer getVal() {
		return this.val;
	}

//	@Override
//	public String toPrologString() {
//		return ("num(" + val + ")");
//	}
	
	@Override
	public String toPrologString() {
		return val + "";
	}
	
	
}