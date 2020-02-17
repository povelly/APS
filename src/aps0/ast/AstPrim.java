package aps0.ast;

import java.util.ArrayList;

import aps0.interfaces.IASTexpression;

public class AstPrim implements IASTexpression {
	Operator op;
	ArrayList<IASTexpression> opands;

	public AstPrim(Operator op, ArrayList<IASTexpression> es) {
		this.op = op;

		this.opands = es;
	}

	public String toPrologString() {
		String r = "";
		r = op.toString() + "([";
		for (int i = 0; i < opands.size() - 1; i++)
			r += opands.get(i).toPrologString() + ",";
		r += opands.get(opands.size() - 1).toPrologString();
		r += "])";
		return r;
	}
	
	//expression
}