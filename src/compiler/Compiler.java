package compiler;

import java.util.List;

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
import aps0.Operator;
import aps1.ASTblock;
import aps1.ASTcall;
import aps1.ASTifBlock;
import aps1.ASTproc;
import aps1.ASTprocRec;
import aps1.ASTset;
import aps1.ASTvar;
import aps1.ASTwhile;
import interfaces.IASTcommand;
import interfaces.IASTexpression;
import interfaces.IASTprogram;
import interfaces.IASTvisitor;

public class Compiler implements IASTvisitor<String, Void> {

	@Override
	public String visit(IASTprogram node, Void context) throws Exception {
		List<IASTcommand> commands = node.getCommands();
		String program = "[";
		for (int i = 0; i < commands.size() - 1; i++) {
			program += commands.get(i).accept(this, context) + ", \n";
		}
		program += commands.get(commands.size() - 1).accept(this, context) + "].";
		return program;
	}

	@Override
	public String visit(ASTecho node, Void context) throws Exception {
		return "echo(" + node.getExpr().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTconst node, Void context) throws Exception {
		return "const(" + node.getName().getName() + ", " + node.getType().accept(this, context) + ", "
				+ node.getExpr().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTfun node, Void context) throws Exception {
		List<ASTarg> args = node.getArgs();
		String fun = "funDef(" + node.getName().getName() + ", " + node.getType().accept(this, context)
				+ ", [";
		for (int i = 0; i < args.size() - 1; i++)
			fun += args.get(i).accept(this, context) + ", ";
		fun += args.get(args.size() - 1).accept(this, context) + "], " + node.getExpr().accept(this, context) + ")";
		return fun;
	}

	@Override
	public String visit(ASTfunRec node, Void context) throws Exception {
		List<ASTarg> args = node.getArgs();
		String funRec = "funRecDef(" + node.getName().getName() + ", "
				+ node.getType().accept(this, context) + ", [";
		for (int i = 0; i < args.size() - 1; i++)
			funRec += args.get(i).accept(this, context) + ", ";
		funRec += args.get(args.size() - 1).accept(this, context) + "], " + node.getExpr().accept(this, context) + ")";
		return funRec;
	}

	@Override
	public String visit(ASTarg node, Void context) throws Exception {
		return "(" + node.getName().getName() + ", " + node.getType().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTboolean node, Void context) throws Exception {
		return ((Boolean) node.getValue()).toString();
	}

	@Override
	public String visit(ASTnum node, Void context) throws Exception {
		return node.getValue().toString();
	}

	@Override
	public String visit(ASTident node, Void context) throws Exception {
		return "var(" + node.getName() + ")";
	}

	@Override
	public String visit(ASTif node, Void context) throws Exception {
		return "if(" + node.getCondition().accept(this, context) + ", " + node.getConsequence().accept(this, context)
				+ ", " + node.getAlternant().accept(this, context) + ")";
	}

	@Override
	public String visit(ASToperation node, Void context) throws Exception {
		if (node.getOperator() == Operator.NOT)
			return node.getOperator().getName() + "(" + node.getLeftOperand().accept(this, context) + ")";
		return node.getOperator().getName() + "(" + node.getLeftOperand().accept(this, context) + ", "
				+ node.getRightOperand().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTlambda node, Void context) throws Exception {
		List<ASTarg> args = node.getArgs();
		String lambda = "lambda([";
		for (int i = 0; i < args.size() - 1; i++)
			lambda += args.get(i).accept(this, context) + ", ";
		lambda += args.get(args.size() - 1).accept(this, context) + "], " + node.getExpr().accept(this, context) + ")";
		return lambda;
	}
	
	@Override
	public String visit(ASTclosure node, Void context) throws Exception {
		List<IASTexpression> exprs = node.getArgs();
		String s = "application(" + node.getExpr().accept(this, context) + ", [";
		for (int i = 0; i < exprs.size() - 1; i++)
			s += exprs.get(i).accept(this, context) + ", ";
		s += exprs.get(exprs.size() - 1).accept(this, context) + "])";
		return s;
	}

	@Override
	public String visit(ASTtypes node, Void context) throws Exception {
		String types = "";
		ASTtypes currentType = node;
		while (currentType != null) {
			types += currentType.getType().toString();
//			types += currentType.getType().accept(this, context); TODO
//			asString à définir dans ASTfunctionType
			if (currentType.getNext() != null)
				types += " * ";
			currentType = currentType.getNext();
		}
		return types;
	}

	@Override
	public String visit(ASTblock node, Void context) throws Exception {
		List<IASTcommand> commands = node.getCommands();
		String block = "block([";
		for (int i = 0; i < commands.size() - 1; i++) {
			block += commands.get(i).accept(this, context) + ", ";
		}
		block += commands.get(commands.size() - 1).accept(this, context) + "])";
		return block;
	}
	
	@Override
	public String visit(ASTvar var, Void context) throws Exception {
		return "var(" + var.getVar().getName() + ", " + var.getType().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTifBlock node, Void context) throws Exception {
		return "ifBlock(" + node.getCondition().accept(this, context) + ", " + node.getConsequence().accept(this, context)
				+ ", " + node.getAlternant().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTset node, Void context) throws Exception {
		return "set(var(" + node.getVar().getName() + "), " + node.getValue().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTwhile node, Void context) throws Exception {
		return "while(" + node.getCondition().accept(this, context) + ", " + node.getBody().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTproc node, Void context) throws Exception {
		List<ASTarg> args = node.getArgs();
		String proc = "proc(" + node.getName().getName() + ", [";
		for (int i = 0; i < args.size() - 1; i++)
			proc += args.get(i).accept(this, context) + ", ";
		proc += args.get(args.size() - 1).accept(this, context) + "], " + node.getBlock().accept(this, context) + ")";
		return proc;
	}

	@Override
	public String visit(ASTprocRec node, Void context) throws Exception {
		List<ASTarg> args = node.getArgs();
		String proc = "procRec(" + node.getName().accept(this, context) + ", [";
		for (int i = 0; i < args.size(); i++)
			proc += args.get(i).accept(this, context) + ", ";
		proc += args.get(args.size() - 1).accept(this, context) + "], " + node.getBlock().accept(this, context) + ")";
		return proc;
	}

	@Override
	public String visit(ASTcall node, Void context) throws Exception {
		List<IASTexpression> args = node.getArgs();
		String call = "call(var(" + node.getProc().getName() + "), [";
		for (int i = 0; i < args.size() - 1; i++)
			call += args.get(i).accept(this, context) + ", ";
		call += args.get(args.size() - 1).accept(this, context) + "])";
		return call;
	}
	
}
