package aps0.ast;

import aps0.interfaces.IASTtype;

public class ASTfunctionType implements IASTtype {

	private final ASTtypes argumentTypes;
	private final IASTtype returnType;

	public ASTfunctionType(ASTtypes argumentTypes, IASTtype returnType) {
		this.argumentTypes = argumentTypes;
		this.returnType = returnType;
	}

	public ASTtypes getArgumentTypes() {
		return this.argumentTypes;
	}

	public IASTtype getReturnType() {
		return this.returnType;
	}

	@Override
	public String toPrologString() {
		return "type(" + argumentTypes.toPrologString() + "->" + returnType.toPrologString() + ")";
	}

}
