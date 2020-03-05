package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;

public class ASTlambda extends AbstractFun implements IASTexpression {
	
	public ASTlambda(ArrayList<ASTarg> args, IASTexpression expr) {
		super(args, expr);
	}
	
//	@Override
//	public String toPrologString() {
//		String s = "funAbs [";
//		for (ASTarg arg : args)
//			s += arg.toPrologString() +", ";
//		s += "] " + expr.toPrologString();
//		return s;
//	}
	
	@Override
	public String toPrologString() {
		String s = "lambda([";
		for (int i = 0; i < args.size() - 1; i++)
			s += args.get(i).toPrologString() + ", ";
		s += args.get(args.size() - 1).toPrologString() + "], " + expr.toPrologString() + ")";
		return s;
	}

}
