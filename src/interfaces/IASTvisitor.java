package interfaces;

import aps0.ASTarg;
import aps0.ASTboolean;
import aps0.ASTclosure;
import aps0.ASTconst;
import aps0.ASTecho;
import aps0.ASTfun;
import aps0.ASTfunRec;
import aps0.ASTident;
import aps0.ASTif;
import aps0.ASTlambda;
import aps0.ASTnum;
import aps0.ASToperation;
import aps0.ASTtypes;
import aps1.ASTblock;
import aps1.ASTcall;
import aps1.ASTifBlock;
import aps1.ASTproc;
import aps1.ASTprocRec;
import aps1.ASTset;
import aps1.ASTvar;
import aps1.ASTwhile;

public interface IASTvisitor<Result, Env, Err extends Exception> {
	
	
	/**
	 * APS 0
	 */
	
	// PROGRAM
	public Result visit(IASTprogram node, Env context) throws Err;
	
	// COMMAND
	public Result visit(ASTecho node, Env context) throws Err;
	
	// DECLARATION
	public Result visit(ASTconst node, Env context) throws Err;
	public Result visit(ASTfun node, Env context) throws Err;
	public Result visit(ASTfunRec node, Env context) throws Err;

	// ARGUMENT
	public Result visit(ASTarg node, Env context) throws Err;
	
	// EXPRESSION
	public Result visit(ASTboolean node, Env context) throws Err;
	public Result visit(ASTnum node, Env context) throws Err;
	public Result visit(ASTident node, Env context) throws Err;
	public Result visit(ASTif node, Env context) throws Err;
	public Result visit(ASToperation node, Env context) throws Err;
	public Result visit(ASTlambda node, Env context) throws Err;
	public Result visit(ASTclosure node, Env context) throws Err;
	
	// OTHER NODES
//	public Result visit(IASTtype node, Env context) throws Err;
	public Result visit(ASTtypes node, Env context) throws Err;
	
	
	/**
	 * APS 1
	 */
	
	// COMMAND
	public Result visit(ASTblock node, Env context) throws Err;
	public Result visit(ASTvar var, Env context) throws Err;
	public Result visit(ASTifBlock node, Env context) throws Err;
	public Result visit(ASTset node, Env context) throws Err;
	public Result visit(ASTwhile node, Env context) throws Err;
	public Result visit(ASTproc node, Env context) throws Err;
	public Result visit(ASTprocRec node, Env context) throws Err;
	public Result visit(ASTcall node, Env context) throws Err;

}
