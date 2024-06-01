# Generated from pen.g4 by ANTLR 4.13.1
from antlr4 import *
if "." in __name__:
    from .penParser import penParser
else:
    from penParser import penParser

# This class defines a complete generic visitor for a parse tree produced by penParser.

class penVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by penParser#program.
    def visitProgram(self, ctx:penParser.ProgramContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#declaration_element.
    def visitDeclaration_element(self, ctx:penParser.Declaration_elementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#declaration.
    def visitDeclaration(self, ctx:penParser.DeclarationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#statement.
    def visitStatement(self, ctx:penParser.StatementContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#while.
    def visitWhile(self, ctx:penParser.WhileContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#until.
    def visitUntil(self, ctx:penParser.UntilContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#for.
    def visitFor(self, ctx:penParser.ForContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#if.
    def visitIf(self, ctx:penParser.IfContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#pause.
    def visitPause(self, ctx:penParser.PauseContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#stdout.
    def visitStdout(self, ctx:penParser.StdoutContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprToString.
    def visitExprToString(self, ctx:penParser.ExprToStringContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprToBool.
    def visitExprToBool(self, ctx:penParser.ExprToBoolContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprToInt.
    def visitExprToInt(self, ctx:penParser.ExprToIntContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprMultDivMod.
    def visitExprMultDivMod(self, ctx:penParser.ExprMultDivModContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprSetProperty.
    def visitExprSetProperty(self, ctx:penParser.ExprSetPropertyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprAddSub.
    def visitExprAddSub(self, ctx:penParser.ExprAddSubContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprInteger.
    def visitExprInteger(self, ctx:penParser.ExprIntegerContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprId.
    def visitExprId(self, ctx:penParser.ExprIdContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprPi.
    def visitExprPi(self, ctx:penParser.ExprPiContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprString.
    def visitExprString(self, ctx:penParser.ExprStringContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprParent.
    def visitExprParent(self, ctx:penParser.ExprParentContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprComp.
    def visitExprComp(self, ctx:penParser.ExprCompContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprPoint.
    def visitExprPoint(self, ctx:penParser.ExprPointContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprStringConcat.
    def visitExprStringConcat(self, ctx:penParser.ExprStringConcatContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprColor.
    def visitExprColor(self, ctx:penParser.ExprColorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprToReal.
    def visitExprToReal(self, ctx:penParser.ExprToRealContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprPenOperator.
    def visitExprPenOperator(self, ctx:penParser.ExprPenOperatorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprConvToRad.
    def visitExprConvToRad(self, ctx:penParser.ExprConvToRadContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprPenUnary.
    def visitExprPenUnary(self, ctx:penParser.ExprPenUnaryContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprUnary.
    def visitExprUnary(self, ctx:penParser.ExprUnaryContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprBoolOp.
    def visitExprBoolOp(self, ctx:penParser.ExprBoolOpContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprStdin.
    def visitExprStdin(self, ctx:penParser.ExprStdinContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprAssign.
    def visitExprAssign(self, ctx:penParser.ExprAssignContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprReal.
    def visitExprReal(self, ctx:penParser.ExprRealContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#ExprBool.
    def visitExprBool(self, ctx:penParser.ExprBoolContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#assign.
    def visitAssign(self, ctx:penParser.AssignContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#point.
    def visitPoint(self, ctx:penParser.PointContext):
        return self.visitChildren(ctx)



del penParser