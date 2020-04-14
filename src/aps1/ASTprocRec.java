package aps1;

import java.util.List;

import aps0.ASTarg;
import aps0.ASTident;
import interfaces.IASTvisitor;

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
