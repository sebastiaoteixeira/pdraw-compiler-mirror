# Generated from pen.g4 by ANTLR 4.13.1
from antlr4 import *
if "." in __name__:
    from .penParser import penParser
else:
    from penParser import penParser

# This class defines a complete listener for a parse tree produced by penParser.
class penListener(ParseTreeListener):

    # Enter a parse tree produced by penParser#program.
    def enterProgram(self, ctx:penParser.ProgramContext):
        pass

    # Exit a parse tree produced by penParser#program.
    def exitProgram(self, ctx:penParser.ProgramContext):
        pass


    # Enter a parse tree produced by penParser#command.
    def enterCommand(self, ctx:penParser.CommandContext):
        pass

    # Exit a parse tree produced by penParser#command.
    def exitCommand(self, ctx:penParser.CommandContext):
        pass


    # Enter a parse tree produced by penParser#setOrientation.
    def enterSetOrientation(self, ctx:penParser.SetOrientationContext):
        pass

    # Exit a parse tree produced by penParser#setOrientation.
    def exitSetOrientation(self, ctx:penParser.SetOrientationContext):
        pass


    # Enter a parse tree produced by penParser#setPressure.
    def enterSetPressure(self, ctx:penParser.SetPressureContext):
        pass

    # Exit a parse tree produced by penParser#setPressure.
    def exitSetPressure(self, ctx:penParser.SetPressureContext):
        pass


    # Enter a parse tree produced by penParser#setThickness.
    def enterSetThickness(self, ctx:penParser.SetThicknessContext):
        pass

    # Exit a parse tree produced by penParser#setThickness.
    def exitSetThickness(self, ctx:penParser.SetThicknessContext):
        pass


    # Enter a parse tree produced by penParser#setColor.
    def enterSetColor(self, ctx:penParser.SetColorContext):
        pass

    # Exit a parse tree produced by penParser#setColor.
    def exitSetColor(self, ctx:penParser.SetColorContext):
        pass


    # Enter a parse tree produced by penParser#setPosition.
    def enterSetPosition(self, ctx:penParser.SetPositionContext):
        pass

    # Exit a parse tree produced by penParser#setPosition.
    def exitSetPosition(self, ctx:penParser.SetPositionContext):
        pass


    # Enter a parse tree produced by penParser#penControl.
    def enterPenControl(self, ctx:penParser.PenControlContext):
        pass

    # Exit a parse tree produced by penParser#penControl.
    def exitPenControl(self, ctx:penParser.PenControlContext):
        pass


    # Enter a parse tree produced by penParser#penMove.
    def enterPenMove(self, ctx:penParser.PenMoveContext):
        pass

    # Exit a parse tree produced by penParser#penMove.
    def exitPenMove(self, ctx:penParser.PenMoveContext):
        pass


    # Enter a parse tree produced by penParser#rotate.
    def enterRotate(self, ctx:penParser.RotateContext):
        pass

    # Exit a parse tree produced by penParser#rotate.
    def exitRotate(self, ctx:penParser.RotateContext):
        pass


    # Enter a parse tree produced by penParser#expr.
    def enterExpr(self, ctx:penParser.ExprContext):
        pass

    # Exit a parse tree produced by penParser#expr.
    def exitExpr(self, ctx:penParser.ExprContext):
        pass



del penParser