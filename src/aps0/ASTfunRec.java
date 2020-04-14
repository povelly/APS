package aps0;

import java.util.ArrayList;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interfaces.IFun;

public class ASTfunRec extends ASTfun implements IFun {
	
	public ASTfunRec(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		super(name, types, args, expr);
	}
	
	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
