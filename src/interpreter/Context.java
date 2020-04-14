package interpreter;

import java.util.Iterator;

import aps0.ASTident;
import exceptions.UnboundVariableException;

public class Context implements Iterable<Context> {

	private class ContextIterator implements Iterator<Context> {

		private Context ctx;

		public ContextIterator() {
			this.ctx = Context.this;
		}

		@Override
		public boolean hasNext() {
			return this.ctx != null && this.ctx.variable != null;
		}

		@Override
		public Context next() {
			Context current = this.ctx;
			this.ctx = this.ctx.next;
			return current;
		}

	}

	private ASTident variable;
	private Object value;
	private Context next;

	public Context(ASTident variable, Object value, Context next) {
		this.variable = variable;
		this.value = value;
		this.next = next;
	}

//	public static void main(String[] args) {
//		Context ctx = new Context(null, null, null);
//		ctx.extend(new ASTident("var1"), 1);
//		ctx.extend(new ASTident("var2"), 2);
//		ctx.extend(new ASTident("var3"), 3);
//		ctx.extend(new ASTident("var4"), 4);
//		ctx.extend(new ASTident("var3"), -1);
//
//		ctx.remove(new ASTident("var3"));
//
//		for (Context c : ctx)
//			System.out.println(c.variable.getString() + " : " + c.value);
//	}
	
	public boolean contains(ASTident variable) {
		if (this.variable == null)
			return false;
		if (this.variable.equals(variable))
			return true;
		return this.next != null && this.next.contains(variable);
	}
	
	public Object get(ASTident variable) throws Exception {
		if (this.variable != null && this.variable.equals(variable))
			return this.value;
		if (this.next != null)
			return this.next.get(variable);
		throw new UnboundVariableException(variable);
	}
	
	public void set(ASTident variable, Object value) throws Exception {
		if (this.variable != null && this.variable.equals(variable))
			this.value = value;
		else if (this.next != null)
			this.next.set(variable, value);
		else
			throw new UnboundVariableException(variable);
	}

	public void extend(ASTident variable, Object value) {
		if (this.variable == null || this.variable.equals(variable)) {
			this.variable = variable;
			this.value = value;
		} else if (this.next == null)
			this.next = new Context(variable, value, null);
		else {
			this.next.extend(variable, value);
		}

	}

	public void remove(ASTident variable) {
		if (this.variable != null && this.variable.equals(variable)) {
			if (this.next == null)
				return;
			this.variable = this.next.variable;
			this.value = this.next.value;
			this.next = this.next.next;
		} else if (this.next != null && this.next.variable.equals(variable)) {
			this.next = this.next.next;
		} else if (this.next != null) {
			this.next.remove(variable);
		}

	}

	public void replace(Context ctx) {
		Context ctx2 = ctx.clone();
		this.variable = ctx2.variable;
		this.value = ctx2.value;
		this.next = ctx2.next;
	}

	@Override
	public Context clone() {
		if (this.next == null)
			return new Context(this.variable, this.value, null);
		return new Context(this.variable, this.value, this.next.clone());
	}

	@Override
	public Iterator<Context> iterator() {
		return new ContextIterator();
	}

	public int size() {
		if (this.variable == null)
			return 0;
		if (this.next == null)
			return 1;
		return 1 + this.next.size();
	}

	public void display() {
		System.out.println("=============");
		System.out.println("Ctx : (@:" + this.toString() + " size:" + this.size() + ")");
		for (Context ctx : this) {
			if (ctx.value != null)
				System.out.println(ctx.variable.getString() + ": " + ctx.value);
			else
				System.out.println(ctx.variable.getString() + ": " + "null");
		}
		System.out.println("=============");
	}

}
