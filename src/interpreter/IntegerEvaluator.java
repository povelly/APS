package interpreter;

import aps0.ASTboolean;
import aps0.ASTclosure;
import aps0.ASTident;
import aps0.ASTif;
import aps0.ASTlambda;
import aps0.ASTnum;
import aps0.ASToperation;
import interfaces.IASTexpression;
import interfaces.IASTvisitor;

public class IntegerEvaluator implements ExpressionEvaluator<Integer, Context> {
	
	private IASTvisitor<Object, Context> v;

	public IntegerEvaluator(IASTvisitor<Object, Context> v) {
		this.v = v;
	}

	@Override
	public Integer visit(ASTboolean node, Context context) throws Exception {
		return node.getValue() ? 1 : 0;
	}

	@Override
	public Integer visit(ASTnum node, Context context) throws Exception {
		return node.getVal();
	}

	@Override
	public Integer visit(ASTident node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result;
		if (result instanceof Boolean)
			return (boolean) result ? 1 : 0;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

	@Override
	public Integer visit(ASTif node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result;
		if (result instanceof Boolean)
			return (boolean) result ? 1 : 0;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

	@Override
	public Integer visit(ASToperation node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result;
		if (result instanceof Boolean)
			return (boolean) result ? 1 : 0;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

	@Override
	public Integer visit(ASTlambda node, Context context) throws Exception {
		return 0;
	}

	@Override
	public Integer visit(ASTclosure node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result;
		if (result instanceof Boolean)
			return (boolean) result ? 1 : 0;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

}
