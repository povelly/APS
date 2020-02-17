#!/bin/bash
JAVA="java"
MAIN_CLASS="parser/PrologTerm"
SAMPLES_DIR="../samples"

if test $# -lt 1
then
  echo "Usage 1 : bash eval.sh t1.aps t3.aps"
  echo "Usage 2 : bash eval.sh all"
  exit
fi

if [[ "$1" == all ]]
then
	echo "Running all tests"
	for arg in $SAMPLES_DIR/*.aps
	do
	  echo -e "\n$arg :"
	  $JAVA $MAIN_CLASS $arg
	done
else
	for arg in $*
	do
	  echo -e "\n$arg :"
		$JAVA $MAIN_CLASS $SAMPLES_DIR/$arg
	done
fi
