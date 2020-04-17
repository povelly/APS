package aps0;

import java.util.ArrayList;

import interfaces.IASTexpression;
import interfaces.IASTvisitor;
import interfaces.IFun;
import interpreter.ExpressionEvaluator;

public class ASTlambda implements IASTexpression, IFun {

	protected final ArrayList<ASTarg> args;
	protected final IASTexpression expr;

	public ASTlambda(ArrayList<ASTarg> args, IASTexpression expr) {
		this.args = args;
		this.expr = expr;
	}

	public ArrayList<ASTarg> getArgs() {
		return this.args;
	}

	public IASTexpression getExpr() {
		return this.expr;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}
	
	@Override
	public <Result, Env> Result accept(ExpressionEvaluator<Result, Env> visitor, Env env)
			throws Exception {
		return visitor.visit(this, env);
	}

}
