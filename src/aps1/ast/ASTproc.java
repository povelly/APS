package aps1.ast;

import java.util.List;

import aps0.ast.ASTarg;
import aps0.ast.ASTident;
import aps0.interfaces.IASTdeclaration;
import aps0.interfaces.IASTvisitor;

public class ASTproc implements IASTdeclaration {
	
	private final ASTident name;
	private final List<ASTarg> args;
	private final ASTblock block;
	
	public ASTproc(ASTident name, List<ASTarg> args, ASTblock block) {
		this.name = name;
		this.args = args;
		this.block = block;
	}
	
	public ASTident getName() {
		return this.name;
	}
	
	public List<ASTarg> getArgs() {
		return this.args;
	}
	
	public ASTblock getBlock() {
		return this.block;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
