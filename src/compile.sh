#!/bin/bash

declare auxilary

usage() {
    echo "Usage: $0 [-a auxilary] [-o destiny_path] main"
    echo "  -h: show this help"
    echo "  -a auxilary: add auxilary files to the list [you can use this option multiple times]"
    echo "  -o destiny_path: specify the destiny path"
    echo "  -m main: specify the main output file name"
    exit 1
}

destiny_path="out"
mainoutput="run.py"
# auxiliary is a list of files
auxilary=""

while [ "$OPTIND" -le "$#" ]; do
    if getopts ":a:o:l:m:h" opt; then
        case $opt in
            a)
                ## add auxilary files to the list
                auxilary="$auxilary ${OPTARG}"
                ;;

            o)
                destiny_path=$OPTARG
                ;;
            m)
                mainoutput=$OPTARG
                ;;
            h)
                usage
                ;;
            -*)
                usage
                ;;
        esac
    else
        if [ -z "$main" ]; then
            main="${!OPTIND}"
            ((OPTIND++))
        else
            usage
        fi
    fi
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
for file in $auxilary; do
    cp $file $destiny_path
done

cd pen
antlr4-build -python

cd ../compiler
antlr4-build
if [ -z "$mainoutput" ]; then
    java pdrawMain ../$main
else
    java pdrawMain ../$main -o $mainoutput
fi
errorcode=$?
if [ $errorcode -ne 0 ]; then
    exit $errorcode
fi

mv $mainoutput ../$destiny_path

cp ../pen/*.py ../$destiny_path

