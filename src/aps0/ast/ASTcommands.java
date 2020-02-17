package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTcommands implements IASTexpression {

	private final IASTexpression expression;
	private final ASTcommands next;

	public ASTcommands(IASTexpression expression, ASTcommands next) {
		this.expression = expression;
		this.next = next;
	}

	public IASTexpression getExpression() {
		return this.expression;
	}

	public ASTcommands getNext() {
		return this.next;
	}

	@Override
	public String toPrologString() {
		String commands = "";
		ASTcommands currentCommand = this;
		while (currentCommand != null) {
			commands += currentCommand.getExpression().toPrologString();
			if (currentCommand.getNext() != null)
				commands += ",\n ";
			currentCommand = currentCommand.getNext();
		}
		return commands;
	}

}
