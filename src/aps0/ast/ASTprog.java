package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTprog implements IASTexpression {

	private final ASTcommands commands;

	public ASTprog(ASTcommands cmd) {
		this.commands = cmd;
	}

	public ASTcommands getCommands() {
		return this.commands;
	}

	@Override
	public String toPrologString() {
		return "[" + commands.toPrologString() + "]";
	}

}
