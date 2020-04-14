package aps1.ast;

import java.util.List;

import aps0.ast.ASTarg;
import aps0.ast.ASTident;
import aps0.interfaces.IASTvisitor;

public class ASTprocRec extends ASTproc {

	public ASTprocRec(ASTident name, List<ASTarg> args, ASTblock block) {
		super(name, args, block);
	}
	
	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
