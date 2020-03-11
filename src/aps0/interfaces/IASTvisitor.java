package aps0.interfaces;

import aps0.ast.ASTapplication;
import aps0.ast.ASTarg;
import aps0.ast.ASTboolean;
import aps0.ast.ASTconst;
import aps0.ast.ASTecho;
import aps0.ast.ASTfun;
import aps0.ast.ASTfunRec;
import aps0.ast.ASTident;
import aps0.ast.ASTif;
import aps0.ast.ASTlambda;
import aps0.ast.ASTnum;
import aps0.ast.ASToperation;
import aps0.ast.ASTtypes;

public interface IASTvisitor<Result, Env, Err extends Exception> {
	
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
	public Result visit(ASTapplication node, Env context) throws Err;
	
	// OTHER NODES
	public Result visit(IASTtype node, Env context) throws Err;
	public Result visit(ASTtypes node, Env context) throws Err;

}
