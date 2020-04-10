package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;
import aps0.interfaces.IFun;

public class ASTfunRec extends ASTfun implements IFun {
	
	public ASTfunRec(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		super(name, types, args, expr);
	}

}
