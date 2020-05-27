main_stdin :-
read(user_input,T),
typeCheck(T, R),
print(R),
nl,
exitCode(R).

typeList(_, [], []).
typeList(G, [E|L], [T|R]):- typeExpr(G, E, T), typeList(G, L, R).

/*
envAdd([], V, V).
envAdd([H|T], V, [H|R]) :- envAdd(T, V, R).

envGet([], _, undefined).
envGet([(K, V)|_], K, V).
envGet([_|T], K, R) :- envGet(T, K, R).
*/

envAdd(E, [], E).
envAdd(E, L, X) :- envAdd(E, L, X), !.


envGet([var(ID, X)|_], ID, X).
envGet([_|T], ID, X) :- envGet(T, ID, X), !.

/*
envGetL(_,[],[]).
envGetL(Env,[H|Q],[T1|T2]):-typeExpr(Env,H, T1),env_getL(Env,Q,T2).
*/


typeCheck(P, ok) :- typeProgram(P).

typeProgram(prog(Cmds)):-
        context_init(Context1),
        typeCommands(Context1, Cmds, void), !.
        
typeCheck(_, ko).

/* BOOL */
typeExpr(_, true, bool).
typeExpr(_, false, bool).

/* NUM */
typeExpr(_, Val, int) :- integer(Val),!.

/* VAR */
typeExpr(G, var(V), R) :- envGet(G, V, R), !.

/* IF */
typeExpr(G, if(E1, E2, E3), R) :- typeExpr(G, E1, bool), typeExpr(G, E2, R), typeExpr(G, E3, R).

/* OP PRIM */
typeExpr(G, not(E1), bool) :- typeExpr(G,E1, bool).
typeExpr(G, and(E1, E2), bool) :- typeExpr(G,E1, bool), typeExpr(G,E2, bool).
typeExpr(G, or(E1, E2), bool) :- typeExpr(G,E1, bool), typeExpr(G,E2, bool).
typeExpr(G, lt(E1,E2), bool) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, eq(E1,E2), bool) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, add(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, sub(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, mul(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, div(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).

/* APP */

/* ABS */

/* ECHO */
typeStat(G, echo(E), void) :- typeExpr(G, E, int), !.

/* CONST */
typeDec(G1, const(V,T,E), G2) :- typeExpr(G1, E, T), envGet([(V, T)], G1, G2), !.

/* FUN */
typeDec(G1, funDef(V, T1, A, E), G2) :- envAdd(A, G1, G3), typeExpr(G3, E, T1), typeArgs(A, T2), envAdd([(V, (T2, T1))], G1, G2), !.

/* FUN REC */


/*Fonction qui permet de recup le type des arguments*/
typeArgs([],[]).
typeArgs([(_,Type)|L], [Type|R]) :- typeArgs(L,R).



typeCommands(Context, [Stat|Cmds], void):-
        typeStat(Context, Stat, void),
        typeCommands(Context, Cmds, void).
typeCommands(Context, [Dec|Cmds], void):-
        typeDec(Context, Dec, New_Context),
        typeCommands(New_Context, Cmds, void).
typeCommands(_, [], void).
    
/* quand ça peut prendre 2 chemin il faut mettre le !*/    
    
    

/*typeProg(P) :- typeCommand(CMDS).*/
/*typeCommand(CMDS) :- typeStat(STAT).
typeCommand(CMDS) :- typeDec(DEC).*/

typeStat(G, echo(E), void) :- typeExpr(G, E, int),!.
typeDec(G1, const(V, T, E), G2) :- typeExpr(G1, E, T), addEnv(G1, [var(V, T, E)], G2). /*Attention au var*/
/*

typeDec(G1, funDef(V, T, Args, E), G3) :- addEnv(G1, Args, G2), typeExpr(G2, E, T),typeArgs(Args,TypeArgs), addEnv(G2, [(V,(TypeArgs,Type))], G3).
typeDec(G1, funRecDef(V, T, Args, E), G4) :- typeArgs(Args,TypeArgs),addEnv(G1, [(V,(TypeArgs, T)) | Args], G2), addEnv(G2, V, G3) typeExpr(G3, E, T), addEnv(G3, [(V,(TypeArgs,T))], G4).

*/




/*typeExpr(G, val, T) :- env_get(G, val, T).*/



exitCode(ok) :- halt(0).
exitCode(_) :- halt(1).
