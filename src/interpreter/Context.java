package interpreter;

import aps0.ast.ASTident;

public class Context {

	public ASTident variable;
	private Object value;
	private Context next;

	public Context(ASTident variable, Object value, Context next) {
		this.variable = variable;
		this.value = value;
		this.next = next;
	}

	public Context getNext() {
		return this.next;
	}

	public boolean contains(ASTident variable) {
		if (variable.getString().equals(this.variable.getString()))
			return true;
		if (this.next == null)
			return false;
		else
			return this.next.contains(variable);
	}

	public Object getValue(ASTident variable) {
		if (this.variable.getString().equals(variable.getString()))
			return this.value;
		if (this.next == null)
			return null;
		return this.next.getValue(variable);
	}

	public void setValue(ASTident variable, Object value) {
		if (this.variable.getString().equals(variable.getString()))
			this.value = value;
		if (this.next != null)
			this.next.setValue(variable, value);
	}

	public void extend(ASTident variable, Object value) {
		Context ctx = this;
		while (ctx.next != null) {
			ctx = ctx.next;
		}
		ctx.next = new Context(variable, value, null);
	}
	
	@Override
	public Context clone() {
		if (this.next == null)
			return new Context(this.variable, this.value, this.next);
		return new Context(this.variable, this.value, this.next.clone());
	}

}
