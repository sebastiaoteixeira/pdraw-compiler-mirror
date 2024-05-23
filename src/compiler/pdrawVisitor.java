// Generated from pdraw.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link pdrawParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface pdrawVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link pdrawParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(pdrawParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(pdrawParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(pdrawParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(pdrawParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#compound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound(pdrawParser.CompoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(pdrawParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefine(pdrawParser.DefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#penDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPenDefinition(pdrawParser.PenDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#propertyDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#canvasDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#parameter_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter_list(pdrawParser.Parameter_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(pdrawParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#declaration_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration_element(pdrawParser.Declaration_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(pdrawParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#while}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(pdrawParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#until}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUntil(pdrawParser.UntilContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(pdrawParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(pdrawParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#execution}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExecution(pdrawParser.ExecutionContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#pause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPause(pdrawParser.PauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#stdout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStdout(pdrawParser.StdoutContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprToString}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprToString(pdrawParser.ExprToStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprToBool}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprToBool(pdrawParser.ExprToBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprToInt}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprToInt(pdrawParser.ExprToIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDivMod}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(pdrawParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSetProperty}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInteger(pdrawParser.ExprIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprId(pdrawParser.ExprIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPi}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPi(pdrawParser.ExprPiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprString(pdrawParser.ExprStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParent(pdrawParser.ExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprComp}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprComp(pdrawParser.ExprCompContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPoint}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPoint(pdrawParser.ExprPointContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprStringConcat}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprColor}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprColor(pdrawParser.ExprColorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprToReal}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprToReal(pdrawParser.ExprToRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPenOperator}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprConvToRad}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPenUnary}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprFunctionCall}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprFunctionCall(pdrawParser.ExprFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnary(pdrawParser.ExprUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNew}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNew(pdrawParser.ExprNewContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprBoolOp}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBoolOp(pdrawParser.ExprBoolOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprStdin}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStdin(pdrawParser.ExprStdinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAssign}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAssign(pdrawParser.ExprAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprReal(pdrawParser.ExprRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprBool}
	 * labeled alternative in {@link pdrawParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBool(pdrawParser.ExprBoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(pdrawParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPoint(pdrawParser.PointContext ctx);
	/**
	 * Visit a parse tree produced by {@link pdrawParser#tname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTname(pdrawParser.TnameContext ctx);
}