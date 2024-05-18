import org.antlr.v4.runtime.ParserRuleContext;
import symbols.Symbol;
import symbols.SymbolTable;
import symbols.Type;




@SuppressWarnings("CheckReturnValue")
public class semanticVerifier extends pdrawBaseVisitor<Type> {

	private SymbolTable symbolTable = new SymbolTable();

	@Override
	public Type visitProgram(pdrawParser.ProgramContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Type visitStatement(pdrawParser.StatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Type visitDefine(pdrawParser.DefineContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Type visitPenDefinition(pdrawParser.PenDefinitionContext ctx) {
		String id = ctx.ID().getText();
		if (symbolTable.getSymbol(id) != null) {
			System.err.println("Error: Pen with id " + id + " already exists.");
			System.exit(1);
		}
		Symbol pen = new Symbol(id, Type.PEN);
		symbolTable.addSymbol(pen);
		return Type.PENTYPE;
	}

	@Override
	public Type visitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx) {
		String property = ctx.Property().getText();
		Type exprType = visit(ctx.expression());

		switch (property) {
			case "color":
				if (exprType != Type.COLOR) {
					ErrorHandler.error(getFileName(ctx), "Color value must be a color value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "pressure":
				if (!isNumericType(exprType)) {
					ErrorHandler.error(getFileName(ctx), "Pressure value must be a real or integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "thickness":
				if (exprType != Type.INTEGER) {
					ErrorHandler.error(getFileName(ctx), "Thickness value must be a integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;	
			case "orientation":
				if (!isNumericType(exprType)) {
					ErrorHandler.error(getFileName(ctx), "Orientation value must be a real or integer value.",
						ctx.start.getLine(), ctx.start.getCharPositionInLine());
				}
				break;
			case "position":
				if (exprType != Type.POINT) {
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

	@Override
	public Type visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Type visitDeclaration(pdrawParser.DeclarationContext ctx) {
		Type type = null;
		switch (ctx.type.getText()) {
			case "pen":
				type = Type.PEN;
				break;
			case "real":
				type = Type.REAL;
				break;
			case "int":
				type = Type.INTEGER;
				break;
			case "string":
				type = Type.STRING;
				break;
			case "point":
				type = Type.POINT;
				break;
			default:
				ErrorHandler.error(getFileName(ctx), "Invalid type " + ctx.type.getText(),
					ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}

		String id = ctx.ID().getText();
		if (symbolTable.getSymbol(id) != null) {
			ErrorHandler.error(getFileName(ctx), "Variable with id " + id + " already exists.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}

		Symbol variable = new Symbol(id, type);
		symbolTable.addSymbol(variable);
		
		return type;
	}

	@Override
	public Type visitExecution(pdrawParser.ExecutionContext ctx) {
		return visit(ctx.expression());
	}

	@Override
	public Type visitPause(pdrawParser.PauseContext ctx) {
		Type exprType = visit(ctx.expression());
		if (exprType != Type.INTEGER) {
			ErrorHandler.error(getFileName(ctx), "Pause time must be an integer value.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return exprType;
	}

	@Override
	public Type visitStdout(pdrawParser.StdoutContext ctx) {
		Type exprType = visit(ctx.expression());
		if (exprType != Type.STRING) {
			ErrorHandler.error(getFileName(ctx), "Stdout value must be a string value.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return null;
	}

	@Override
	public Type visitExprToString(pdrawParser.ExprToStringContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isConvertibleToString(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Conversion to string can only be applied to real, integer, or string values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return Type.STRING;
	}

	@Override
	public Type visitExprString(pdrawParser.ExprStringContext ctx) {
		return Type.STRING;
	}

	@Override
	public Type visitExprParent(pdrawParser.ExprParentContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Type visitExprPoint(pdrawParser.ExprPointContext ctx) {
		return Type.POINT;
	}

	@Override public Type visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      Type stringType = visit(ctx.String());
      Type exprType = visit(ctx.expression());
      if (stringType != Type.STRING || (exprType != Type.STRING && !isConvertibleToString(exprType))) {
         ErrorHandler.error(getFileName(ctx), "Operands of '+' must be string values.",
			ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return Type.STRING;
  }

	@Override
	public Type visitExprToInt(pdrawParser.ExprToIntContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isConvertibleToNumeric(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Conversion to integer can only be applied to real, integer, or string values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return Type.INTEGER;
	}

	@Override
	public Type visitExprToReal(pdrawParser.ExprToRealContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isConvertibleToNumeric(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Conversion to real can only be applied to real, integer, or string values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return Type.REAL;
	}

	@Override
	public Type visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
		Type exprType = visit(ctx.expression(0));
		Type valueType = visit(ctx.expression(1));
		if (exprType != Type.PEN) {
			ErrorHandler.error(getFileName(ctx), "Pen operator can only be applied to pen values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		if (!isNumericType(valueType)) {
			ErrorHandler.error(getFileName(ctx), "Pen operator value must be a real or integer value.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return Type.PEN;
	}

	@Override public Type visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      Type leftType = visit(ctx.expression(0));
      Type rightType = visit(ctx.expression(1));
      if (!isNumericType(leftType) || !isNumericType(rightType)) {
          ErrorHandler.error(getFileName(ctx), "Operands of '*' or '/' must be numeric.",
			ctx.start.getLine(), ctx.start.getCharPositionInLine());}
      if (ctx.op.getText().equals("//")) {
          return Type.INTEGER;
      } else if (leftType == Type.REAL || rightType == Type.REAL) {
          return Type.REAL;
      }
      return Type.INTEGER;
  }

	@Override
	public Type visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
		Type leftType = visit(ctx.expression(0));
		Type rightType = visit(ctx.expression(1));

		if (leftType != Type.PEN) {
			ErrorHandler.error(getFileName(ctx), "Set property operator can only be applied to pen values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}

		String property = ctx.Property().getText();
		switch (property) {
			case "color":
				if (rightType != Type.COLOR) {
					ErrorHandler.error(getFileName(ctx), "Color value must be a color value.",
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
		return null;
	}

	@Override public Type visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
		Type leftType = visit(ctx.expression(0));
		Type rightType = visit(ctx.expression(1));
		if (!isNumericType(leftType) || !isNumericType(rightType)) {
			ErrorHandler.error(getFileName(ctx), "Operands of '+' or '-' must be numeric.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine()); 
		}
      	return (leftType == Type.REAL || rightType == Type.REAL) ? Type.REAL : Type.INTEGER;
  }

  	@Override public Type visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
		Type exprType = visit(ctx.expression());
		if (exprType != Type.INTEGER && exprType != Type.REAL) {
			ErrorHandler.error(getFileName(ctx), "Conversion to radian can only be applied to real or integer values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return Type.REAL;
	}

	@Override
	public Type visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
		Type exprType = visit(ctx.expression());
		if (exprType != Type.PEN) {
			ErrorHandler.error(getFileName(ctx), "Pen unary operator can only be applied to pen values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return Type.PEN;
	}

	@Override public Type visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isNumericType(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Unary operator can only be applied to numeric values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return exprType;
   }

	@Override
	public Type visitExprNew(pdrawParser.ExprNewContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Type visitExprStdin(pdrawParser.ExprStdinContext ctx) {
		return Type.STRING;
	}

	@Override
	public Type visitExprAssign(pdrawParser.ExprAssignContext ctx) {
		String id = ctx.ID().getText();
		Type exprType = visit(ctx.expression());
		if (symbolTable.getSymbol(id) == null) {
			// Implicit declaration
			Symbol variable = new Symbol(id, exprType);
			symbolTable.addSymbol(variable);

			ErrorHandler.warning(getFileName(ctx), "Implicit declaration of variable " + id,
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
				
		} else if (symbolTable.getSymbol(id).getType() != exprType) {
			ErrorHandler.error(getFileName(ctx), "Type mismatch in assignment.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return null;
	}

	@Override
	public Type visitExprReal(pdrawParser.ExprRealContext ctx) {
		return Type.REAL;
	}

	@Override
	public Type visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
		return Type.INTEGER;
	}

	@Override
	public Type visitExprId(pdrawParser.ExprIdContext ctx) {
		return symbolTable.getSymbol(ctx.ID().getText()).getType();
	}

	@Override
	public Type visitPoint(pdrawParser.PointContext ctx) {
		if (visit(ctx.expression(0)) != Type.INTEGER || visit(ctx.expression(1)) != Type.INTEGER) {
			ErrorHandler.error(getFileName(ctx), "Point values must be integer values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return Type.POINT;
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
