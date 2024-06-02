# Generated from pdraw.g4 by ANTLR 4.13.1
from antlr4 import *
if "." in __name__:
    from .pdrawParser import pdrawParser
else:
    from pdrawParser import pdrawParser

# This class defines a complete listener for a parse tree produced by pdrawParser.
class pdrawListener(ParseTreeListener):

    # Enter a parse tree produced by pdrawParser#program.
    def enterProgram(self, ctx:pdrawParser.ProgramContext):
        pass

    # Exit a parse tree produced by pdrawParser#program.
    def exitProgram(self, ctx:pdrawParser.ProgramContext):
        pass


    # Enter a parse tree produced by pdrawParser#function.
    def enterFunction(self, ctx:pdrawParser.FunctionContext):
        pass

    # Exit a parse tree produced by pdrawParser#function.
    def exitFunction(self, ctx:pdrawParser.FunctionContext):
        pass


    # Enter a parse tree produced by pdrawParser#statement.
    def enterStatement(self, ctx:pdrawParser.StatementContext):
        pass

    # Exit a parse tree produced by pdrawParser#statement.
    def exitStatement(self, ctx:pdrawParser.StatementContext):
        pass


    # Enter a parse tree produced by pdrawParser#return.
    def enterReturn(self, ctx:pdrawParser.ReturnContext):
        pass

    # Exit a parse tree produced by pdrawParser#return.
    def exitReturn(self, ctx:pdrawParser.ReturnContext):
        pass


    # Enter a parse tree produced by pdrawParser#compound.
    def enterCompound(self, ctx:pdrawParser.CompoundContext):
        pass

    # Exit a parse tree produced by pdrawParser#compound.
    def exitCompound(self, ctx:pdrawParser.CompoundContext):
        pass


    # Enter a parse tree produced by pdrawParser#block.
    def enterBlock(self, ctx:pdrawParser.BlockContext):
        pass

    # Exit a parse tree produced by pdrawParser#block.
    def exitBlock(self, ctx:pdrawParser.BlockContext):
        pass


    # Enter a parse tree produced by pdrawParser#define.
    def enterDefine(self, ctx:pdrawParser.DefineContext):
        pass

    # Exit a parse tree produced by pdrawParser#define.
    def exitDefine(self, ctx:pdrawParser.DefineContext):
        pass


    # Enter a parse tree produced by pdrawParser#penDefinition.
    def enterPenDefinition(self, ctx:pdrawParser.PenDefinitionContext):
        pass

    # Exit a parse tree produced by pdrawParser#penDefinition.
    def exitPenDefinition(self, ctx:pdrawParser.PenDefinitionContext):
        pass


    # Enter a parse tree produced by pdrawParser#propertyDefinition.
    def enterPropertyDefinition(self, ctx:pdrawParser.PropertyDefinitionContext):
        pass

    # Exit a parse tree produced by pdrawParser#propertyDefinition.
    def exitPropertyDefinition(self, ctx:pdrawParser.PropertyDefinitionContext):
        pass


    # Enter a parse tree produced by pdrawParser#canvasDefinition.
    def enterCanvasDefinition(self, ctx:pdrawParser.CanvasDefinitionContext):
        pass

    # Exit a parse tree produced by pdrawParser#canvasDefinition.
    def exitCanvasDefinition(self, ctx:pdrawParser.CanvasDefinitionContext):
        pass


    # Enter a parse tree produced by pdrawParser#parameter_list.
    def enterParameter_list(self, ctx:pdrawParser.Parameter_listContext):
        pass

    # Exit a parse tree produced by pdrawParser#parameter_list.
    def exitParameter_list(self, ctx:pdrawParser.Parameter_listContext):
        pass


    # Enter a parse tree produced by pdrawParser#parameter.
    def enterParameter(self, ctx:pdrawParser.ParameterContext):
        pass

    # Exit a parse tree produced by pdrawParser#parameter.
    def exitParameter(self, ctx:pdrawParser.ParameterContext):
        pass


    # Enter a parse tree produced by pdrawParser#declaration_element.
    def enterDeclaration_element(self, ctx:pdrawParser.Declaration_elementContext):
        pass

    # Exit a parse tree produced by pdrawParser#declaration_element.
    def exitDeclaration_element(self, ctx:pdrawParser.Declaration_elementContext):
        pass


    # Enter a parse tree produced by pdrawParser#declaration.
    def enterDeclaration(self, ctx:pdrawParser.DeclarationContext):
        pass

    # Exit a parse tree produced by pdrawParser#declaration.
    def exitDeclaration(self, ctx:pdrawParser.DeclarationContext):
        pass


    # Enter a parse tree produced by pdrawParser#while.
    def enterWhile(self, ctx:pdrawParser.WhileContext):
        pass

    # Exit a parse tree produced by pdrawParser#while.
    def exitWhile(self, ctx:pdrawParser.WhileContext):
        pass


    # Enter a parse tree produced by pdrawParser#until.
    def enterUntil(self, ctx:pdrawParser.UntilContext):
        pass

    # Exit a parse tree produced by pdrawParser#until.
    def exitUntil(self, ctx:pdrawParser.UntilContext):
        pass


    # Enter a parse tree produced by pdrawParser#for.
    def enterFor(self, ctx:pdrawParser.ForContext):
        pass

    # Exit a parse tree produced by pdrawParser#for.
    def exitFor(self, ctx:pdrawParser.ForContext):
        pass


    # Enter a parse tree produced by pdrawParser#if.
    def enterIf(self, ctx:pdrawParser.IfContext):
        pass

    # Exit a parse tree produced by pdrawParser#if.
    def exitIf(self, ctx:pdrawParser.IfContext):
        pass


    # Enter a parse tree produced by pdrawParser#execution.
    def enterExecution(self, ctx:pdrawParser.ExecutionContext):
        pass

    # Exit a parse tree produced by pdrawParser#execution.
    def exitExecution(self, ctx:pdrawParser.ExecutionContext):
        pass


    # Enter a parse tree produced by pdrawParser#stdout.
    def enterStdout(self, ctx:pdrawParser.StdoutContext):
        pass

    # Exit a parse tree produced by pdrawParser#stdout.
    def exitStdout(self, ctx:pdrawParser.StdoutContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprToString.
    def enterExprToString(self, ctx:pdrawParser.ExprToStringContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprToString.
    def exitExprToString(self, ctx:pdrawParser.ExprToStringContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprToBool.
    def enterExprToBool(self, ctx:pdrawParser.ExprToBoolContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprToBool.
    def exitExprToBool(self, ctx:pdrawParser.ExprToBoolContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprToInt.
    def enterExprToInt(self, ctx:pdrawParser.ExprToIntContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprToInt.
    def exitExprToInt(self, ctx:pdrawParser.ExprToIntContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprMultDivMod.
    def enterExprMultDivMod(self, ctx:pdrawParser.ExprMultDivModContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprMultDivMod.
    def exitExprMultDivMod(self, ctx:pdrawParser.ExprMultDivModContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprAddSub.
    def enterExprAddSub(self, ctx:pdrawParser.ExprAddSubContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprAddSub.
    def exitExprAddSub(self, ctx:pdrawParser.ExprAddSubContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprSetProperty.
    def enterExprSetProperty(self, ctx:pdrawParser.ExprSetPropertyContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprSetProperty.
    def exitExprSetProperty(self, ctx:pdrawParser.ExprSetPropertyContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprInteger.
    def enterExprInteger(self, ctx:pdrawParser.ExprIntegerContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprInteger.
    def exitExprInteger(self, ctx:pdrawParser.ExprIntegerContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprId.
    def enterExprId(self, ctx:pdrawParser.ExprIdContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprId.
    def exitExprId(self, ctx:pdrawParser.ExprIdContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprPi.
    def enterExprPi(self, ctx:pdrawParser.ExprPiContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprPi.
    def exitExprPi(self, ctx:pdrawParser.ExprPiContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprString.
    def enterExprString(self, ctx:pdrawParser.ExprStringContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprString.
    def exitExprString(self, ctx:pdrawParser.ExprStringContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprParent.
    def enterExprParent(self, ctx:pdrawParser.ExprParentContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprParent.
    def exitExprParent(self, ctx:pdrawParser.ExprParentContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprComp.
    def enterExprComp(self, ctx:pdrawParser.ExprCompContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprComp.
    def exitExprComp(self, ctx:pdrawParser.ExprCompContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprPoint.
    def enterExprPoint(self, ctx:pdrawParser.ExprPointContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprPoint.
    def exitExprPoint(self, ctx:pdrawParser.ExprPointContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprStringConcat.
    def enterExprStringConcat(self, ctx:pdrawParser.ExprStringConcatContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprStringConcat.
    def exitExprStringConcat(self, ctx:pdrawParser.ExprStringConcatContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprColor.
    def enterExprColor(self, ctx:pdrawParser.ExprColorContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprColor.
    def exitExprColor(self, ctx:pdrawParser.ExprColorContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprToReal.
    def enterExprToReal(self, ctx:pdrawParser.ExprToRealContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprToReal.
    def exitExprToReal(self, ctx:pdrawParser.ExprToRealContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprPenOperator.
    def enterExprPenOperator(self, ctx:pdrawParser.ExprPenOperatorContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprPenOperator.
    def exitExprPenOperator(self, ctx:pdrawParser.ExprPenOperatorContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprConvToRad.
    def enterExprConvToRad(self, ctx:pdrawParser.ExprConvToRadContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprConvToRad.
    def exitExprConvToRad(self, ctx:pdrawParser.ExprConvToRadContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprPenUnary.
    def enterExprPenUnary(self, ctx:pdrawParser.ExprPenUnaryContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprPenUnary.
    def exitExprPenUnary(self, ctx:pdrawParser.ExprPenUnaryContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprFunctionCall.
    def enterExprFunctionCall(self, ctx:pdrawParser.ExprFunctionCallContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprFunctionCall.
    def exitExprFunctionCall(self, ctx:pdrawParser.ExprFunctionCallContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprUnary.
    def enterExprUnary(self, ctx:pdrawParser.ExprUnaryContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprUnary.
    def exitExprUnary(self, ctx:pdrawParser.ExprUnaryContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprNew.
    def enterExprNew(self, ctx:pdrawParser.ExprNewContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprNew.
    def exitExprNew(self, ctx:pdrawParser.ExprNewContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprBoolOp.
    def enterExprBoolOp(self, ctx:pdrawParser.ExprBoolOpContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprBoolOp.
    def exitExprBoolOp(self, ctx:pdrawParser.ExprBoolOpContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprStdin.
    def enterExprStdin(self, ctx:pdrawParser.ExprStdinContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprStdin.
    def exitExprStdin(self, ctx:pdrawParser.ExprStdinContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprAssign.
    def enterExprAssign(self, ctx:pdrawParser.ExprAssignContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprAssign.
    def exitExprAssign(self, ctx:pdrawParser.ExprAssignContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprReal.
    def enterExprReal(self, ctx:pdrawParser.ExprRealContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprReal.
    def exitExprReal(self, ctx:pdrawParser.ExprRealContext):
        pass


    # Enter a parse tree produced by pdrawParser#ExprBool.
    def enterExprBool(self, ctx:pdrawParser.ExprBoolContext):
        pass

    # Exit a parse tree produced by pdrawParser#ExprBool.
    def exitExprBool(self, ctx:pdrawParser.ExprBoolContext):
        pass


    # Enter a parse tree produced by pdrawParser#pause.
    def enterPause(self, ctx:pdrawParser.PauseContext):
        pass

    # Exit a parse tree produced by pdrawParser#pause.
    def exitPause(self, ctx:pdrawParser.PauseContext):
        pass


    # Enter a parse tree produced by pdrawParser#assign.
    def enterAssign(self, ctx:pdrawParser.AssignContext):
        pass

    # Exit a parse tree produced by pdrawParser#assign.
    def exitAssign(self, ctx:pdrawParser.AssignContext):
        pass


    # Enter a parse tree produced by pdrawParser#point.
    def enterPoint(self, ctx:pdrawParser.PointContext):
        pass

    # Exit a parse tree produced by pdrawParser#point.
    def exitPoint(self, ctx:pdrawParser.PointContext):
        pass


    # Enter a parse tree produced by pdrawParser#tname.
    def enterTname(self, ctx:pdrawParser.TnameContext):
        pass

    # Exit a parse tree produced by pdrawParser#tname.
    def exitTname(self, ctx:pdrawParser.TnameContext):
        pass



del pdrawParser