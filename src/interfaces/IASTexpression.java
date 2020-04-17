package interfaces;

import interpreter.ExpressionEvaluator;

/**
 * Implements par ASTboolean, ASTnum, ASTid, ASTif, ASToperation, ASTlambda et
 * ASTapplication
 */
public interface IASTexpression extends IASTnode, IASTvisitable {
	
	<Result, Env, Err> Result accept(ExpressionEvaluator<Result, Env, Exception> visitor, Env data) throws Exception;
	
}
