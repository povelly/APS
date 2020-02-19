package aps0.ast;

import aps0.interfaces.IASTdec;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTnode;
import aps0.interfaces.IASTtype;

public class PRINT implements IASTexpression {
	
	private final IASTnode node;
	
	public PRINT(IASTtype type) {
		this.node = new ASTtypes(type);
	}
	
	public PRINT(ASTtypes types) {
		this.node = types;
	}
	
	public PRINT(IASTdec cst) {
		this.node = cst;
	}
	
	public IASTnode getNode() {
		return node;
	}

	@Override
	public String toPrologString() {
		return node.toPrologString();
	}

}
