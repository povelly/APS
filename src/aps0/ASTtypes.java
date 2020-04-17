package aps0;

import interfaces.IASTnode;
import interfaces.IASTtype;
import interfaces.IASTvisitable;
import interfaces.IASTvisitor;

public class ASTtypes implements IASTnode, IASTvisitable {
	
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
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}
	
	@Override
	public String toString() {
		String types = "";
		ASTtypes t = this;
		while (t != null) {
			types += t.type.asString();
			if (t.next != null)
				types += " * ";
			t = t.next;
		}
		return types;
	}
	
}
