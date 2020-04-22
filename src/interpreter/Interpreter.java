package interpreter;

import aps0.ASTarg;
import aps0.ASTboolean;
import aps0.ASTclosure;
import aps0.ASTconst;
import aps0.ASTecho;
import aps0.ASTfun;
import aps0.ASTfunRec;
import aps0.ASTident;
import aps0.ASTif;
import aps0.ASTlambda;
import aps0.ASTnum;
import aps0.ASToperation;
import aps0.ASTtypes;
import aps1.ASTblock;
import aps1.ASTcall;
import aps1.ASTifBlock;
import aps1.ASTproc;
import aps1.ASTprocRec;
import aps1.ASTset;
import aps1.ASTvar;
import aps1.ASTwhile;
import exceptions.ArityException;
import exceptions.TypeException;
import interfaces.IASTcommand;
import interfaces.IASTprogram;
import interfaces.IASTvisitor;
import interfaces.IFun;

public class Interpreter implements IASTvisitor<Object, Context> {

	private final Context globalVars = new Context();
	private final IntegerEvaluator integerEvaluator = new IntegerEvaluator(this);
	private final BooleanEvaluator booleanEvaluator = new BooleanEvaluator(this);
	private final PartialEvaluator partialEvaluator = new PartialEvaluator(this);

	@Override
	public Object visit(IASTprogram node, Context context) throws Exception {
		for (IASTcommand command : node.getCommands())
			command.accept(this, context);
		return null;
	}

	@Override
	public Object visit(ASTecho node, Context context) throws Exception {
		System.out.println(node.getExpr().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTconst node, Context context) throws Exception {
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
		if (node.getCondition().accept(booleanEvaluator, context))
			return node.getConsequence().accept(this, context);
		return node.getAlternant().accept(this, context);
	}

	@Override
	public Object visit(ASToperation node, Context context) throws Exception {
		switch (node.getOperator()) {
		case ADD:
			return node.getLeftOperand().accept(integerEvaluator, context) + node.getRightOperand().accept(integerEvaluator, context);
		case SUB:
			return node.getLeftOperand().accept(integerEvaluator, context) - node.getRightOperand().accept(integerEvaluator, context);
		case MUL:
			return node.getLeftOperand().accept(integerEvaluator, context) * node.getRightOperand().accept(integerEvaluator, context);
		case DIV:
			return node.getLeftOperand().accept(integerEvaluator, context) / node.getRightOperand().accept(integerEvaluator, context);
		case AND:
			return node.getLeftOperand().accept(booleanEvaluator, context) && node.getRightOperand().accept(booleanEvaluator, context);
		case OR:
			return node.getLeftOperand().accept(booleanEvaluator, context) || node.getRightOperand().accept(booleanEvaluator, context);
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
			return node.getLeftOperand().accept(booleanEvaluator, context).equals(node.getRightOperand().accept(booleanEvaluator, context));
		case LT:
			return node.getLeftOperand().accept(integerEvaluator, context) < node.getRightOperand().accept(integerEvaluator, context);
		case NOT:
			return !node.getLeftOperand().accept(booleanEvaluator, context);
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
		IFun f = null;
		Context context2 = context.clone();

		if (node.getExpr() instanceof ASTident) {
			ASTident fname = (ASTident) node.getExpr();
			f = (IFun) fname.accept(this, context);
			if (f instanceof ASTfunRec)
				context2.extend(fname, f);
		} else if (node.getExpr() instanceof ASTlambda) {
			f = (IFun) node.getExpr();
		} else if (node.getExpr() instanceof ASTclosure) {
			ASTclosure partialClosure = (ASTclosure) node.getExpr().accept(this.partialEvaluator, context2);	
			f = (IFun) partialClosure.accept(this, context2); 
		}

		if (node.getArguments().size() != f.getArgs().size())
			throw new ArityException(f);
		TypeChecker tc = new TypeChecker(this, context);
		for (int i = 0; i < f.getArgs().size(); i++) {
			if (!node.getArguments().get(i).accept(tc, f.getArgs().get(i).getTypes()))
				throw new TypeException();
			// System.out.println("typecheck: " + node.getArguments().get(i).accept(tc, f.getArgs().get(i).getTypes()));
			
			
			// System.out.println("def:"+f.getArgs().get(i).getTypes().toString());
			// System.out.println("call:"+node.getArguments().get(i).toString());
			context2.extend(f.getArgs().get(i).getName(), node.getArguments().get(i).accept(this, context));
		}
		return f.getExpr().accept(this, context2);
	}

	@Override
	public Object visit(ASTtypes node, Context context) throws Exception {
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
		if (node.getCondition().accept(booleanEvaluator, context))
			return node.getConsequence().accept(this, context);
		return node.getAlternant().accept(this, context);
	}

	@Override
	public Object visit(ASTset node, Context context) throws Exception {
		context.set(node.getVar(), node.getValue().accept(this, context));
		return null;
	}

	@Override
	public Object visit(ASTwhile node, Context context) throws Exception {
		while (node.getCondition().accept(booleanEvaluator, context))
			node.getCorps().accept(this, context);
		return null;
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
		// TODO faux, car les parametres doivent etre retirés du context après le call
		ASTproc p = (ASTproc) context.get(node.getProc());
		if (p == null)
			return null;
		Context context2 = context.clone();
		if (p instanceof ASTprocRec)
			context2.extend(node.getProc(), p);
		if (node.getArgs().size() != p.getArgs().size())
			throw new ArityException(p);
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
