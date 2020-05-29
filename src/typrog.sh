#!/bin/bash

JAVA="java -cp"
PROLOG="swipl -s typeChecker.pl -g main_stdin"
MAIN_CLASS="../bin parser.RunCompiler"
SAMPLES_DIR="../samples"

if test $# -lt 1
then
  echo "Usage 1 : bash typrog.sh aps0"
  echo "Usage 2 : bash typrog.sh aps0/prog000.aps"
  echo "Usage 3 : bash typrog.sh aps0/prog000.aps aps0/prog001.aps"
  exit
fi

if [[ "$1" == aps0 ]]
then
    echo "Running all tests"
    for arg in $SAMPLES_DIR/aps0/*.aps
    do
      echo -e "\n$arg :"
      $JAVA $MAIN_CLASS $arg | $PROLOG
    done
elif [[ "$1" == aps1 ]]
then
    echo "Running all tests"
    for arg in $SAMPLES_DIR/aps1/*.aps
    do
      echo -e "\n$arg :"
      $JAVA $MAIN_CLASS $arg | $PROLOG
    done
else
    for arg in $*
    do
      echo -e "\n$arg :"
        $JAVA $MAIN_CLASS $SAMPLES_DIR/$arg | $PROLOG
    done
fi