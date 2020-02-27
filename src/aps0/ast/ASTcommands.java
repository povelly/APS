package aps0.ast;

import aps0.interfaces.IASTnode;

public class ASTcommands implements IASTnode {

	private final IASTnode expression;
	private final ASTcommands next;

	public ASTcommands(IASTnode expression, ASTcommands next) {
		this.expression = expression;
		this.next = next;
	}

	public IASTnode getExpression() {
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
				commands += ";\n ";
			currentCommand = currentCommand.getNext();
		}
		return commands;
	}

}
