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


    # Enter a parse tree produced by penParser#declaration_element.
    def enterDeclaration_element(self, ctx:penParser.Declaration_elementContext):
        pass

    # Exit a parse tree produced by penParser#declaration_element.
    def exitDeclaration_element(self, ctx:penParser.Declaration_elementContext):
        pass


    # Enter a parse tree produced by penParser#declaration.
    def enterDeclaration(self, ctx:penParser.DeclarationContext):
        pass

    # Exit a parse tree produced by penParser#declaration.
    def exitDeclaration(self, ctx:penParser.DeclarationContext):
        pass


    # Enter a parse tree produced by penParser#statement.
    def enterStatement(self, ctx:penParser.StatementContext):
        pass

    # Exit a parse tree produced by penParser#statement.
    def exitStatement(self, ctx:penParser.StatementContext):
        pass


    # Enter a parse tree produced by penParser#while.
    def enterWhile(self, ctx:penParser.WhileContext):
        pass

    # Exit a parse tree produced by penParser#while.
    def exitWhile(self, ctx:penParser.WhileContext):
        pass


    # Enter a parse tree produced by penParser#until.
    def enterUntil(self, ctx:penParser.UntilContext):
        pass

    # Exit a parse tree produced by penParser#until.
    def exitUntil(self, ctx:penParser.UntilContext):
        pass


    # Enter a parse tree produced by penParser#for.
    def enterFor(self, ctx:penParser.ForContext):
        pass

    # Exit a parse tree produced by penParser#for.
    def exitFor(self, ctx:penParser.ForContext):
        pass


    # Enter a parse tree produced by penParser#if.
    def enterIf(self, ctx:penParser.IfContext):
        pass

    # Exit a parse tree produced by penParser#if.
    def exitIf(self, ctx:penParser.IfContext):
        pass


    # Enter a parse tree produced by penParser#pause.
    def enterPause(self, ctx:penParser.PauseContext):
        pass

    # Exit a parse tree produced by penParser#pause.
    def exitPause(self, ctx:penParser.PauseContext):
        pass


    # Enter a parse tree produced by penParser#stdout.
    def enterStdout(self, ctx:penParser.StdoutContext):
        pass

    # Exit a parse tree produced by penParser#stdout.
    def exitStdout(self, ctx:penParser.StdoutContext):
        pass


    # Enter a parse tree produced by penParser#ExprToString.
    def enterExprToString(self, ctx:penParser.ExprToStringContext):
        pass

    # Exit a parse tree produced by penParser#ExprToString.
    def exitExprToString(self, ctx:penParser.ExprToStringContext):
        pass


    # Enter a parse tree produced by penParser#ExprToBool.
    def enterExprToBool(self, ctx:penParser.ExprToBoolContext):
        pass

    # Exit a parse tree produced by penParser#ExprToBool.
    def exitExprToBool(self, ctx:penParser.ExprToBoolContext):
        pass


    # Enter a parse tree produced by penParser#ExprToInt.
    def enterExprToInt(self, ctx:penParser.ExprToIntContext):
        pass

    # Exit a parse tree produced by penParser#ExprToInt.
    def exitExprToInt(self, ctx:penParser.ExprToIntContext):
        pass


    # Enter a parse tree produced by penParser#ExprMultDivMod.
    def enterExprMultDivMod(self, ctx:penParser.ExprMultDivModContext):
        pass

    # Exit a parse tree produced by penParser#ExprMultDivMod.
    def exitExprMultDivMod(self, ctx:penParser.ExprMultDivModContext):
        pass


    # Enter a parse tree produced by penParser#ExprSetProperty.
    def enterExprSetProperty(self, ctx:penParser.ExprSetPropertyContext):
        pass

    # Exit a parse tree produced by penParser#ExprSetProperty.
    def exitExprSetProperty(self, ctx:penParser.ExprSetPropertyContext):
        pass


    # Enter a parse tree produced by penParser#ExprAddSub.
    def enterExprAddSub(self, ctx:penParser.ExprAddSubContext):
        pass

    # Exit a parse tree produced by penParser#ExprAddSub.
    def exitExprAddSub(self, ctx:penParser.ExprAddSubContext):
        pass


    # Enter a parse tree produced by penParser#ExprInteger.
    def enterExprInteger(self, ctx:penParser.ExprIntegerContext):
        pass

    # Exit a parse tree produced by penParser#ExprInteger.
    def exitExprInteger(self, ctx:penParser.ExprIntegerContext):
        pass


    # Enter a parse tree produced by penParser#ExprId.
    def enterExprId(self, ctx:penParser.ExprIdContext):
        pass

    # Exit a parse tree produced by penParser#ExprId.
    def exitExprId(self, ctx:penParser.ExprIdContext):
        pass


    # Enter a parse tree produced by penParser#ExprPi.
    def enterExprPi(self, ctx:penParser.ExprPiContext):
        pass

    # Exit a parse tree produced by penParser#ExprPi.
    def exitExprPi(self, ctx:penParser.ExprPiContext):
        pass


    # Enter a parse tree produced by penParser#ExprString.
    def enterExprString(self, ctx:penParser.ExprStringContext):
        pass

    # Exit a parse tree produced by penParser#ExprString.
    def exitExprString(self, ctx:penParser.ExprStringContext):
        pass


    # Enter a parse tree produced by penParser#ExprParent.
    def enterExprParent(self, ctx:penParser.ExprParentContext):
        pass

    # Exit a parse tree produced by penParser#ExprParent.
    def exitExprParent(self, ctx:penParser.ExprParentContext):
        pass


    # Enter a parse tree produced by penParser#ExprComp.
    def enterExprComp(self, ctx:penParser.ExprCompContext):
        pass

    # Exit a parse tree produced by penParser#ExprComp.
    def exitExprComp(self, ctx:penParser.ExprCompContext):
        pass


    # Enter a parse tree produced by penParser#ExprPoint.
    def enterExprPoint(self, ctx:penParser.ExprPointContext):
        pass

    # Exit a parse tree produced by penParser#ExprPoint.
    def exitExprPoint(self, ctx:penParser.ExprPointContext):
        pass


    # Enter a parse tree produced by penParser#ExprStringConcat.
    def enterExprStringConcat(self, ctx:penParser.ExprStringConcatContext):
        pass

    # Exit a parse tree produced by penParser#ExprStringConcat.
    def exitExprStringConcat(self, ctx:penParser.ExprStringConcatContext):
        pass


    # Enter a parse tree produced by penParser#ExprColor.
    def enterExprColor(self, ctx:penParser.ExprColorContext):
        pass

    # Exit a parse tree produced by penParser#ExprColor.
    def exitExprColor(self, ctx:penParser.ExprColorContext):
        pass


    # Enter a parse tree produced by penParser#ExprToReal.
    def enterExprToReal(self, ctx:penParser.ExprToRealContext):
        pass

    # Exit a parse tree produced by penParser#ExprToReal.
    def exitExprToReal(self, ctx:penParser.ExprToRealContext):
        pass


    # Enter a parse tree produced by penParser#ExprPenOperator.
    def enterExprPenOperator(self, ctx:penParser.ExprPenOperatorContext):
        pass

    # Exit a parse tree produced by penParser#ExprPenOperator.
    def exitExprPenOperator(self, ctx:penParser.ExprPenOperatorContext):
        pass


    # Enter a parse tree produced by penParser#ExprConvToRad.
    def enterExprConvToRad(self, ctx:penParser.ExprConvToRadContext):
        pass

    # Exit a parse tree produced by penParser#ExprConvToRad.
    def exitExprConvToRad(self, ctx:penParser.ExprConvToRadContext):
        pass


    # Enter a parse tree produced by penParser#ExprPenUnary.
    def enterExprPenUnary(self, ctx:penParser.ExprPenUnaryContext):
        pass

    # Exit a parse tree produced by penParser#ExprPenUnary.
    def exitExprPenUnary(self, ctx:penParser.ExprPenUnaryContext):
        pass


    # Enter a parse tree produced by penParser#ExprUnary.
    def enterExprUnary(self, ctx:penParser.ExprUnaryContext):
        pass

    # Exit a parse tree produced by penParser#ExprUnary.
    def exitExprUnary(self, ctx:penParser.ExprUnaryContext):
        pass


    # Enter a parse tree produced by penParser#ExprBoolOp.
    def enterExprBoolOp(self, ctx:penParser.ExprBoolOpContext):
        pass

    # Exit a parse tree produced by penParser#ExprBoolOp.
    def exitExprBoolOp(self, ctx:penParser.ExprBoolOpContext):
        pass


    # Enter a parse tree produced by penParser#ExprStdin.
    def enterExprStdin(self, ctx:penParser.ExprStdinContext):
        pass

    # Exit a parse tree produced by penParser#ExprStdin.
    def exitExprStdin(self, ctx:penParser.ExprStdinContext):
        pass


    # Enter a parse tree produced by penParser#ExprAssign.
    def enterExprAssign(self, ctx:penParser.ExprAssignContext):
        pass

    # Exit a parse tree produced by penParser#ExprAssign.
    def exitExprAssign(self, ctx:penParser.ExprAssignContext):
        pass


    # Enter a parse tree produced by penParser#ExprReal.
    def enterExprReal(self, ctx:penParser.ExprRealContext):
        pass

    # Exit a parse tree produced by penParser#ExprReal.
    def exitExprReal(self, ctx:penParser.ExprRealContext):
        pass


    # Enter a parse tree produced by penParser#ExprBool.
    def enterExprBool(self, ctx:penParser.ExprBoolContext):
        pass

    # Exit a parse tree produced by penParser#ExprBool.
    def exitExprBool(self, ctx:penParser.ExprBoolContext):
        pass


    # Enter a parse tree produced by penParser#assign.
    def enterAssign(self, ctx:penParser.AssignContext):
        pass

    # Exit a parse tree produced by penParser#assign.
    def exitAssign(self, ctx:penParser.AssignContext):
        pass


    # Enter a parse tree produced by penParser#point.
    def enterPoint(self, ctx:penParser.PointContext):
        pass

    # Exit a parse tree produced by penParser#point.
    def exitPoint(self, ctx:penParser.PointContext):
        pass



del penParser