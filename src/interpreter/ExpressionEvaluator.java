package interpreter;

import aps0.ASTboolean;
import aps0.ASTclosure;
import aps0.ASTident;
import aps0.ASTif;
import aps0.ASTlambda;
import aps0.ASTnum;
import aps0.ASToperation;

public interface ExpressionEvaluator<Result, Env> {
	
	// EXPRESSION
	public Result visit(ASTboolean node, Env context) throws Exception;
	public Result visit(ASTnum node, Env context) throws Exception;
	public Result visit(ASTident node, Env context) throws Exception;
	public Result visit(ASTif node, Env context) throws Exception;
	public Result visit(ASToperation node, Env context) throws Exception;
	public Result visit(ASTlambda node, Env context) throws Exception;
	public Result visit(ASTclosure node, Env context) throws Exception;

}
