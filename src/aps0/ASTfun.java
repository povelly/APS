package aps0;

import java.util.ArrayList;

import interfaces.IASTdeclaration;
import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interfaces.IFun;

public class ASTfun implements IASTdeclaration, IFun {

	protected final ArrayList<ASTarg> args;
	protected final IASTexpression expr;
	protected final ASTident name;
	protected final ASTtypes types;

	public ASTfun(ASTident name, ASTtypes types, ArrayList<ASTarg> args, IASTexpression expr) {
		this.args = args;
		this.expr = expr;
		this.name = name;
		this.types = types;
	}

	public ArrayList<ASTarg> getArgs() {
		return this.args;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	public ASTident getName() {
		return this.name;
	}

	public ASTtypes getTypes() {
		return this.types;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
