#!/bin/bash

JAVA="java"
MAIN_CLASS="parser/RunInterpreter"
SAMPLES_DIR="../samples"

if test $# -lt 1
then
  echo "Usage 1 : bash evaluate.sh aps0"
  echo "Usage 2 : bash evaluate.sh aps0/prog000.aps"
  echo "Usage 3 : bash evaluate.sh aps0/prog000.aps aps0/prog001.aps"
  exit
fi

if [[ "$1" == aps0 ]]
then
    echo "Running all tests"
    for arg in $SAMPLES_DIR/aps0/*.aps
    do
      echo -e "\n$arg :"
      $JAVA $MAIN_CLASS $arg
    done
elif [[ "$1" == aps1 ]]
then
    echo "Running all tests"
    for arg in $SAMPLES_DIR/aps1/*.aps
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