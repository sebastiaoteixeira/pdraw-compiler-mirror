import symbols.*;
import org.antlr.v4.runtime.ParserRuleContext;

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
				if (exprType.getType() != Type.INTEGER) {
					ErrorHandler.error(getFileName(ctx), "Color value must be a integer value.",
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
				type = new IntegerType();
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

      for (pdrawParser.Declaration_elementContext element : ctx.declaration_element()) {
         String id = element.ID() != null ? element.ID().getText() : element.assign().ID().getText();
         if (symbolTable.getSymbol(id) != null) {
            ErrorHandler.error(getFileName(ctx), "Variable with id " + id + " already exists.",
               ctx.start.getLine(), ctx.start.getCharPositionInLine());
         }
         Symbol variable = new Symbol(id, type);
		   symbolTable.addSymbol(variable);

         visit(element);
      }
		return type;
   }

   @Override public GenericType visitDeclaration_element(pdrawParser.Declaration_elementContext ctx) {
      if (ctx.ID() != null) {
         String id = ctx.ID().getText();
         ctx.identifier = id;
      }
      else if (ctx.assign() != null) {
         String id = ctx.assign().identifier;
         ctx.identifier = id;
      }
      return null;
   }

   @Override public GenericType visitWhile(pdrawParser.WhileContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "While condition must be a boolean value.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
   }

   @Override public GenericType visitUntil(pdrawParser.UntilContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "Until condition must be a boolean value.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
   }

   @Override public GenericType visitFor(pdrawParser.ForContext ctx) {
      // check if the loop range is an integer
      if (visit(ctx.expression(0)).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "Loop range must be a boolean value.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
   }

   @Override public GenericType visitIf(pdrawParser.IfContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "If condition must be a boolean value.", 
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
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
      Type exprType = visit(ctx.expression()).getType();
		if (!isConvertibleToString(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Conversion to string can only be applied to real, integer, or string values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new StringType();
   }

   @Override public GenericType visitExprToBool(pdrawParser.ExprToBoolContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
		if (!isConvertibleToNumeric(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Conversion to integer can only be applied to real, integer, or string values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new IntegerType();
   }

   @Override public GenericType visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
		Type rightType = visit(ctx.expression(1)).getType();
		if (!isNumericType(leftType) || !isNumericType(rightType)) {
			ErrorHandler.error(getFileName(ctx), "Operands of '*' or '/' must be numeric.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());}
		if (ctx.op.getText().equals("//")) {
			return new IntegerType();
		} else if (leftType == Type.REAL || rightType == Type.REAL) {
			return new Real();
		}
		return new IntegerType();
   }

   @Override public GenericType visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
		Type leftType = visit(ctx.expression(0)).getType();
		Type rightType = visit(ctx.expression(1)).getType();
		if (!isNumericType(leftType) || !isNumericType(rightType)) {
			ErrorHandler.error(getFileName(ctx), "Operands of '+' or '-' must be numeric.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine()); 
		}
      return (leftType == Type.REAL || rightType == Type.REAL) ? new IntegerType() : new Real();
   }

   @Override public GenericType visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
		Type rightType = visit(ctx.expression(1)).getType();

		if (leftType != Type.PEN) {
			ErrorHandler.error(getFileName(ctx), "Set property operator can only be applied to pen values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}

		String property = ctx.Property().getText();
		switch (property) {
			case "color":
				if (rightType != Type.INTEGER) {
					ErrorHandler.error(getFileName(ctx), "Color value must be a integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "pressure":
				if (!isNumericType(rightType)) {
					ErrorHandler.error(getFileName(ctx), "Pressure value must be a real or integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "thickness":
				if (rightType != Type.INTEGER) {
					ErrorHandler.error(getFileName(ctx), "Thickness value must be a integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;	
			case "orientation":
				if (!isNumericType(rightType)) {
					ErrorHandler.error(getFileName(ctx), "Orientation value must be a real or integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "position":
				if (rightType != Type.POINT) {
					ErrorHandler.error(getFileName(ctx), "Position value must be a point value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
		
			default:
				ErrorHandler.error(getFileName(ctx), "Invalid property " + property,
					ctx.start.getLine(), ctx.start.getCharPositionInLine());
				break;

		}
		return new Pen();
   }

   @Override public GenericType visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
      return new IntegerType();
   }

   @Override public GenericType visitExprId(pdrawParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();
      Symbol s = symbolTable.getSymbol(id);
      if (s == null) {
         ErrorHandler.error(getFileName(ctx), id + " was not declared.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
         
         return null;
      }

      return s.getGenericType();
   }

   @Override public GenericType visitExprString(pdrawParser.ExprStringContext ctx) {
      return new StringType();
   }

   @Override public GenericType visitExprParent(pdrawParser.ExprParentContext ctx) {
      return visit(ctx.expression());
   }

   @Override public GenericType visitExprComp(pdrawParser.ExprCompContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprPoint(pdrawParser.ExprPointContext ctx) {
      return new Point();
   }

   @Override public GenericType visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      if (leftType != Type.STRING || rightType != Type.STRING) {
         ErrorHandler.error(getFileName(ctx), "Operands of '++' must be strings.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new StringType();
   }

   @Override public GenericType visitExprColor(pdrawParser.ExprColorContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (!isConvertibleToNumeric(exprType)) {
         ErrorHandler.error(getFileName(ctx), "Conversion to real can only be applied to real, integer, or string values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Real();
   }

   @Override public GenericType visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      if (leftType != Type.PEN || !isNumericType(rightType)) {
         ErrorHandler.error(getFileName(ctx), "Operands of pen operator must be pen values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Pen();
   }

   @Override public GenericType visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (!isConvertibleToNumeric(exprType)) {
         ErrorHandler.error(getFileName(ctx), "Conversion to radian can only be applied to real, integer, or string values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Real();
   }

   @Override public GenericType visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.PEN) {
         ErrorHandler.error(getFileName(ctx), "Unary operator can only be applied to pen values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Pen();
   }

   @Override public GenericType visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      GenericType exprType = visit(ctx.expression());
      if (!isNumericType(exprType.getType())) {
         ErrorHandler.error(getFileName(ctx), "Unary operator can only be applied to numeric values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return exprType;
   }

   @Override public GenericType visitExprNew(pdrawParser.ExprNewContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.PENTYPE) {
         ErrorHandler.error(getFileName(ctx), "New operator can only be applied to pen type values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Pen();
   }

   @Override public GenericType visitExprBoolOp(pdrawParser.ExprBoolOpContext ctx) {
      GenericType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public GenericType visitExprStdin(pdrawParser.ExprStdinContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.STRING) {
         ErrorHandler.error(getFileName(ctx), "Stdin operator can only be applied to string values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new StringType();
   }

   @Override public GenericType visitExprAssign(pdrawParser.ExprAssignContext ctx) {
      return visit(ctx.assign());
   }

   @Override public GenericType visitAssign(pdrawParser.AssignContext ctx) {
      String id = ctx.ID().getText();
      ctx.identifier = id;
      Symbol symbol = symbolTable.getSymbol(id);
      if (symbol == null) {
         ErrorHandler.error(getFileName(ctx), "Variable with id " + id + " does not exist.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      GenericType exprType = visit(ctx.expression());
      if (symbol.getType() != exprType.getType()) {
         ErrorHandler.error(getFileName(ctx), "Type mismatch in assignment.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return exprType;
   }

   @Override public GenericType visitExprReal(pdrawParser.ExprRealContext ctx) {
      return new Real();
   }

   @Override public GenericType visitExprBool(pdrawParser.ExprBoolContext ctx) {
      return new BooleanType();
   }

   @Override public GenericType visitPoint(pdrawParser.PointContext ctx) {
      Type exprType1 = visit(ctx.expression(0)).getType();
      Type exprType2 = visit(ctx.expression(1)).getType();
      if (exprType1 != Type.INTEGER || exprType2 != Type.INTEGER) {
         ErrorHandler.error(getFileName(ctx), "Point values must be integer values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Point();
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
