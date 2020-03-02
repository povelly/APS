package aps0.interfaces;

/**
 * Extends par IASTcommand, IASTexpression et IASTtype
 * Implements par ASTtypes (par contre c'est faux en théorie)
 */
public interface IASTnode {
	public String toPrologString();
}
