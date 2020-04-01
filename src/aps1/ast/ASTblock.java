package aps1.ast;

import java.util.List;

import aps0.interfaces.IASTblock;
import aps0.interfaces.IASTcommand;
import aps0.interfaces.IASTvisitor;

public class ASTblock implements IASTblock {
	
	private final List<IASTcommand> commands;
	
	public ASTblock(List<IASTcommand> commands) {
		this.commands = commands;
	}
	
	public List<IASTcommand> getCommands() {
		return this.commands;
	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
