package interfaces;

import interpreter.ExpressionEvaluator;

/**
 * Implements par ASTboolean, ASTnum, ASTid, ASTif, ASToperation, ASTlambda et
 * ASTapplication
 */
public interface IASTexpression extends IASTnode, IASTvisitable {
	
	<Result, Env> Result accept(ExpressionEvaluator<Result, Env> visitor, Env data) throws Exception;
	
}
