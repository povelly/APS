LEX_J = ./jflex
YACC_J = ./yacc.linux -J -Jpackage=parser 
JAVAC = javac -sourcepath ../

all: runCompiler runInterpreter
	
runCompiler: parser RunCompiler.java
	$(JAVAC) RunCompiler.java

runInterpreter: parser RunInterpreter.java
	$(JAVAC) RunInterpreter.java

parser: lexer.lex parser.y
	$(LEX_J) lexer.lex
	$(YACC_J) parser.y

clean:
	rm Parser*.java
	rm Yylex.java
	rm *.class
