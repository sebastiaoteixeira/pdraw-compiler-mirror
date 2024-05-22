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


    # Visit a parse tree produced by penParser#command.
    def visitCommand(self, ctx:penParser.CommandContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#setOrientation.
    def visitSetOrientation(self, ctx:penParser.SetOrientationContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#setPressure.
    def visitSetPressure(self, ctx:penParser.SetPressureContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#setThickness.
    def visitSetThickness(self, ctx:penParser.SetThicknessContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#setColor.
    def visitSetColor(self, ctx:penParser.SetColorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#setPosition.
    def visitSetPosition(self, ctx:penParser.SetPositionContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#penControl.
    def visitPenControl(self, ctx:penParser.PenControlContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#penMove.
    def visitPenMove(self, ctx:penParser.PenMoveContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#rotate.
    def visitRotate(self, ctx:penParser.RotateContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by penParser#expr.
    def visitExpr(self, ctx:penParser.ExprContext):
        return self.visitChildren(ctx)



del penParser