package interpreter;

import aps0.ASTboolean;
import aps0.ASTclosure;
import aps0.ASTfun;
import aps0.ASTident;
import aps0.ASTif;
import aps0.ASTlambda;
import aps0.ASTnum;
import aps0.ASToperation;
import aps0.ASTprimitiveType;
import aps0.ASTtypes;
import interfaces.IASTexpression;
import interfaces.IASTvisitor;

public class TypeChecker implements ExpressionEvaluator<Boolean, ASTtypes> {
	
	private IASTvisitor<Object, Context> v;
	private Context ctx;
	
	public TypeChecker(IASTvisitor<Object, Context> v, Context ctx) {
		this.v = v;
		this.ctx = ctx;
	}

	@Override
	public Boolean visit(ASTboolean node, ASTtypes type) throws Exception {
		return type.getType().equals(ASTprimitiveType.BOOLEAN);
	}

	@Override
	public Boolean visit(ASTnum node, ASTtypes type) throws Exception {
		return type.getType().equals(ASTprimitiveType.INTEGER);
	}

	@Override
	public Boolean visit(ASTident node, ASTtypes type) throws Exception {
		Object res = node.accept(v, ctx);
		if (res instanceof Integer)
			return type.getType().equals(ASTprimitiveType.INTEGER);
		if (res instanceof Boolean)
			return type.getType().equals(ASTprimitiveType.BOOLEAN);
		if (res instanceof ASTfun) {
			ASTfun f = (ASTfun) res;
			res = new ASTlambda(f.getArgs(), f.getExpr());
		}
		return ((IASTexpression) res).accept(this, type);
	}

	@Override
	public Boolean visit(ASTif node, ASTtypes type) throws Exception {
		Object res = node.accept(v, ctx);
		if (res instanceof Integer)
			return type.getType().equals(ASTprimitiveType.INTEGER);
		if (res instanceof Boolean)
			return type.getType().equals(ASTprimitiveType.BOOLEAN);
		return ((IASTexpression) res).accept(this, type);
	}

	@Override
	public Boolean visit(ASToperation node, ASTtypes type) throws Exception {
		Object res = node.accept(v, ctx);
		if (res instanceof Integer)
			return type.getType().equals(ASTprimitiveType.INTEGER);
		if (res instanceof Boolean)
			return type.getType().equals(ASTprimitiveType.BOOLEAN);
		return ((IASTexpression) res).accept(this, type);
	}

	@Override
	public Boolean visit(ASTlambda node, ASTtypes type) throws Exception { // TODO
		System.out.println("LAMBDA TO DO");
		return true;
		
	}

	@Override
	public Boolean visit(ASTclosure node, ASTtypes type) throws Exception {
		Object res = node.accept(v, ctx);
		if (res instanceof Integer)
			return type.getType().equals(ASTprimitiveType.INTEGER);
		if (res instanceof Boolean)
			return type.getType().equals(ASTprimitiveType.BOOLEAN);
		if (res instanceof ASTfun) {
			ASTfun f = (ASTfun) res;
			res = new ASTlambda(f.getArgs(), f.getExpr());
		}
		return ((IASTexpression) res).accept(this, type);
	}

}
