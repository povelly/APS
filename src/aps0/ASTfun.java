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
	protected final ASTtypes type;

	public ASTfun(ASTident name, ASTtypes type, ArrayList<ASTarg> args, IASTexpression expr) {
		this.args = args;
		this.expr = expr;
		this.name = name;
		this.type = type;
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

	public ASTtypes getType() {
		return this.type;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
