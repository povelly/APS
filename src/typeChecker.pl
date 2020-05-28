main_stdin :-
read(user_input,T),
typeCheck(T, R),
print(R),
nl,
exitCode(R).

typeList(_, [], []).
typeList(G, [E|L], [T|R]):- typeExpr(G, E, T), typeList(G, L, R), !.


envAdd([], V, V).
envAdd([H|T], V, [H|R]) :- envAdd(T, V, R), !.

envGet([], _, undefined).
envGet([(K, V)|_], K, V) :- !.
envGet([_|T], K, R) :- envGet(T, K, R), !.

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
typeExpr(G1, application(F, Ex), T1) :- typeList(G1, Ex, T2), typeExpr(G1, F, (T2, T1)), !.

/* ABS */
typeExpr(G1, lambda(A, Ex), (T2, T1)) :- envAdd(G1, A, G2), typeArgs(A, T2), typeExpr(G2, Ex, T1), !.

/* ECHO */
typeStat(G, echo(E), void) :- typeExpr(G, E, int), !.

/* CONST */
typeDec(G1, const(V,T,E), G2) :- typeExpr(G1, E, T), envGet([(V, T)], G1, G2), !.

/* FUN */
typeDec(G1, funDef(V, T1, A, E), G2) :- envAdd(A, G1, G3), typeExpr(G3, E, T1), typeArgs(A, T2), envAdd([(V, (T2, T1))], G1, G2), !.

/* FUN REC */
typeDec(G1, funRecDef(V, T1, A, E), G2) :- typeArgs(A, T2), envAdd(G1, [(T2,T1)|A], G3), typeExpr(G3, E, T1), envAdd([(V, (T2, T1))], G1, G2), !.

/* ARGS */
typeArgs([],[]).
typeArgs([(_,Type)|L], [Type|R]) :- typeArgs(L,R).

/* COMMANDS */
typeCommands(_, [], void) :- !.
typeCommands(G, [S|L], void) :- typeStat(G, S, void), typeCommands(G, L, void), !.
typeCommands(G1, [D|L], void) :- typeDec(G1, D, G2), typeCommands(G2, L, void), !.

/* PROGRAM */
typeProg(P) :- typeCommands([], P, void).

 
    
/* quand ça peut prendre 2 chemin il faut mettre le !*/    
    
    

/*typeProg(P) :- typeCommand(CMDS).*/
/*typeCommand(CMDS) :- typeStat(STAT).
typeCommand(CMDS) :- typeDec(DEC).*/


exitCode(ok) :- halt(0).
exitCode(_) :- halt(1).

/*
LES TRUCS QUI DECONNENT

- Application
- funRec car appli marche pas

*/




/*
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
*/