package aps0.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

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
		return val.toString();
	}
	
	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}
	
}