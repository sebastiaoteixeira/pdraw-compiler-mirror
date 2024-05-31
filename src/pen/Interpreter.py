from antlr4 import *
from penParser import penParser
from penVisitor import penVisitor
from pen import Pen, Point
from time import sleep
import math


class Interpreter(penVisitor):
   
   def __init__(self, pen):
      self.pen = pen
      self.variables={}
   
   def visitProgram(self, ctx:penParser.ProgramContext):
      return self.visitChildren(ctx)

   def visitDeclaration_element(self, ctx:penParser.Declaration_elementContext):
      return self.visitChildren(ctx)

   def visitDeclaration(self, ctx:penParser.DeclarationContext):
      type_= ctx.type.text
      for element in ctx.declaration_element:
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

   def visitWhile(self, ctx:penParser.WhileContext):
      while self.visit(ctx.expression()):
         self.visit(ctx.statement())
      return None

   def visitUntil(self, ctx:penParser.UntilContext):
      while not self.visit(ctx.expression()):
         self.visit(ctx.statement())
      return None

   def visitFor(self, ctx:penParser.ForContext):
      self.visit(ctx.declaration())
      while self.visit(ctx.expression(0)):
         self.visit(ctx.statement())
         self.visit(ctx.expression(1))
      return None
   
   def visitIf(self, ctx:penParser.IfContext):
      if self.visit(ctx.expression()):
         self.visit(ctx.statement(0))
      elif ctx.ELSE():
         self.visit(ctx.statement(1))
      return None

   def visitPause(self, ctx:penParser.PauseContext):
      sleep(self.visit(ctx.expression()) / 1000000.0)
      return None

   def visitStdout(self, ctx:penParser.StdoutContext):
      print(self.visit(ctx.expression()))
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
         return left // right
      elif ctx.op.text == '\\\\':
         return left % right
      return None
   
   def visitExprSetProperty(self, ctx:penParser.ExprSetPropertyContext):
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
      x = self.visit(ctx.expression(0))
      y = self.visit(ctx.expression(1))
      return Point(x, y)

   def visitExprStringConcat(self, ctx:penParser.ExprStringConcatContext):
      left = self.visit(ctx.expression(0))
      right = self.visit(ctx.expression(1))
      return left + right

   def visitExprColor(self, ctx:penParser.ExprColorContext):
      return ctx.color.getText()

   def visitExprToReal(self, ctx:penParser.ExprToRealContext):
      return float(self.visit(ctx.expression()))

   def visitExprPenOperator(self, ctx:penParser.ExprPenOperatorContext):
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
      degrees = self.visit(ctx.expression())
      return math.radians(degrees)

   def visitExprPenUnary(self, ctx:penParser.ExprPenUnaryContext):
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

   def visitExprStdin(self, ctx:penParser.ExprStdinContext):
      return input(self.visit(ctx.expression()))

   def visitExprAssign(self, ctx:penParser.ExprAssignContext):
      var_name = ctx.ID().getText()
      value = self.visit(ctx.expression())
      self.variables[var_name] = value
      return value

   def visitExprReal(self, ctx:penParser.ExprRealContext):
      return float(ctx.Real().getText())

   def visitExprBool(self, ctx:penParser.ExprBoolContext):
      return ctx.Boolean().getText() == 'true'

   def visitAssign(self, ctx:penParser.AssignContext):
      return self.visitChildren(ctx)

   def visitPoint(self, ctx:penParser.PointContext):
      return self.visitChildren(ctx)

