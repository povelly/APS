package interpreter;

import java.util.List;

import aps0.ast.ASTarg;
import aps0.ast.ASTfun;
import aps0.ast.ASTfunRec;
import aps0.ast.ASTlambda;
import aps0.interfaces.IASTexpression;

public class Closure {

	private final List<ASTarg> arguments;
	private final IASTexpression expression;
	private final Context lexenv;

	public Closure(ASTlambda lambda, Context lexenv) {
		this.arguments = lambda.getArgs();
		this.expression = lambda.getExpr();
		this.lexenv = lexenv.clone();
	}

	public Closure(ASTfun fun, Context lexenv) {
		this.arguments = fun.getArgs();
		this.expression = fun.getExpr();
		this.lexenv = lexenv.clone();
	}

	public Closure(ASTfunRec funRec, Context lexenv) {
		this.arguments = funRec.getArgs();
		this.expression = funRec.getExpr();
		this.lexenv = lexenv.clone();
		this.lexenv.extend(funRec.getName(), funRec);
	}

	public List<ASTarg> getArguments() {
		return this.arguments;
	}

	public IASTexpression getExpression() {
		return this.expression;
	}

	public Context getLexenv() {
		return this.lexenv;
	}

}
