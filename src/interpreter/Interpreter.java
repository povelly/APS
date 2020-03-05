package interpreter;

import aps0.ast.ASTapplication;
import aps0.ast.ASTarg;
import aps0.ast.ASTboolean;
import aps0.ast.ASTconst;
import aps0.ast.ASTecho;
import aps0.ast.ASTident;
import aps0.ast.ASTif;
import aps0.ast.ASTnum;
import aps0.ast.ASToperation;
import aps0.ast.ASTtypes;
import aps0.ast.AbstractFun;
import aps0.ast.AbstractNamedFun;
import aps0.interfaces.IASTcommand;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTprogram;
import aps0.interfaces.IASTtype;
import aps0.interfaces.IASTvisitor;

public class Interpreter implements IASTvisitor<Object, Context, Exception> {

	private Context globalVars;

	public Interpreter(Context context) {
		this.globalVars = context;
	}

	@Override
	public Object visit(IASTprogram node, Context context) throws Exception {
		for (IASTcommand command : node.getCommands()) {
			command.accept(this, context);
		}
		return null;
	}

	@Override
	public Object visit(IASTcommand node, Context context) throws Exception {
		return node.accept(this, context);
	}

	@Override
	public Object visit(ASTapplication node, Context context) throws Exception { // TODO
		return null;
	}

	@Override
	public Object visit(ASTarg node, Context context) throws Exception {
		return context.getValue(node.getName());
	}

	@Override
	public Object visit(ASTboolean node, Context context) throws Exception {
		return node.getValue();
	}

	@Override
	public Object visit(ASTconst node, Context context) throws Exception {
		globalVars = globalVars.extend(node.getName(), node.getExpr().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTecho node, Context context) throws Exception {
		System.out.println(node.getExpr().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTident node, Context context) throws Exception {
		if (context.contains(node))
			return context.getValue(node);
		return globalVars.getValue(node);
	}

	@Override
	public Object visit(ASTif node, Context context) throws Exception {
		if ((boolean) node.getCondition().accept(this, context))
			return node.getConsequence().accept(this, context);
		return node.getAlternant().accept(this, context);
	}

	@Override
	public Object visit(ASTnum node, Context context) throws Exception {
		return node.getVal();
	}

	@Override
	public Object visit(ASToperation node, Context context) throws Exception {

		switch (node.getOperator()) {
		case ADD:
			return (int) node.getLeftOperand().accept(this, context)
					+ (int) node.getRightOperand().accept(this, context);
		case SUB:
			return (int) node.getLeftOperand().accept(this, context)
					- (int) node.getRightOperand().accept(this, context);
		case MUL:
			return (int) node.getLeftOperand().accept(this, context)
					* (int) node.getRightOperand().accept(this, context);
		case DIV:
			return (int) node.getLeftOperand().accept(this, context)
					/ (int) node.getRightOperand().accept(this, context);
		case AND:
			return (boolean) node.getLeftOperand().accept(this, context)
					&& (boolean) node.getRightOperand().accept(this, context);
		case OR:
			return (boolean) node.getLeftOperand().accept(this, context)
					|| (boolean) node.getRightOperand().accept(this, context);
		case EQ:
			return (int) node.getLeftOperand().accept(this, context) == (int) node.getRightOperand().accept(this,
					context);
		case LT:
			return (int) node.getLeftOperand().accept(this, context) < (int) node.getRightOperand().accept(this,
					context);
		case NOT:
			return !((boolean) node.getLeftOperand().accept(this, context));
		default:
			return null;
		}
	}

	@Override
	public Object visit(AbstractFun node, Context context) throws Exception {
		Closure closure = new Closure(node.getArgs(), (IASTexpression) node.getExpr().accept(this, context));
		if (node instanceof AbstractNamedFun)
			this.globalVars.extend(((AbstractNamedFun) node).getName(), closure);
		else
			this.globalVars.extend(null, closure);
		return 1;

	}

	@Override
	public Object visit(IASTtype node, Context context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTtypes node, Context context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
