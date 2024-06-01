from antlr4 import *
from penParser import penParser
from penVisitor import penVisitor
from vTypes import Type

class SemanticVerifier(penVisitor):
   def __init__(self):
      self.variables = {} # {variable_name: type}
      
   def switchFunctionType(self, vType):
      if vType == 'int':
         return Type.Integer
      elif vType == 'real':
         return Type.Real
      elif vType == 'string':
         return Type.String
      elif vType == 'bool':
         return Type.Bool
      elif vType == 'point':
         return Type.Point
      else:
         return None
      

   def visitProgram(self, ctx:penParser.ProgramContext):
      return self.visitChildren(ctx)

   def visitDeclaration_element(self, ctx:penParser.Declaration_elementContext):
      return self.visitChildren(ctx)

   def visitDeclaration(self, ctx:penParser.DeclarationContext):
      vType = self.switchFunctionType(ctx.Type().getText())
      self.variables[ctx.ID().getText()] = vType
      return vType

   def visitStatement(self, ctx:penParser.StatementContext):
      return self.visitChildren(ctx)

   def visitWhile(self, ctx:penParser.WhileContext):
      if (self.visit(ctx.expression()) != Type.Bool):
         raise TypeError("While condition must be a boolean value.")
      return None

   def visitUntil(self, ctx:penParser.UntilContext):
      if (self.visit(ctx.expression()) != Type.Bool):
         raise TypeError("Until condition must be a boolean value.")      
      return None

   def visitFor(self, ctx:penParser.ForContext):
      if (self.visit(ctx.expression(0)) != Type.Bool):
         raise TypeError("Loop range must be a boolean value.")
      return None

   def visitIf(self, ctx:penParser.IfContext):
      if (self.visit(ctx.expression()) != Type.Bool):
         raise TypeError("If condition must be a boolean value.")
      return None

   def visitPause(self, ctx:penParser.PauseContext):
      if (self.visit(ctx.expression()) != Type.Integer):
         raise TypeError("Pause time must be an integer value.")
      return self.visit(ctx.expression())

   def visitStdout(self, ctx:penParser.StdoutContext):
      if (self.visit(ctx.expression()) != Type.String):
         raise TypeError("Stdout value must be a string.")
      return None

   def visitExprToString(self, ctx:penParser.ExprToStringContext):
      exprType = self.visit(ctx.expression)
      if (self.isNumericType(exprType) or self.isStringType(exprType)):
         raise TypeError("Conversion to string can only be applied to real, integer, or string values.")
      return Type.String

   def visitExprToBool(self, ctx:penParser.ExprToBoolContext):
      exprType = self.visit(ctx.expression())
      if (exprType != Type.Bool):
         raise TypeError("Conversion to boolean can only be applied to boolean values.")
      return Type.Bool

   def visitExprToInt(self, ctx:penParser.ExprToIntContext):
      exprType = self.visit(ctx.expression())
      if not (self.isNumericType(exprType) or self.isStringType(exprType)):
         raise TypeError("Conversion to integer can only be applied to real, integer, or string values.")
      return Type.Integer
   
   def visitExprMultDivMod(self, ctx:penParser.ExprMultDivModContext):
      exprType1, exprType2 = self.visit(ctx.expression(0)), self.visit(ctx.expression(1))
      if not (self.isNumericType(exprType1) and self.isNumericType(exprType2)):
         raise TypeError("Error: The values must be numeric (Integer or Real)")
      result = Type.Integer
      if (exprType1 == Type.Real or exprType2 == Type.Real):
         result = Type.Real
      return result

   def visitExprSetProperty(self, ctx:penParser.ExprSetPropertyContext):
      prop = ctx.Property().getText()
      if prop == 'color':
         if (self.visit(ctx.expression()) != Type.Integer):
            raise TypeError("Color value must be an integer.")
      elif prop == 'pressure':
         if self.isNumericType(self.visit(ctx.expression())):
            raise TypeError("Pressure value must be an integer.")
      elif prop == 'thickness':
         if self.isNumericType(self.visit(ctx.expression())):
            raise TypeError("Thickness value must be an integer.")
      elif prop == 'orientation':
         if self.isNumericType(self.visit(ctx.expression())):
            raise TypeError("Orientation value must be an integer.")
      elif prop == 'position':
         if self.visit(ctx.expression()) != Type.Point:
            raise TypeError("Position value must be a point.")
      return None

   def visitExprAddSub(self, ctx:penParser.ExprAddSubContext):
      exprType1, exprType2 = self.visit(ctx.expression(0)), self.visit(ctx.expression(1))
      if not (self.isNumericType(exprType1) and self.isNumericType(exprType2)):
         raise TypeError("Error: The values must be numeric (Integer or Real)")
      result = Type.Integer
      if (exprType1 == Type.Real or exprType2 == Type.Real):
         result = Type.Real
      return result

   def visitExprInteger(self, ctx:penParser.ExprIntegerContext):
      return Type.Integer

   def visitExprId(self, ctx:penParser.ExprIdContext):
      id = ctx.ID().getText()
      if id not in self.variables:
         raise NameError("Error: Variable not declared.")
      return self.variables[id]

   def visitExprPi(self, ctx:penParser.ExprPiContext):
      return Type.Real

   def visitExprString(self, ctx:penParser.ExprStringContext):
      return Type.String

   def visitExprParent(self, ctx:penParser.ExprParentContext):
      return self.visit(ctx.expression())

   def visitExprComp(self, ctx:penParser.ExprCompContext):
      exprType1, exprType2 = self.visit(ctx.expression(0)), self.visit(ctx.expression(1))
      if not (self.isNumericType(exprType1) and self.isNumericType(exprType2) 
          or self.isStringType(exprType1) and self.isStringType(exprType2) 
          or self.isPointType(exprType1) and self.isPointType(exprType2)):
         raise TypeError("Error: Incompatible types!")
      return Type.Bool
         
   def visitExprPoint(self, ctx:penParser.ExprPointContext):
      return Type.Point

   def visitExprStringConcat(self, ctx:penParser.ExprStringConcatContext):
      exprType1 = self.visit(ctx.expression(0))
      exprType2 = self.visit(ctx.expression(1))
      if not (self.isStringType(exprType1) or self.isStringType(exprType2)):
         raise TypeError("Operands of '++' must be strings.")
      return Type.String

   def visitExprColor(self, ctx:penParser.ExprColorContext):
      return Type.Integer

   def visitExprToReal(self, ctx:penParser.ExprToRealContext):
      exprType = self.visit(ctx.expression())
      if not (self.isNumericType(exprType) or self.isStringType(exprType)):
         raise TypeError("Conversion to real can only be applied to real, integer, or string values.")
      return Type.Real

   def visitExprPenOperator(self, ctx:penParser.ExprPenOperatorContext):
      if not self.isNumericType(self.visit(ctx.expression())):
         raise TypeError("Pen operator can only be applied to real or integer values.")
      return None

   def visitExprConvToRad(self, ctx:penParser.ExprConvToRadContext):
      exprType = self.visit(ctx.expression())
      if not (self.isNumericType(exprType) or self.isStringType(exprType)):
         raise TypeError ("Conversion to radian can only be applied to real, integer, or string values.")     
      return Type.Real

   def visitExprPenUnary(self, ctx:penParser.ExprPenUnaryContext):
      return None

   def visitExprUnary(self, ctx:penParser.ExprUnaryContext):
      exprType = self.visit(ctx.expression())
      if not (self.isNumericType(exprType)):
         raise TypeError("Error: Unary operator can only be applied to numeric values.")
      return exprType

   def visitExprBoolOp(self, ctx:penParser.ExprBoolOpContext):
      exprType1 = self.visit(ctx.expression(0))
      exprType2 = self.visit(ctx.expression(1))
      if exprType1 != Type.Bool or exprType2 != Type.Bool:
         raise TypeError("Operands of boolean operators must be boolean values.")
      return Type.Bool

   def visitExprStdin(self, ctx:penParser.ExprStdinContext):
      exprType = self.visit(ctx.expression())
      if not (self.isStringType(exprType)):
         raise TypeError("Stdin operator can only be applied to string values.")
      return Type.String

   def visitExprAssign(self, ctx:penParser.ExprAssignContext):
      return self.visit(ctx.assign())

   def visitExprReal(self, ctx:penParser.ExprRealContext):
      return Type.Real

   def visitExprBool(self, ctx:penParser.ExprBoolContext):
      return Type.Bool

   def visitAssign(self, ctx:penParser.AssignContext):
      id = ctx.ID().getText()
      if id not in self.variables:
         raise NameError("Error: Variable not declared.")
      if self.variables[id] != self.visit(ctx.expression()):
         raise TypeError("Error: Incompatible types!")
      return self.variables[id]

   def visitPoint(self, ctx:penParser.PointContext):
      exprType1 = self.visit(ctx.expression(0))
      exprType2 = self.visit(ctx.expression(1))
      if not (self.isNumericType(exprType1) and self.isNumericType(exprType2)):
           raise TypeError("Error: Point values must be integer or real values.")
      return Type.Point
   
   def isNumericType(self, vType):
      return vType == Type.Integer or vType == Type.Real
   
   def isStringType(self, vType):
      return vType == Type.String
   
   def isPointType(self, vType):
      return vType == Type.Point