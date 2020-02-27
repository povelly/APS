//package aps0.ast;
//
//import java.util.ArrayList;
//
//import aps0.interfaces.IASTnode;
//
//public class PRINT_LIST implements IASTnode {
//
//	private final ArrayList<ASTarg> node;
//
//	public PRINT_LIST(ArrayList<ASTarg> node) {
//		this.node = node;
//	}
//
//	public ArrayList<ASTarg> getNode() {
//		return node;
//	}
//
//	@Override
//	public String toPrologString() {
//		String s = "";
//		for (IASTnode node : node)
//			s += node.toPrologString() + ",";
//		return s;
//	}
//
//}
