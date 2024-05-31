from antlr4 import *
from penParser import penParser
from penVisitor import penVisitor
from types import Type

class semanticVerifier(penVisitor):
   def visitProgram(self, ctx:penParser.ProgramContext):
      return self.visitChildren(ctx)

   def visitDeclaration_element(self, ctx:penParser.Declaration_elementContext):
      return self.visitChildren(ctx)

   def visitDeclaration(self, ctx:penParser.DeclarationContext):
      return self.visitChildren(ctx)

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
      return self.visitChildren(ctx)

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
         result = Type.Integer
      return result

   def visitExprSetProperty(self, ctx:penParser.ExprSetPropertyContext):
      return self.visitChildren(ctx)

   def visitExprAddSub(self, ctx:penParser.ExprAddSubContext):
      exprType1, exprType2 = self.visit(ctx.expression(0)), self.visit(ctx.expression(1))
      if not (self.isNumericType(exprType1) and self.isNumericType(exprType2)):
         raise TypeError("Error: The values must be numeric (Integer or Real)")
      result = Type.Integer
      if (exprType1 == Type.Real or exprType2 == Type.Real):
         result = Type.Integer
      return result

   def visitExprInteger(self, ctx:penParser.ExprIntegerContext):
      return Type.Integer

   def visitExprId(self, ctx:penParser.ExprIdContext):
      return self.visitChildren(ctx)

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
      return self.visitChildren(ctx)

   def visitExprConvToRad(self, ctx:penParser.ExprConvToRadContext):
      exprType = self.visit(ctx.expression())
      if not (self.isNumericType(exprType) or self.isStringType(exprType)):
         raise TypeError ("Conversion to radian can only be applied to real, integer, or string values.")     
      return Type.Real

   def visitExprPenUnary(self, ctx:penParser.ExprPenUnaryContext):
      return self.visitChildren(ctx)

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
      return self.visitChildren(ctx)

   def visitPoint(self, ctx:penParser.PointContext):
      exprType1 = self.visit(ctx.expression(0))
      exprType2 = self.visit(ctx.expression(1))
      if not (self.isNumericType(exprType1) and self.isNumericType(exprType2)):
           raise TypeError("Error: Point values must be integer or real values.")
      return Type.Point
   
   def isNumericType(self, type):
      return type == Type.Integer or type == Type.Real
   
   def isStringType(self, type):
      return type == Type.String
   
   def isPointType(self, type):
      return type == Type.Point