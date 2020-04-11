package compiler;

import java.util.List;

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
import aps0.ast.Operator;
import aps0.interfaces.IASTcommand;
import aps0.interfaces.IASTexpression;
import aps0.interfaces.IASTprogram;
import aps0.interfaces.IASTvisitor;
import aps1.ast.ASTblock;
import aps1.ast.ASTifBlock;
import aps1.ast.ASTset;
import aps1.ast.ASTvar;
import aps1.ast.ASTwhile;

public class Compiler implements IASTvisitor<String, Void, Exception> {

	public Compiler() {

	}

	@Override
	public String visit(IASTprogram node, Void context) throws Exception {
		List<IASTcommand> commands = node.getCommands();
		String program = "";
		for (int i = 0; i < commands.size() - 1; i++) {
			program += commands.get(i).accept(this, context) + ";\n";
		}
		program += commands.get(commands.size() - 1).accept(this, context) + ".";
		return program;
	}

	@Override
	public String visit(ASTecho node, Void context) throws Exception {
		return node.getExpr().accept(this, context);
	}

	@Override
	public String visit(ASTconst node, Void context) throws Exception {
		return "const(" + node.getName().getString() + ", " + node.getTypes().accept(this, context) + ", "
				+ node.getExpr().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTfun node, Void context) throws Exception {
		List<ASTarg> args = node.getArgs();
		String fun = "funDef(" + node.getName().getString() + ", " + node.getTypes().accept(this, context)
				+ ", [";
		for (int i = 0; i < args.size() - 1; i++)
			fun += args.get(i).accept(this, context) + ", ";
		fun += args.get(args.size() - 1).accept(this, context) + "], " + node.getExpr().accept(this, context) + ")";
		return fun;
	}

	@Override
	public String visit(ASTfunRec node, Void context) throws Exception {
		List<ASTarg> args = node.getArgs();
		String funRec = "funRecDef(" + node.getName().getString() + ", "
				+ node.getTypes().accept(this, context) + ", [";
		for (int i = 0; i < args.size() - 1; i++)
			funRec += args.get(i).accept(this, context) + ", ";
		funRec += args.get(args.size() - 1).accept(this, context) + "], " + node.getExpr().accept(this, context) + ")";
		return funRec;
	}

	@Override
	public String visit(ASTarg node, Void context) throws Exception {
		return "(" + node.getName().getString() + ", " + node.getTypes().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTboolean node, Void context) throws Exception {
		return ((Boolean) node.getValue()).toString();
	}

	@Override
	public String visit(ASTnum node, Void context) throws Exception {
		return node.getVal().toString();
	}

	@Override
	public String visit(ASTident node, Void context) throws Exception {
		return node.getString();
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
		List<IASTexpression> exprs = node.getArguments();
		String s = "application(" + node.getExpr().accept(this, context) + ", (";
		for (int i = 0; i < exprs.size() - 1; i++)
			s += exprs.get(i).accept(this, context) + ", ";
		s += exprs.get(exprs.size() - 1).accept(this, context) + "))";
		return s;
	}

	@Override
	public String visit(ASTtypes node, Void context) throws Exception {
		String types = "";
		ASTtypes currentType = node;
		while (currentType != null) {
			types += currentType.getType().asString();
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
		String block = "block(";
		for (int i = 0; i < commands.size() - 1; i++) {
			block += commands.get(i).accept(this, context) + "; ";
		}
		block += commands.get(commands.size() - 1).accept(this, context) + ")";
		return block;
	}
	
	@Override
	public String visit(ASTvar var, Void context) throws Exception {
		return "var(" + var.getVar().getString() + ", " + var.getType().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTifBlock node, Void context) throws Exception {
		return "ifBlock(" + node.getCondition().accept(this, context) + ", " + node.getConsequence().accept(this, context)
				+ ", " + node.getAlternant().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTset node, Void context) throws Exception {
		return "set(" + node.getVar().getString() + ", " + node.getValue().accept(this, context) + ")";
	}

	@Override
	public String visit(ASTwhile node, Void context) throws Exception {
		return "while(" + node.getCondition().accept(this, context) + ", " + node.getCorps().accept(this, context) + ")";
	}
	
}
