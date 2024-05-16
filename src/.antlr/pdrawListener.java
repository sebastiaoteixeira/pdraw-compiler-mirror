// Generated from /home/rmachado/uni/pdraw-t12/src/pdraw.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link pdrawParser}.
 */
public interface pdrawListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link pdrawParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(pdrawParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(pdrawParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(pdrawParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(pdrawParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#define}.
	 * @param ctx the parse tree
	 */
	void enterDefine(pdrawParser.DefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#define}.
	 * @param ctx the parse tree
	 */
	void exitDefine(pdrawParser.DefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#penDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPenDefinition(pdrawParser.PenDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#penDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPenDefinition(pdrawParser.PenDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#propertyDefinition}.
	 * @param ctx the parse tree
	 */
	void enterPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#propertyDefinition}.
	 * @param ctx the parse tree
	 */
	void exitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#canvasDefinition}.
	 * @param ctx the parse tree
	 */
	void enterCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#canvasDefinition}.
	 * @param ctx the parse tree
	 */
	void exitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(pdrawParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(pdrawParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(pdrawParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(pdrawParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#execution}.
	 * @param ctx the parse tree
	 */
	void enterExecution(pdrawParser.ExecutionContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#execution}.
	 * @param ctx the parse tree
	 */
	void exitExecution(pdrawParser.ExecutionContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#pause}.
	 * @param ctx the parse tree
	 */
	void enterPause(pdrawParser.PauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#pause}.
	 * @param ctx the parse tree
	 */
	void exitPause(pdrawParser.PauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#stdout}.
	 * @param ctx the parse tree
	 */
	void enterStdout(pdrawParser.StdoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#stdout}.
	 * @param ctx the parse tree
	 */
	void exitStdout(pdrawParser.StdoutContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprToString}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprToString(pdrawParser.ExprToStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprToString}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprToString(pdrawParser.ExprToStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprString(pdrawParser.ExprStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprString(pdrawParser.ExprStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(pdrawParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(pdrawParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPoint}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPoint(pdrawParser.ExprPointContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPoint}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPoint(pdrawParser.ExprPointContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStringConcat}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprStringConcat(pdrawParser.ExprStringConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStringConcat}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprStringConcat(pdrawParser.ExprStringConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprToInt}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprToInt(pdrawParser.ExprToIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprToInt}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprToInt(pdrawParser.ExprToIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprToReal}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprToReal(pdrawParser.ExprToRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprToReal}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprToReal(pdrawParser.ExprToRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPenOperator}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPenOperator(pdrawParser.ExprPenOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPenOperator}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDivMod}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDivMod(pdrawParser.ExprMultDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDivMod}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(pdrawParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(pdrawParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSetProperty}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSetProperty(pdrawParser.ExprSetPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSetProperty}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprConvToRad}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprConvToRad(pdrawParser.ExprConvToRadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprConvToRad}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprConvToRad(pdrawParser.ExprConvToRadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPenUnary}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPenUnary(pdrawParser.ExprPenUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPenUnary}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnary(pdrawParser.ExprUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnary(pdrawParser.ExprUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNew}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNew(pdrawParser.ExprNewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNew}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNew(pdrawParser.ExprNewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprStdin}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprStdin(pdrawParser.ExprStdinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprStdin}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprStdin(pdrawParser.ExprStdinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAssign}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAssign(pdrawParser.ExprAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAssign}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAssign(pdrawParser.ExprAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprReal(pdrawParser.ExprRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprReal(pdrawParser.ExprRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInteger(pdrawParser.ExprIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInteger(pdrawParser.ExprIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprId(pdrawParser.ExprIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link pdrawParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprId(pdrawParser.ExprIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link pdrawParser#point}.
	 * @param ctx the parse tree
	 */
	void enterPoint(pdrawParser.PointContext ctx);
	/**
	 * Exit a parse tree produced by {@link pdrawParser#point}.
	 * @param ctx the parse tree
	 */
	void exitPoint(pdrawParser.PointContext ctx);
}