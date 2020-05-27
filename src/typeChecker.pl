main_stdin :-
read(user_input,T),
typeCheck(T, R),
print(R),
nl,
exitCode(R).
/*Fonction qui permet de recup le type des arguments*/
typeArgs([],[]).
typeArgs([(_,Type)|Rest],[Type | Result]):- 
        typeArgs(Rest,Result).

typeCheck(P, ok) :- typeProgram(P).

typeProgram(prog(Cmds)):-
        context_init(Context1),
        typeCommands(Context1, Cmds, void), !.
        
typeCheck(_, ko).

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

typeExpr(_, Val, int) :- integer(Val),!.
/*typeExpr(_, Val, int) :- !.*/
typeExpr(_, true, bool).
typeExpr(_, false, bool).


/*typeExpr(G, val, T) :- env_get(G, val, T).*/



typeExpr(G, if(E1, E2, E3), T):-typeExpr(G, E1, bool), typeExpr(G, E2, T), typeExpr(G, E3, T).

typeExpr(G, not(E1), bool) :- typeExpr(G,E1, bool).
typeExpr(G, and(E1, E2), bool) :- typeExpr(G,E1, bool), typeExpr(G,E2, bool).
typeExpr(G, or(E1, E2), bool) :- typeExpr(G,E1, bool), typeExpr(G,E2, bool).

typeExpr(G, lt(E1,E2), bool) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, eq(E1,E2), bool) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, add(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, sub(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, mul(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).
typeExpr(G, div(E1,E2), int) :- typeExpr(G,E1, int), typeExpr(G,E2, int).




addEnv(G, [], G).
addEnv(G1, L, G2) :- append(G1, L, G2).


exitCode(ok) :- halt(0).
exitCode(_) :- halt(1).
