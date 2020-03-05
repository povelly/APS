package aps0.interfaces;

import aps0.ast.ASTapplication;
import aps0.ast.ASTarg;
import aps0.ast.ASTboolean;
import aps0.ast.ASTconst;
import aps0.ast.ASTecho;
import aps0.ast.ASTident;
import aps0.ast.ASTif;
import aps0.ast.ASTnum;
import aps0.ast.ASToperation;
import aps0.ast.ASTtypes;
import aps0.ast.AbstractFun;

public interface IASTvisitor<Result, Env, Err extends Exception> {
	
	public Result visit(IASTprogram node, Env context) throws Err;
	public Result visit(IASTcommand node, Env context) throws Err;
	
	public Result visit(AbstractFun node, Env context) throws Err;

	public Result visit(ASTapplication node, Env context) throws Err;	
	public Result visit(ASTarg node, Env context) throws Err;
	public Result visit(ASTboolean node, Env context) throws Err;
	public Result visit(ASTconst node, Env context) throws Err;
	public Result visit(ASTecho node, Env context) throws Err;
	public Result visit(ASTident node, Env context) throws Err;
	public Result visit(ASTif node, Env context) throws Err;
	public Result visit(ASTnum node, Env context) throws Err;
	public Result visit(ASToperation node, Env context) throws Err;
	public Result visit(IASTtype node, Env context) throws Err;
	public Result visit(ASTtypes node, Env context) throws Err;

}
