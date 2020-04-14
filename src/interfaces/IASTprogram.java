package interfaces;

import java.util.List;

/**
 * Implements par IASTprog
 */
public interface IASTprogram extends IASTnode, IASTvisitable {
	
	public List<IASTcommand> getCommands();
	
}
