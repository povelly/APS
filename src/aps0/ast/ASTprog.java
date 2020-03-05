package aps0.ast;

import java.util.List;

import aps0.interfaces.IASTcommand;
import aps0.interfaces.IASTprogram;
import aps0.interfaces.IASTvisitor;

public class ASTprog implements IASTprogram {

	private final List<IASTcommand> commands;

	public ASTprog(List<IASTcommand> commands) {
		this.commands = commands;
	}

	public List<IASTcommand> getCommands() {
		return this.commands;
	}

	@Override
	public String toPrologString() {
		String commands = "";
		for (int i = 0; i < this.commands.size() - 1; i++) {
			commands += this.commands.get(i).toPrologString() + ";\n";
		}
		commands += this.commands.get(this.commands.size() - 1).toPrologString() + ".";
		return commands;
	}
	
//	@Override
//	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
//			throws Err {
//		return (Result) ((IASTvisitor)visitor).visit(this, env);
//	}
	
    @Override
	public <Result, Env, Err extends Exception> 
    Result accept(IASTvisitor<Result, Env, Err> visitor, Env data)
            throws Err {
        return visitor.visit(this, data);
    }

}