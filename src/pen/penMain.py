import sys
from antlr4 import *
from penLexer import penLexer
from penParser import penParser
from interpreter import Interpreter
from semanticVerifier import SemanticVerifier
from pen import Pen, Point
from canvas import Canvas

def exec(filename: str, pen: Pen):
    input_stream = FileStream(filename, encoding="utf-8")
    lexer = penLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = penParser(stream)
    tree = parser.program()
    if parser.getNumberOfSyntaxErrors() == 0:
        semantic = SemanticVerifier()
        semantic.visit(tree)
        visitor = Interpreter(pen)
        visitor.visit(tree)

def main(argv):
    canvas = Canvas("Drawing Canvas", 400, 400)
    pen = Pen(canvas)
    visitor = Interpreter(pen)
    input_stream = StdinStream()
    lexer = penLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = penParser(stream)
    tree = parser.program()
    if parser.getNumberOfSyntaxErrors() == 0:
        visitor.visit(tree)

if __name__ == '__main__':
      canvas = Canvas("Drawing Canvas", 400, 400)
      
      penType = {"position": Point(200, 200), "color": "green", "orientation": 45, "thickness": 10, "pressure": 1}
      pen = Pen(canvas, penType)
   # main(sys.argv)
      exec("p1.ipdraw", pen)
      canvas.waitUntilClose()

