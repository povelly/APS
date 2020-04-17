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
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
