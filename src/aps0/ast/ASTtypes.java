package aps0.ast;

import aps0.interfaces.IASTnode;
import aps0.interfaces.IASTtype;
import aps0.interfaces.IASTvisitor;

public class ASTtypes implements IASTnode {
	
	private final IASTtype type;
	private final ASTtypes next;
	
	public ASTtypes(IASTtype type) {
		this.type = type;
		this.next = null;
	}
	
	public ASTtypes(IASTtype type, ASTtypes next) {
		this.type = type;
		this.next = next;
	}
	
	public IASTtype getType() {
		return this.type;
	}
	
	public ASTtypes getNext() {
		return this.next;
	}

	@Override
	public String toPrologString() {
		String types = "";
		ASTtypes currentType = this;
		while (currentType != null) {
			types += currentType.getType().toPrologString();
			if (currentType.getNext() != null)
				types += " * ";
			currentType = currentType.getNext();
		}
		return types;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}
	
}
