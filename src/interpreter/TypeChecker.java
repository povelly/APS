package interpreter;

import aps0.ASTboolean;
import aps0.ASTclosure;
import aps0.ASTfun;
import aps0.ASTfunctionType;
import aps0.ASTident;
import aps0.ASTif;
import aps0.ASTlambda;
import aps0.ASTnum;
import aps0.ASToperation;
import aps0.ASTprimitiveType;
import aps0.ASTtypes;
import aps1.ASTproc;
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
		if (res instanceof ASTproc)
			return type.getType().equals(ASTprimitiveType.VOID);
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
	public Boolean visit(ASTlambda node, ASTtypes type) throws Exception {
		if (!(type.getType() instanceof ASTfunctionType))
			return false;
		ASTfunctionType t = (ASTfunctionType) type.getType();

		ASTtypes c = t.getArgumentTypes();
		int index = 0;
		while (c != null) {
			if (!node.getArgs().get(index++).getType().toString().equals(c.toString())) // TODO moche
				return false;
			c = c.getNext();
		}

		Object res = node.accept(v, ctx);
		if (res instanceof Integer)
			return t.getReturnType().equals(ASTprimitiveType.INTEGER);
		if (res instanceof Boolean)
			return t.getReturnType().equals(ASTprimitiveType.BOOLEAN);
		
		// TODO test du type de retour
		// if (res instanceof ASTfun) {
		// ASTfun f = (ASTfun) res;
		// res = new ASTlambda(f.getArgs(), f.getExpr());
		// }
		// return ((IASTexpression) res).accept(this, type);
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
