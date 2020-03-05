package interpreter;

import java.util.ArrayList;
import java.util.List;

import aps0.ast.ASTarg;
import aps0.interfaces.IASTexpression;

public class Closure {
	
	private List<ASTarg> arguments = new ArrayList<>();
	private IASTexpression expression;
	
	public Closure(List<ASTarg> arguments, IASTexpression expression) {
		this.arguments = arguments;
		this.expression = expression;
	}
	
	public List<ASTarg> getArguments() {
		return this.arguments;
	}
	
	public IASTexpression getExpression() {
		return this.expression;
	}
	
}
