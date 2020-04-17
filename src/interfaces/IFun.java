package interfaces;

import java.util.ArrayList;

import aps0.ASTarg;

public interface IFun extends IASTvisitable {

	public ArrayList<ASTarg> getArgs();

	public IASTexpression getExpr();

	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env data) throws Exception;

}
