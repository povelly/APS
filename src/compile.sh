#!/bin/bash
JAVA="java"
MAIN_CLASS="parser/PrologTerm"
SAMPLES_DIR="../samples"

if test $# -lt 1
then
  echo "Usage 1 : bash compile.sh t1.aps t3.aps"
  echo "Usage 2 : bash compile.sh all"
  exit
fi

rm $SAMPLES_DIR/*.pl

if [[ "$1" == all ]]
then
	echo "Running all tests"
	for arg in $SAMPLES_DIR/*.aps
	do
	  echo -e "\n$arg :"
	  $JAVA $MAIN_CLASS $arg
	  $JAVA $MAIN_CLASS $arg > "$arg.pl"
	done
else
	for arg in $*
	do
	  echo -e "\n$arg :"
		$JAVA $MAIN_CLASS $SAMPLES_DIR/$arg
		$JAVA $MAIN_CLASS $SAMPLES_DIR/$arg > "$SAMPLES_DIR/$arg.pl"
	done
fi
