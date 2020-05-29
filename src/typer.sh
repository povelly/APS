java -cp ../bin parser.RunCompiler $1 | swipl -s typeChecker.pl -g main_stdin
