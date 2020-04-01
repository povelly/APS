package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;

public class ASTfunRec extends ASTfun {
	
	public ASTfunRec(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		super(name, types, args, expr);
	}

}
