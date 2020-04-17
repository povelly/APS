package aps1;

import java.util.List;

import interfaces.IASTblock;
import interfaces.IASTcommand;
import interfaces.IASTvisitor;

public class ASTblock implements IASTblock {
	
	private final List<IASTcommand> commands;
	
	public ASTblock(List<IASTcommand> commands) {
		this.commands = commands;
	}
	
	public List<IASTcommand> getCommands() {
		return this.commands;
	}

	@Override
	public <Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env env) throws Exception {
		return visitor.visit(this, env);
	}

}
