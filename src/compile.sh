#!/bin/bash

declare auxilary

while getopts ":m:a:o:" opt; do
    case $opt in
        m)
            main=$OPTARG
            ;;
        a)
            auxilary=$OPTARG
            ;;

        o)
            destiny_path=$OPTARG
            ;;
    esac
done 

if [ -z "$main" ]; then
    echo "Main file not specified"
    exit 1
fi

if [ ! -f $main ]; then
    echo "Main file not found"
    exit 1
fi


# remove all files in destiny path
rm -rf $destiny_path/*

mkdir -p $destiny_path

# copy auxilary files to destiny path
if [ -n "$auxilary" ]; then
    cp $auxilary $destiny_path
fi

cd pen
antlr4-build -python

cd ../compiler
antlr4-build
java pdrawMain ../$main


mv output.py ../$destiny_path

cp ../pen/*.py ../$destiny_path

