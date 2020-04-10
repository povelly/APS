package interpreter;

import aps0.interfaces.IFun;

public class Closure {

	private final IFun fun;
	private final Context lexenv;

	public Closure(IFun fun, Context lexenv) {
		this.fun = fun;
		this.lexenv = lexenv;
	}

	public Context getLexenv() {
		return this.lexenv;
	}

	public IFun getFun() {
		return this.fun;
	}

}
