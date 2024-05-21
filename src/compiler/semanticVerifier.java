import compiler.symbols.*;
import compiler.symbols.Integer;

@SuppressWarnings("CheckReturnValue")
public class semanticVerifier extends pdrawBaseVisitor<GenericType> {

   private SymbolTable symbolTable = new SymbolTable();

   @Override
	public GenericType visitProgram(pdrawParser.ProgramContext ctx) {
		return visitChildren(ctx);
	}

   @Override public GenericType visitStatement(pdrawParser.StatementContext ctx) {
      return visitChildren(ctx);
   }

   @Override public GenericType visitCompound(pdrawParser.CompoundContext ctx) {
      return visitChildren(ctx);
   }

   @Override public GenericType visitBlock(pdrawParser.BlockContext ctx) {
      return visitChildren(ctx);
   }

   @Override public GenericType visitDefine(pdrawParser.DefineContext ctx) {
      return visitChildren(ctx);
   }

   @Override public GenericType visitPenDefinition(pdrawParser.PenDefinitionContext ctx) {
      String id = ctx.ID().getText();
      GenericType type = new PenType();
		Symbol penType = new Symbol(id, type);
		symbolTable.addSymbol(penType);
		return type;
   }

   @Override public GenericType visitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx) {
      String property = ctx.Property().getText();
		GenericType exprType = visit(ctx.expression());

		switch (property) {
			case "color":
				if (exprType.getType() != Type.COLOR) {
					ErrorHandler.error(getFileName(ctx), "Color value must be a color value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "pressure":
				if (!isNumericType(exprType.getType())) {
					ErrorHandler.error(getFileName(ctx), "Pressure value must be a real or integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "thickness":
				if (exprType.getType() != Type.INTEGER) {
					ErrorHandler.error(getFileName(ctx), "Thickness value must be a integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;	
			case "orientation":
				if (!isNumericType(exprType.getType())) {
					ErrorHandler.error(getFileName(ctx), "Orientation value must be a real or integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "position":
				if (exprType.getType() != Type.POINT) {
					ErrorHandler.error(getFileName(ctx), "Position value must be a point value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
		
			default:
				ErrorHandler.error(getFileName(ctx), "Invalid property " + property,
					ctx.start.getLine(), ctx.start.getCharPositionInLine());
				break;
		}
		return exprType;
	}

   @Override public GenericType visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitDeclaration(pdrawParser.DeclarationContext ctx) {
      GenericType type = null;
		switch (ctx.type.getText()) {
			case "pen":
				type = new Pen();
				break;
			case "real":
				type = new Real();
				break;
			case "int":
				type = new Integer();
				break;
			case "string":
				type = new StringType();
				break;
			case "point":
				type = new Point();
				break;
			default:
				ErrorHandler.error(getFileName(ctx), "Invalid type " + ctx.type.getText(),
					ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}

		StringType id = ctx.ID().getText();
		if (symbolTable.getSymbol(id) != null) {
			ErrorHandler.error(getFileName(ctx), "Variable with id " + id + " already exists.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}

		Symbol variable = new Symbol(id, type);
		symbolTable.addSymbol(variable);
		
		return type;
   }

   @Override public GenericType visitWhile(pdrawParser.WhileContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitUntil(pdrawParser.UntilContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitFor(pdrawParser.ForContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitIf(pdrawParser.IfContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExecution(pdrawParser.ExecutionContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitPause(pdrawParser.PauseContext ctx) {
      GenericType exprType = visit(ctx.expression());
		if (exprType.getType() != Type.INTEGER) {
			ErrorHandler.error(getFileName(ctx), "Pause time must be an integer value.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return exprType;
   }

   @Override public GenericType visitStdout(pdrawParser.StdoutContext ctx) {
      GenericType exprType = visit(ctx.expression());
		if (exprType.getType() != Type.STRING) {
			ErrorHandler.error(getFileName(ctx), "Stdout value must be a string value.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return null;
   }

   @Override public GenericType visitExprToString(pdrawParser.ExprToStringContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprToBool(pdrawParser.ExprToBoolContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprId(pdrawParser.ExprIdContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprString(pdrawParser.ExprStringContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprParent(pdrawParser.ExprParentContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprComp(pdrawParser.ExprCompContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprPoint(pdrawParser.ExprPointContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprColor(pdrawParser.ExprColorContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprNew(pdrawParser.ExprNewContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprBoolOp(pdrawParser.ExprBoolOpContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprStdin(pdrawParser.ExprStdinContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprAssign(pdrawParser.ExprAssignContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprReal(pdrawParser.ExprRealContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprBool(pdrawParser.ExprBoolContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitPoint(pdrawParser.PointContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitBoolean(pdrawParser.BooleanContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   // private function to see if expr is INTEGER or REAL
	private boolean isNumericType(Type type) {
		return type == Type.INTEGER || type == Type.REAL;
	}
  
	// types that can be converted besides string
	private boolean isConvertibleToString(Type type) {
		return type == Type.INTEGER || type == Type.REAL || type == Type.STRING;
	}

	// types that can be converted to numeric
	private boolean isConvertibleToNumeric(Type type) {
		return type == Type.INTEGER || type == Type.REAL || type == Type.STRING;
	}

	private String getFileName(ParserRuleContext ctx) {
		return ctx.getStart().getInputStream().getSourceName();
	}
}
