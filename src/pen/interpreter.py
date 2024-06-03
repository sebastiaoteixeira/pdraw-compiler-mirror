from antlr4 import *
from penParser import penParser
from penVisitor import penVisitor
from pen import Pen, ColorToInt
from point import Point
from time import sleep
import math


class Interpreter(penVisitor):
   def __init__(self, pen):
      self.pen = pen
      self.variables={}
      self.colors = {
         'white': '#FFFFFF',
         'black': '#000000',
         'grey': '#808080',
         'darkgrey': '#A9A9A9',
         'lightgrey': '#D3D3D3',
         'green': '#008000',
         'lime': '#00FF00',
         'red': '#FF0000',
         'orange': '#FFA500',
         'darkorange': '#FF8C00',
         'blue': '#0000FF',
         'yellow': '#FFFF00',
         'magenta': '#FF00FF',
         'darkmagenta': '#8B008B',
         'lightmagenta': '#FF77FF',
         'cyan': '#00FFFF',
         'darkcyan': '#008B8B',
         'lightcyan': '#E0FFFF',
         'darkblue': '#00008B',
         'darkgreen': '#006400',
         'lightgreen': '#90EE90',
         'darkred': '#8B0000',
         'brown': '#A52A2A',
         'purple': '#800080',
         'pink': '#FFC0CB',
         'deeppink': '#FF1493',
         'lightpink': '#FFB6C1',
         'silver': '#C0C0C0',
         'gold': '#FFD700',
         'goldenrod': '#DAA520',
         'darkgoldenrod': '#B8860B',
         'skyblue': '#87CEEB',
         'lightskyblue': '#87CEFA',
         'deepskyblue': '#00BFFF',
         'violet': '#EE82EE',
         'darkviolet': '#9400D3',
         'lightyellow': '#FFFFE0'
      }
   
   def visitProgram(self, ctx:penParser.ProgramContext):
      return self.visitChildren(ctx)

   def visitDeclaration_element(self, ctx:penParser.Declaration_elementContext):
      return self.visitChildren(ctx)

   def visitDeclaration(self, ctx:penParser.DeclarationContext):
      type_ = ctx.type_.getText()
      for element in ctx.declaration_element():
         if element.ID():
            var_name= element.ID().getText()
            if type_ == 'int':
               self.variables[var_name] = 0
            elif type_ == 'real':
               self.variables[var_name] = 0.0
            elif type_ == 'string':
               self.variables[var_name] = ''
            elif type_ == 'point':
               self.variables[var_name] = Point(0,0)
            elif type_ == 'bool':
               self.variables[var_name] = False
            elif type_ == 'pen':
               self.variables[var_name] = None
         elif element.assign():
            self.visit(element.assign())
      return None

   def visitStatement(self, ctx:penParser.StatementContext):
      return self.visitChildren(ctx)
   
   def visitCompound(self, ctx:penParser.CompoundContext):
      return self.visitChildren(ctx)
   
   def visitBlock(self, ctx:penParser.BlockContext):
      return self.visitChildren(ctx)

   def visitWhile(self, ctx:penParser.WhileContext):
      while self.visit(ctx.expression()):
         self.visit(ctx.statement())
      return None

   def visitUntil(self, ctx:penParser.UntilContext):
      while not self.visit(ctx.expression()):
         self.visit(ctx.statement())
      return None

   def visitFor(self, ctx:penParser.ForContext):
      self.visit(ctx.statement(0))
      while self.visit(ctx.expression(0)):
         self.visit(ctx.statement(1))
         self.visit(ctx.expression(1))
      return None
   
   def visitIf(self, ctx:penParser.IfContext):
      if self.visit(ctx.expression()):
            self.visit(ctx.statement(0))
      else:
         if ctx.statement(1):
            self.visit(ctx.statement(1))
      return None

   def visitPause(self, ctx:penParser.PauseContext):
      sleep(self.visit(ctx.expression()) / 1000000.0)
      return None

   def visitStdout(self, ctx:penParser.StdoutContext):
      print(self.visit(ctx.expression()), end='')
      return None 

   def visitExprToString(self, ctx:penParser.ExprToStringContext):
      return str(self.visit(ctx.expression()))

   def visitExprToBool(self, ctx:penParser.ExprToBoolContext):
      return bool(self.visit(ctx.expression))

   def visitExprToInt(self, ctx:penParser.ExprToIntContext):
      return int(self.visit(ctx.expression))

   def visitExprMultDivMod(self, ctx:penParser.ExprMultDivModContext):
      left = self.visit(ctx.expression(0))
      right = self.visit(ctx.expression(1))
      if ctx.op.text == '*':
         return left * right
      elif ctx.op.text == '/':
         return left / right
      elif ctx.op.text == '//':
         return int(left // right)
      elif ctx.op.text == '\\\\':
         return left % right
      return None
   
   def visitSetProperty(self, ctx:penParser.SetPropertyContext):
      property = ctx.Property().getText()
      value = self.visit(ctx.expression())
      if property == 'color':
         self.pen.color(value)
      elif property == 'pressure':
         self.pen.pressure(value)
      elif property == 'thickness':
         self.pen.thickness(value)
      elif property == 'orientation':
         self.pen.orientation(value)
      elif property == 'position':
         x, y = value[0], value[1]
         self.pen.position(Point(x, y))
      return None

   def visitExprAddSub(self, ctx:penParser.ExprAddSubContext):
      left = self.visit(ctx.expression(0))
      right = self.visit(ctx.expression(1))
      if ctx.op.text == '+':
         return left + right
      elif ctx.op.text == '-':
         return left - right
      return None

   def visitExprInteger(self, ctx:penParser.ExprIntegerContext):
      return int(ctx.Integer().getText())

   def visitExprId(self, ctx:penParser.ExprIdContext):
      var_name = ctx.ID().getText()
      if var_name in self.variables:
         return self.variables[var_name]
      return None

   def visitExprPi(self, ctx:penParser.ExprPiContext):
      return math.pi

   def visitExprString(self, ctx:penParser.ExprStringContext):
      return ctx.String().getText().strip('"')

   def visitExprParent(self, ctx:penParser.ExprParentContext):
      return self.visit(ctx.expression())

   def visitExprComp(self, ctx:penParser.ExprCompContext):
      left = self.visit(ctx.expression(0))
      right = self.visit(ctx.expression(1))
      if ctx.op.text == '<':
         return left < right
      elif ctx.op.text == '>':
         return left > right
      elif ctx.op.text == '<=':
         return left <= right
      elif ctx.op.text == '>=':
         return left >= right
      elif ctx.op.text == '==':
         return left == right
      elif ctx.op.text == '!=':
         return left != right
      return None

   def visitExprPoint(self, ctx:penParser.ExprPointContext):
      return self.visit(ctx.point())

   def visitExprStringConcat(self, ctx:penParser.ExprStringConcatContext):
      left = self.visit(ctx.expression(0))
      right = self.visit(ctx.expression(1))
      return left + right

   def visitExprColor(self, ctx:penParser.ExprColorContext):
      color = ctx.Color().getText()
      if color[0] != '#':
         color = self.colors[color]
      return ColorToInt(color)
      

   def visitExprToReal(self, ctx:penParser.ExprToRealContext):
      return float(self.visit(ctx.expression()))

   def visitPenOperator(self, ctx:penParser.PenOperatorContext):
      distance = self.visit(ctx.expression())
      direction = ctx.op.text
      if direction == 'forward':
         self.pen.forward(distance)
      elif direction == 'backward':
         self.pen.backward(distance)
      elif direction == 'left':
         self.pen.left(distance)
      elif direction == 'right':
         self.pen.right(distance)
      return None

   def visitExprConvToRad(self, ctx:penParser.ExprConvToRadContext):
      if ctx.Integer():
         degrees = int(ctx.Integer().getText())
      elif ctx.Real():
         degrees = float(ctx.Real().getText())
      return math.radians(degrees)

   def visitPenUnary(self, ctx:penParser.PenUnaryContext):
      if ctx.op.text == 'down':
         self.pen.down()
      elif ctx.op.text == 'up':
         self.pen.up()
      return None
      
   def visitExprUnary(self, ctx:penParser.ExprUnaryContext):
      val = self.visit(ctx.expression())
      if ctx.op.text == '-':
         return -val
      elif ctx.op.text == '+':
         return val

   def visitExprBoolOp(self, ctx:penParser.ExprBoolOpContext):
      left = self.visit(ctx.expression(0))
      right = self.visit(ctx.expression(1))
      if ctx.op.text == 'and':
         return left and right
      elif ctx.op.text == 'or':
         return left or right
      return None
   
   def visitExprBoolUnary(self, ctx:penParser.ExprBoolUnaryContext):
      return not self.visit(ctx.expression())

   def visitExprStdin(self, ctx:penParser.ExprStdinContext):
      return input(self.visit(ctx.expression()))

   def visitExprAssign(self, ctx:penParser.ExprAssignContext):
      return self.visit(ctx.assign())

   def visitExprReal(self, ctx:penParser.ExprRealContext):
      return float(ctx.Real().getText())

   def visitExprBool(self, ctx:penParser.ExprBoolContext):
      return ctx.Boolean().getText() == 'true'

   def visitAssign(self, ctx:penParser.AssignContext):
      var_name = ctx.ID().getText()
      value = self.visit(ctx.expression())
      self.variables[var_name] = value
      return value

   def visitPoint(self, ctx:penParser.PointContext):
      return Point(self.visit(ctx.expression(0)), self.visit(ctx.expression(1)))

