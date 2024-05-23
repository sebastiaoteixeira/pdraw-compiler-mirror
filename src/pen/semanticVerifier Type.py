from antlr4 import *
from penParser import penParser
from penVisitor import penVisitor

class semanticVerifier Type(penVisitor):
   def visitProgram(self, ctx:penParser.ProgramContext):
      return self.visitChildren(ctx)

   def visitDeclaration_element(self, ctx:penParser.Declaration_elementContext):
      return self.visitChildren(ctx)

   def visitDeclaration(self, ctx:penParser.DeclarationContext):
      return self.visitChildren(ctx)

   def visitStatement(self, ctx:penParser.StatementContext):
      return self.visitChildren(ctx)

   def visitWhile(self, ctx:penParser.WhileContext):
      return self.visitChildren(ctx)

   def visitUntil(self, ctx:penParser.UntilContext):
      return self.visitChildren(ctx)

   def visitFor(self, ctx:penParser.ForContext):
      return self.visitChildren(ctx)

   def visitIf(self, ctx:penParser.IfContext):
      return self.visitChildren(ctx)

   def visitPause(self, ctx:penParser.PauseContext):
      return self.visitChildren(ctx)

   def visitStdout(self, ctx:penParser.StdoutContext):
      return self.visitChildren(ctx)

   def visitExprToString(self, ctx:penParser.ExprToStringContext):
      return self.visitChildren(ctx)

   def visitExprToBool(self, ctx:penParser.ExprToBoolContext):
      return self.visitChildren(ctx)

   def visitExprToInt(self, ctx:penParser.ExprToIntContext):
      return self.visitChildren(ctx)

   def visitExprMultDivMod(self, ctx:penParser.ExprMultDivModContext):
      return self.visitChildren(ctx)

   def visitExprSetProperty(self, ctx:penParser.ExprSetPropertyContext):
      return self.visitChildren(ctx)

   def visitExprAddSub(self, ctx:penParser.ExprAddSubContext):
      return self.visitChildren(ctx)

   def visitExprInteger(self, ctx:penParser.ExprIntegerContext):
      return self.visitChildren(ctx)

   def visitExprId(self, ctx:penParser.ExprIdContext):
      return self.visitChildren(ctx)

   def visitExprPi(self, ctx:penParser.ExprPiContext):
      return self.visitChildren(ctx)

   def visitExprString(self, ctx:penParser.ExprStringContext):
      return self.visitChildren(ctx)

   def visitExprParent(self, ctx:penParser.ExprParentContext):
      return self.visitChildren(ctx)

   def visitExprComp(self, ctx:penParser.ExprCompContext):
      return self.visitChildren(ctx)

   def visitExprPoint(self, ctx:penParser.ExprPointContext):
      return self.visitChildren(ctx)

   def visitExprStringConcat(self, ctx:penParser.ExprStringConcatContext):
      return self.visitChildren(ctx)

   def visitExprColor(self, ctx:penParser.ExprColorContext):
      return self.visitChildren(ctx)

   def visitExprToReal(self, ctx:penParser.ExprToRealContext):
      return self.visitChildren(ctx)

   def visitExprPenOperator(self, ctx:penParser.ExprPenOperatorContext):
      return self.visitChildren(ctx)

   def visitExprConvToRad(self, ctx:penParser.ExprConvToRadContext):
      return self.visitChildren(ctx)

   def visitExprPenUnary(self, ctx:penParser.ExprPenUnaryContext):
      return self.visitChildren(ctx)

   def visitExprUnary(self, ctx:penParser.ExprUnaryContext):
      return self.visitChildren(ctx)

   def visitExprBoolOp(self, ctx:penParser.ExprBoolOpContext):
      return self.visitChildren(ctx)

   def visitExprStdin(self, ctx:penParser.ExprStdinContext):
      return self.visitChildren(ctx)

   def visitExprAssign(self, ctx:penParser.ExprAssignContext):
      return self.visitChildren(ctx)

   def visitExprReal(self, ctx:penParser.ExprRealContext):
      return self.visitChildren(ctx)

   def visitExprBool(self, ctx:penParser.ExprBoolContext):
      return self.visitChildren(ctx)

   def visitAssign(self, ctx:penParser.AssignContext):
      return self.visitChildren(ctx)

   def visitPoint(self, ctx:penParser.PointContext):
      return self.visitChildren(ctx)