package interpreter;

import aps0.ast.ASTapplication;
import aps0.ast.ASTarg;
import aps0.ast.ASTboolean;
import aps0.ast.ASTconst;
import aps0.ast.ASTecho;
import aps0.ast.ASTfun;
import aps0.ast.ASTfunRec;
import aps0.ast.ASTident;
import aps0.ast.ASTif;
import aps0.ast.ASTlambda;
import aps0.ast.ASTnum;
import aps0.ast.ASToperation;
import aps0.ast.ASTtypes;
import aps0.interfaces.IASTcommand;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTprogram;
import aps0.interfaces.IASTvisitor;
import aps1.ast.ASTblock;
import aps1.ast.ASTifBlock;
import aps1.ast.ASTset;
import aps1.ast.ASTvar;
import aps1.ast.ASTwhile;

public class Interpreter implements IASTvisitor<Object, Context, Exception> {

	private Context globalVars;

	public Interpreter(Context context) {
		this.globalVars = context;
	}

	@Override
	public Object visit(IASTprogram node, Context context) throws Exception {
		for (IASTcommand command : node.getCommands())
			command.accept(this, this.globalVars);
		return null;
	}

	@Override
	public Object visit(ASTecho node, Context context) throws Exception {
		System.out.println(node.getExpr().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTconst node, Context context) throws Exception {
		globalVars.extend(node.getName(), node.getExpr().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTfun node, Context context) throws Exception {
		// TODO add parameter
		Context context2 = context.clone();
		Closure closure = new Closure(node, context2);
		context.extend(node.getName(), closure);
		return null;
	}

	@Override
	public Object visit(ASTfunRec node, Context context) throws Exception {
		Context context2 = context.clone();
		context2.extend(node.getName(), node);
		Closure closure = new Closure(node, context2);
		context.extend(node.getName(), closure);
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
	public Object visit(ASTnum node, Context context) throws Exception {
		return node.getVal();
	}

	@Override
	public Object visit(ASTident node, Context context) throws Exception {
		// TODO pas la bonne methode pour ce qui est fonction, faut appeler les args etc
		// -> autre type à visiter
		if (context.contains(node)) {
			Object value = context.getValue(node);
			if (value == null)
				return null;
			if (value instanceof Closure) {
				Closure closure = (Closure) value;
				return closure.getFun().accept(this, closure.getLexenv());
			}
			return value;
		}
		return globalVars.getValue(node);
	}

	@Override
	public Object visit(ASTif node, Context context) throws Exception {
		if ((boolean) node.getCondition().accept(this, context))
			return node.getConsequence().accept(this, context);
		return node.getAlternant().accept(this, context);
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
	public Object visit(ASTlambda node, Context context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTapplication node, Context context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTtypes node, Context context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTblock node, Context context) throws Exception {
		Context local_context = context.clone();
		for (IASTcommand command : node.getCommands())
			command.accept(this, local_context);
		return null;
	}

	@Override
	public Object visit(ASTvar var, Context context) throws Exception {
		context.extend(var.getVar(), null);
		return null;
	}

	@Override
	public Object visit(ASTifBlock node, Context context) throws Exception {
		if ((boolean) node.getCondition().accept(this, context))
			return node.getConsequence().accept(this, context);
		return node.getAlternant().accept(this, context);
	}

	@Override
	public Object visit(ASTset node, Context context) throws Exception {
		if (context.contains(node.getVar()))
			context.setValue(node.getVar(), node.getValue().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTwhile node, Context context) throws Exception {
		while (this.evaluateAsBool(node.getCondition(), context)) {
			node.getCorps().accept(this, context);
		}
		return null;
	}

	public boolean evaluateAsBool(IASTexpression expr, Context context) {
		if (expr instanceof ASTnum)
			return ((ASTnum) expr).getVal() == 0 ? false : true;
		if (expr instanceof ASTboolean)
			return ((ASTboolean) expr).getValue() ? true : false;
		if (expr instanceof ASTident)
			return ((ASTident) expr).getString().equals("") ? false : true;
		if (expr instanceof ASTif)
			try {
				return this.evaluateAsBool((IASTexpression) ((ASTif) expr).accept(this, context), context);
			} catch (Exception e) {
				return false;
			}
		if (expr instanceof ASToperation)
			try {
				return this.evaluateAsBool((IASTexpression) ((ASToperation) expr).accept(this, context), context);
			} catch (Exception e) {
				return false;
			}
		if (expr instanceof ASTlambda)
			try {
				return this.evaluateAsBool((IASTexpression) ((ASTlambda) expr).accept(this, context), context);
			} catch (Exception e) {
				return false;
			}
		if (expr instanceof ASTapplication)
			try {
				return this.evaluateAsBool((IASTexpression) ((ASTlambda) expr).accept(this, context), context);
			} catch (Exception e) {
				return false;
			}
		return false;
	}

}
