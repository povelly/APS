LEX_J = ./jflex
YACC_J = ./yacc.linux -J -Jpackage=parser 
JAVAC = javac -sourcepath ../

prologTerm: parser PrologTerm.java
	$(JAVAC) PrologTerm.java

parser: lexer.lex parser.y
	$(LEX_J) lexer.lex
	$(YACC_J) parser.y

clean:
	rm Parser*.java
	rm Yylex.java
	rm *.class
