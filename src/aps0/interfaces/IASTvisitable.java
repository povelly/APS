package aps0.interfaces;

public interface IASTvisitable {

	<Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env data) throws Err;

}
