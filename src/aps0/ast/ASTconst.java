package aps0.ast;

import aps0.interfaces.IASTdec;
import aps0.interfaces.IASTexpression;

public class ASTconst implements IASTdec {
	
	private final ASTid name;
	private final ASTtypes types;
	private final IASTexpression expr;
	
	public ASTconst(ASTid name, ASTtypes types, IASTexpression expr) {
		this.name = name;
		this.types = types;
		this.expr = expr;
	}
	
	public ASTid getName() {
		return this.name;
	}
	
	public ASTtypes getTypes() {
		return this.types;
	}
	
	public IASTexpression getExpr() {
		return this.expr;
	}
	
	@Override
	public String toPrologString() {
		return "CONST " + name.toPrologString() + ":" + expr.toPrologString();
	}

}
