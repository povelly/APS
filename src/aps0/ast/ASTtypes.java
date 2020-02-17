package aps0.ast;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTtype;

public class ASTtypes implements IASTexpression {
	
	private final IASTtype type;
	private final ASTtypes next;
	
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

}
