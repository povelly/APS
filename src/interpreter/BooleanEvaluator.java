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

public class BooleanEvaluator implements ExpressionEvaluator<Boolean, Context> {
	
	private final IASTvisitor<Object, Context> v;

	public BooleanEvaluator(IASTvisitor<Object, Context> v) {
		this.v = v;
	}

	@Override
	public Boolean visit(ASTboolean node, Context context) throws Exception {
		return node.getValue();
	}

	@Override
	public Boolean visit(ASTnum node, Context context) throws Exception {
		return node.getValue() == 0 ? false : true;
	}

	@Override
	public Boolean visit(ASTident node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result == 0 ? false : true;
		if (result instanceof Boolean)
			return (boolean) result;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

	@Override
	public Boolean visit(ASTif node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result == 0 ? false : true;
		if (result instanceof Boolean)
			return (boolean) result;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

	@Override
	public Boolean visit(ASToperation node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result == 0 ? false : true;
		if (result instanceof Boolean)
			return (boolean) result;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

	@Override
	public Boolean visit(ASTlambda node, Context context) throws Exception {
		return true;
	}

	@Override
	public Boolean visit(ASTclosure node, Context context) throws Exception {
		Object result = node.accept(this.v, context);
		if (result instanceof Integer)
			return (int) result == 0 ? false : true;
		if (result instanceof Boolean)
			return (boolean) result;
		if (result instanceof IASTexpression)
			return ((IASTexpression) result).accept(this, context);
		throw new Exception();
	}

}
