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
	public <Result, Env, Err extends Exception> 
    Result accept(IASTvisitor<Result, Env, Err> visitor, Env data)
            throws Err {
        return visitor.visit(this, data);
    }

}