package interpreter;

import aps0.ast.ASTarg;
import aps0.ast.ASTboolean;
import aps0.ast.ASTclosure;
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
import aps1.ast.ASTcall;
import aps1.ast.ASTifBlock;
import aps1.ast.ASTproc;
import aps1.ast.ASTprocRec;
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
		Context ctx = new Context(null, null, null);
		for (IASTcommand command : node.getCommands())
			command.accept(this, ctx);
		return null;
	}

	@Override
	public Object visit(ASTecho node, Context context) throws Exception {
		System.out.println(node.getExpr().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTconst node, Context context) throws Exception { // TODO ça marche mais bof
		if (node.getExpr() instanceof ASTlambda) {
			this.globalVars.extend(node.getName(), node.getExpr().accept(this, context));
			return null;
		}
		this.globalVars.extend(node.getName(), node.getExpr().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTfun node, Context context) throws Exception {
		context.extend(node.getName(), node);
		return null;
	}

	@Override
	public Object visit(ASTfunRec node, Context context) throws Exception {
		context.extend(node.getName(), node);
		return null;
	}

	@Override
	public Object visit(ASTarg node, Context context) throws Exception {
		return context.get(node.getName());
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
		if (context.contains(node))
			return context.get(node);
		return globalVars.get(node);
	}

	@Override
	public Object visit(ASTif node, Context context) throws Exception {
		if (this.evaluateAsBoolean(node.getCondition(), context)) {
			return node.getConsequence().accept(this, context);
		}
		return node.getAlternant().accept(this, context);
	}

	@Override
	public Object visit(ASToperation node, Context context) throws Exception { // TODO fixer les types (call evalAsBool)
		switch (node.getOperator()) {
		case ADD:
			return this.evaluateAsInteger(node.getLeftOperand(), context)
					+ this.evaluateAsInteger(node.getRightOperand(), context);
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
			Object left = node.getLeftOperand().accept(this, context);
			Object right = node.getRightOperand().accept(this, context);
			if (left == right)
				return true;
			if (left == null || right == null)
				return false;
			if (left instanceof Integer && right instanceof Integer)
				return (int) left == (int) right;
			else if (left instanceof Boolean && right instanceof Boolean)
				return (boolean) left == (boolean) right;
			return this.evaluateAsBoolean((IASTexpression) node.getLeftOperand(), context) == this
					.evaluateAsBoolean((IASTexpression) node.getRightOperand(), context);
		case LT:
			return this.evaluateAsInteger(node.getLeftOperand(), context) < this
					.evaluateAsInteger(node.getRightOperand(), context);
		case NOT:
			return !((boolean) node.getLeftOperand().accept(this, context));
		default:
			return null;
		}
	}

	@Override
	public Object visit(ASTlambda node, Context context) throws Exception {
		return node;
	}

	@Override
	public Object visit(ASTclosure node, Context context) throws Exception {
		if (!(node.getExpr() instanceof ASTident)) // TODO traiter les autres cas
			return null;
		ASTident fname = (ASTident) node.getExpr();
		ASTfun f = (ASTfun) fname.accept(this, context);
		// ASTfun f = (ASTfun) node.getExpr().accept(this, context); // leve une
		// exception si le cast est impossible
		Context context2 = context.clone();
		if (f instanceof ASTfunRec)
			context2.extend(fname, f);
		if (node.getArguments().size() != f.getArgs().size()) // TODO exception/assert wrong arity
			return false;
		for (int i = 0; i < f.getArgs().size(); i++) {
			// TODO test des types
			context2.extend(f.getArgs().get(i).getName(), node.getArguments().get(i).accept(this, context));
		}
		return f.getExpr().accept(this, context2);
	}

	// @Override
	// public Object visit(ASTclosure node, Context context) throws Exception {
	// if (!(node.getExpr() instanceof ASTident)) // TODO traiter les autres cas
	// return null;
	// ASTident fname = (ASTident) node.getExpr();
	// if (!(context.getValue(fname) instanceof ASTfun)) // TODO exception/assert
	// not a function
	// return null;
	// Context context2 = context.clone();
	// ASTfun f = (ASTfun) context.getValue(fname);
	// if (f instanceof ASTfunRec)
	// context2.extend(fname, f);
	// if (node.getArguments().size() != f.getArgs().size()) // TODO
	// exception/assert wrong arity
	// return false;
	// for (int i = 0; i < f.getArgs().size(); i++) {
	// // TODO test des types
	// context2.extend(f.getArgs().get(i).getName(),
	// node.getArguments().get(i).accept(this, context));
	// }
	// return f.getExpr().accept(this, context2);
	// }

	@Override
	public Object visit(ASTtypes node, Context context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ASTblock node, Context context) throws Exception {
		for (IASTcommand command : node.getCommands())
			command.accept(this, context);
		return null;
	}

	@Override
	public Object visit(ASTvar var, Context context) throws Exception {
		context.extend(var.getVar(), null);
		return null;
	}

	@Override
	public Object visit(ASTifBlock node, Context context) throws Exception {
		if (this.evaluateAsBoolean(node.getCondition(), context)) {
			return node.getConsequence().accept(this, context);
		}
		return node.getAlternant().accept(this, context);
	}

	@Override
	public Object visit(ASTset node, Context context) throws Exception {
		context.set(node.getVar(), node.getValue().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTwhile node, Context context) throws Exception {
		while (this.evaluateAsBoolean(node.getCondition(), context)) {
			node.getCorps().accept(this, context);
		}
		return null;
	}

	private int evaluateAsInteger(IASTexpression expr, Context context) { // TODO retirer les -1 et gerer les exceptions
		if (expr instanceof ASTnum)
			return ((ASTnum) expr).getVal();
		if (expr instanceof ASTboolean)
			return ((ASTboolean) expr).getValue() ? 1 : 0;
		if (expr instanceof ASTident)
			try {
				Object res = ((ASTident) expr).accept(this, context);
				if (res instanceof Integer)
					return (int) res;
				if (res instanceof Boolean)
					return (boolean) res ? 1 : 0;
				return this.evaluateAsInteger((ASTident) expr.accept(this, context), context);
			} catch (Exception e1) {
				e1.printStackTrace();
				return -1;
			}
//		if (expr instanceof ASTident)
//			return ((ASTident) expr).getString().equals("") ? 0 : 1;
		if (expr instanceof ASTif)
			try {
				return this.evaluateAsInteger((IASTexpression) ((ASTif) expr).accept(this, context), context);
			} catch (Exception e) {
				return -1;
			}
		if (expr instanceof ASToperation)
			try {
				Object result = ((ASToperation) expr).accept(this, context);
				if (result instanceof Integer)
					return (int) result;
				if (result instanceof Boolean)
					return (boolean) result ? 1 : 0;
				return -1;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		if (expr instanceof ASTlambda)
			try {
				return this.evaluateAsInteger((IASTexpression) ((ASTlambda) expr).accept(this, context), context);
			} catch (Exception e) {
				return -1;
			}
		if (expr instanceof ASTclosure)
			try {
				return this.evaluateAsInteger((IASTexpression) ((ASTclosure) expr).accept(this, context), context);
			} catch (Exception e) {
				return -1;
			}
		return -1;
	}

	private boolean evaluateAsBoolean(IASTexpression expr, Context context) { // TODO faire des verifs
		if (expr instanceof ASTnum)
			return ((ASTnum) expr).getVal() == 0 ? false : true;
		if (expr instanceof ASTboolean)
			return ((ASTboolean) expr).getValue();
		if (expr instanceof ASTident) { // TODO verif ici
			try {
				return (boolean) expr.accept(this, context);
			} catch (Exception e) {
				return false;
			}
		}
		if (expr instanceof ASTif)
			try {
				return this.evaluateAsBoolean((IASTexpression) ((ASTif) expr).accept(this, context), context);
			} catch (Exception e) {
				return false;
			}
		if (expr instanceof ASToperation)
			try {
				Object result = ((ASToperation) expr).accept(this, context);
				if (result instanceof Integer)
					return (int) result == 0 ? false : true;
				if (result instanceof Boolean)
					return (boolean) result;
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		if (expr instanceof ASTlambda)
			try {
				return this.evaluateAsBoolean((IASTexpression) ((ASTlambda) expr).accept(this, context), context);
			} catch (Exception e) {
				return false;
			}
		if (expr instanceof ASTclosure)
			try {
				return this.evaluateAsBoolean((IASTexpression) ((ASTclosure) expr).accept(this, context), context);
			} catch (Exception e) {
				return false;
			}
		return false;
	}

	@Override
	public Object visit(ASTproc node, Context context) throws Exception {
		context.extend(node.getName(), node);
		return null;
	}

	@Override
	public Object visit(ASTprocRec node, Context context) throws Exception {
		context.extend(node.getName(), node);
		return null;
	}

	@Override
	public Object visit(ASTcall node, Context context) throws Exception {
		ASTproc p = (ASTproc) context.get(node.getProc());
		if (p == null)
			return null;
		Context context2 = context.clone();
		if (p instanceof ASTprocRec)
			context2.extend(node.getProc(), p);
		if (node.getArgs().size() != p.getArgs().size()) // TODO exception/assert wrong arity
			return false;
		for (int i = 0; i < p.getArgs().size(); i++) {
			// TODO test des types
			context2.extend(p.getArgs().get(i).getName(), node.getArgs().get(i).accept(this, context));
		}
		p.getBlock().accept(this, context2);
		if (!(p instanceof ASTprocRec))
			context2.remove(node.getProc());
		context.replace(context2);
		return null;
	}

}
