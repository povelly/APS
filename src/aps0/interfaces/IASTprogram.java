package aps0.interfaces;

import java.util.List;

/**
 * Implements par IASTprog
 */
public interface IASTprogram extends IASTnode {
	
	public List<IASTcommand> getCommands();
	
}
