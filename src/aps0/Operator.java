package aps0;

public enum Operator {

	ADD("add"),
	SUB("sub"),
	MUL("mul"),
	DIV("div"),
	AND("and"),
	OR("or"),
	EQ("eq"),
	LT("lt"),
	NOT("not");
	
	private String name;

	Operator(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}