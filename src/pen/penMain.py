import sys
from antlr4 import *
from penLexer import penLexer
from penParser import penParser
from interpreter import interpreter

def main(argv):
   visitor0 = interpreter()
   input_stream = StdinStream()
   lexer = penLexer(input_stream)
   stream = CommonTokenStream(lexer)
   parser = penParser(stream)
   tree = parser.pen()
   if parser.getNumberOfSyntaxErrors() == 0:
      visitor0.visit(tree)

if __name__ == '__main__':
   main(sys.argv)
