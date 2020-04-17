package interfaces;

public interface IASTvisitable {

	<Result, Env> Result accept(IASTvisitor<Result, Env> visitor, Env data) throws Exception;

}
