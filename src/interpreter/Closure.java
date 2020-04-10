package interpreter;

import java.util.List;

import aps0.ast.ASTident;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTvisitor;

public class Closure implements IASTexpression {
	
	private final ASTident fun;
	private final List<IASTexpression> arguments;
	
	public Closure(ASTident fun, List<IASTexpression> arguments) {
		this.fun = fun;
		this.arguments = arguments;
	}
	
	public ASTident getFun() {
		return this.fun;
	}
	
	public List<IASTexpression> getArguments() {
		return this.arguments;
	}

//	private final IFun fun;
//	private final Context lexenv;
//
//	public Closure(IFun fun, Context lexenv) {
//		this.fun = fun;
//		this.lexenv = lexenv;
//	}
//
//	public Context getLexenv() {
//		return this.lexenv;
//	}
//
//	public IFun getFun() {
//		return this.fun;
//	}

	@Override
	public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env env)
			throws Err {
		return visitor.visit(this, env);
	}

}
