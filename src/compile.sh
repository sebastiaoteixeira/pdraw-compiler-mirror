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

echo $main $auxilary

cd compiler

antlr4-clean
antlr4-build
antlr4-run < ../$main


mkdir -p ../$destiny_path

mv output.py ../$destiny_path

cp ../pen/*.py ../$destiny_path

