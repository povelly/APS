package aps0;

import java.util.ArrayList;

import interfaces.IASTexpression;
import interfaces.IFun;

public class ASTfunRec extends ASTfun implements IFun {
	
	public ASTfunRec(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		super(name, types, args, expr);
	}

}
