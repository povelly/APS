package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;

public abstract class AbstractNamedFun extends AbstractFun {
	
	protected final ASTident name;
	protected final ASTtypes types;
	
	protected AbstractNamedFun(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		super(args, expr);
		this.name = name;
		this.types = types;
	}
	
	public ASTident getName() {
		return this.name;
	}

	public ASTtypes getTypes() {
		return this.types;
	}

}
