package interfaces;

import java.util.ArrayList;

import aps0.ASTarg;

public interface IFun extends IASTvisitable {

	public ArrayList<ASTarg> getArgs();

	public IASTexpression getExpr();

	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err;

}
