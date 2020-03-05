//package aps0.ast;
//
//import aps0.interfaces.IASTdeclaration;
//import aps0.interfaces.IASTnode;
//import aps0.interfaces.IASTtype;
//import interpreter.IASTvisitor;
//
//public class PRINT implements IASTnode{
//	
//	private final IASTnode node;
//	
//	public PRINT(IASTtype type) {
//		this.node = new ASTtypes(type);
//	}
//	
//	public PRINT(ASTtypes types) {
//		this.node = types;
//	}
//	
//	public PRINT(IASTdeclaration cst) {
//		this.node = cst;
//	}
//	
//	public IASTnode getNode() {
//		return node;
//	}
//
//	@Override
//	public String toPrologString() {
//		return node.toPrologString();
//	}
//
//}
