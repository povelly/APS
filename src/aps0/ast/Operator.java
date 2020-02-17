package aps0.ast;

public enum Operator {

	ADD("add"), SUB("sub"), MUL("mul"), DIV("div");
	private String name;

	Operator(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}