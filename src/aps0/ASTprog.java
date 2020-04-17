package aps0;

import java.util.List;

import interfaces.IASTcommand;
import interfaces.IASTprogram;
import interfaces.IASTvisitor;

public class ASTprog implements IASTprogram {

	private final List<IASTcommand> commands;

	public ASTprog(List<IASTcommand> commands) {
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