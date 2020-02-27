package aps0.ast;

import aps0.interfaces.IASTexpression;

public class ASTif implements IASTexpression {

	private final IASTexpression condition;
	private final IASTexpression consequence;
	private final IASTexpression alternant;

	public ASTif(IASTexpression condition, IASTexpression consequence, IASTexpression alternant) {
		this.condition = condition;
		this.consequence = consequence;
		this.alternant = alternant;
	}

	public IASTexpression getCondition() {
		return this.condition;
	}

	public IASTexpression getConsequence() {
		return this.consequence;
	}

	public IASTexpression getAlternant() {
		return this.alternant;
	}

//	@Override
//	public String toPrologString() {
//		return "if (" + condition.toPrologString() + ") {\n" + consequence.toPrologString() + "\n} else {\n"
//				+ alternant.toPrologString() + "\n}";
//	}
	
	@Override
	public String toPrologString() {
		return "if(" + condition.toPrologString() + ", " + consequence.toPrologString() + ", " + alternant.toPrologString() + ")";
	}

}
