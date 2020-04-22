package interpreter;

import java.util.ArrayList;
import java.util.List;

import aps0.ASTboolean;
import aps0.ASTclosure;
import aps0.ASTfun;
import aps0.ASTident;
import aps0.ASTif;
import aps0.ASTlambda;
import aps0.ASTnum;
import aps0.ASToperation;
import exceptions.ArityException;
import exceptions.TypeException;
import exceptions.UnboundVariableException;
import interfaces.IASTexpression;
import interfaces.IASTvisitor;

public class PartialEvaluator implements ExpressionEvaluator<IASTexpression, Context> {

	private final IASTvisitor<Object, Context> v;

	public PartialEvaluator(IASTvisitor<Object, Context> v) {
		this.v = v;
	}

	@Override
	public IASTexpression visit(ASTboolean node, Context context) throws Exception {
		return new ASTboolean(node.getValue());
	}

	@Override
	public IASTexpression visit(ASTnum node, Context context) throws Exception {
		return new ASTnum(node.getVal());
	}

	@Override
	public IASTexpression visit(ASTident node, Context context) throws Exception {
		try {
			Object res = node.accept(this.v, context);
			if (res instanceof Integer)
				return new ASTnum((int) res);
			if (res instanceof Boolean)
				return new ASTboolean((boolean) res);
			if (res instanceof ASTfun) {
				ASTfun f = (ASTfun) res;
				return new ASTlambda(f.getArgs(), f.getExpr().accept(this, context));
			}
			return ((IASTexpression) res).accept(this, context);
		} catch (UnboundVariableException e) {
			return new ASTident(node.getString());
		}
	}

	@Override
	public IASTexpression visit(ASTif node, Context context) throws Exception {
		return new ASTif(node.getCondition().accept(this, context), node.getConsequence().accept(this, context),
				node.getAlternant().accept(this, context));
	}

	@Override
	public IASTexpression visit(ASToperation node, Context context) throws Exception {
		return new ASToperation(node.getOperator(), node.getLeftOperand().accept(this, context),
				node.getRightOperand().accept(this, context));
	}

	@Override
	public IASTexpression visit(ASTlambda node, Context context) throws Exception {
		return new ASTlambda(node.getArgs(), node.getExpr().accept(this, context));
	}

	@Override
	public IASTexpression visit(ASTclosure node, Context context) throws Exception {
		List<IASTexpression> arguments = new ArrayList<IASTexpression>();
		for (IASTexpression arg : node.getArguments())
			arguments.add(arg.accept(this, context));
		
		ASTlambda f = (ASTlambda) node.getExpr().accept(this, context);
		if (node.getArguments().size() != f.getArgs().size())
			throw new ArityException(f);
		
		Context context2 = context.clone();
		TypeChecker tc = new TypeChecker(this.v, context);
		for (int i = 0; i < node.getArguments().size(); i++) {
			if (!node.getArguments().get(i).accept(tc, f.getArgs().get(i).getTypes()))
				throw new TypeException();
			context2.extend(f.getArgs().get(i).getName(), node.getArguments().get(i).accept(this, context));
		}
		return new ASTclosure(node.getExpr().accept(this, context2), arguments);
	}

}
