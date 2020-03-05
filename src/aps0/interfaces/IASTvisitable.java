package aps0.interfaces;

public interface IASTvisitable {

	// public <Result, Env, Err extends Exception> Result accept(IASTvisitor<Result,
	// Env, Err> visitor, Env env) throws Err;

	<Result, Env, Err extends Exception> Result accept(IASTvisitor<Result, Env, Err> visitor, Env data) throws Err;

}
