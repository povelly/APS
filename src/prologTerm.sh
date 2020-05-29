#!/bin/bash

JAVA="java"
MAIN_CLASS="parser/RunCompiler"
SAMPLES_DIR="../samples"

if test $# -lt 1
then
    echo "Usage 1 : bash prologTerm.sh aps0"
    echo "Usage 2 : bash prologTerm.sh aps0/prog000.aps"
    echo "Usage 3 : bash prologTerm.sh aps0/prog000.aps aps0/prog001.aps"
    exit
fi

rm $SAMPLES_DIR/*.pl

if [[ "$1" == aps0 ]]
then
    echo "Running all tests"
    for arg in $SAMPLES_DIR/aps0/*.aps
    do
        echo -e "\n$arg :"
        $JAVA $MAIN_CLASS $arg
        $JAVA $MAIN_CLASS $arg > "$arg.pl"
    done
elif [[ "$1" == aps1 ]]
then
    echo "Running all tests"
    for arg in $SAMPLES_DIR/aps1/*.aps
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