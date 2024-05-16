
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
					System.err.println("Error: Color value must be a color value.");
					System.exit(1);
				}
				break;
			case "pressure":
				if (!isNumericType(exprType)) {
					System.err.println("Error: Pressure value must be a real or integer value.");
					System.exit(1);
				}
				break;
			case "thickness":
				if (exprType != Type.INTEGER) {
					System.err.println("Error: Thickness value must be a integer value.");
					System.exit(1);
				}
				break;	
			case "orientation":
				if (!isNumericType(exprType)) {
					System.err.println("Error: Orientation value must be a real or integer value.");
					System.exit(1);
				}
				break;
			case "position":
				if (exprType != Type.POINT) {
					System.err.println("Error: Position value must be a point value.");
					System.exit(1);
				}
				break;
		
			default:
				System.err.println("Error: Invalid property " + property);
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
				System.err.println("Error: Invalid type " + ctx.type.getText());
				System.exit(1);
		}

		String id = ctx.ID().getText();
		if (symbolTable.getSymbol(id) != null) {
			System.err.println("Error: Variable with id " + id + " already exists.");
			System.exit(1);
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
			System.err.println("Error: Pause time must be an integer value.");
			System.exit(1);
		}
		return exprType;
	}

	@Override
	public Type visitStdout(pdrawParser.StdoutContext ctx) {
		Type exprType = visit(ctx.expression());
		if (exprType != Type.STRING) {
			System.err.println("Error: stdout can only output string values.");
			System.exit(1);
		}
		return null;
	}

	@Override
	public Type visitExprToString(pdrawParser.ExprToStringContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isConvertibleToString(exprType)) {
			System.err.println("Error: Conversion to string can only be applied to integer, real, or string values.");
			System.exit(1);
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
         System.err.println("Concatenation requires at least one operand to be a string or convertible to string.");
         System.exit(1);
      }
      return Type.STRING;
  }

	@Override
	public Type visitExprToInt(pdrawParser.ExprToIntContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isConvertibleToNumeric(exprType)) {
			System.err.println("Error: Conversion to integer can only be applied to real or integer values.");
			System.exit(1);
		}
		return Type.INTEGER;
	}

	@Override
	public Type visitExprToReal(pdrawParser.ExprToRealContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isConvertibleToNumeric(exprType)) {
			System.err.println("Error: Conversion to real can only be applied to real or integer values.");
			System.exit(1);
		}
		return Type.REAL;
	}

	@Override
	public Type visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
		Type exprType = visit(ctx.expression(0));
		Type valueType = visit(ctx.expression(1));
		if (exprType != Type.PEN) {
			System.err.println("Error: Pen operator can only be applied to pen values.");
			System.exit(1);
		}
		if (!isNumericType(valueType)) {
			System.err.println("Error: Pen operator value must be a real or integer value.");
			System.exit(1);
		}
		return Type.PEN;
	}

	@Override public Type visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      Type leftType = visit(ctx.expression(0));
      Type rightType = visit(ctx.expression(1));
      if (!isNumericType(leftType) || !isNumericType(rightType)) {
          System.err.println("Operands of '*', '/', '//', or '%' must be numeric.");
          System.exit(1);}
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
			System.err.println("Error: Property assignment can only be applied to pen values.");
			System.exit(1);
		}

		String property = ctx.Property().getText();
		switch (property) {
			case "color":
				if (rightType != Type.COLOR) {
					System.err.println("Error: Color value must be a color value.");
					System.exit(1);
				}
				break;
			case "pressure":
				if (!isNumericType(rightType)) {
					System.err.println("Error: Pressure value must be a real or integer value.");
					System.exit(1);
				}
				break;
			case "thickness":
				if (rightType != Type.INTEGER) {
					System.err.println("Error: Thickness value must be a integer value.");
					System.exit(1);
				}
				break;	
			case "orientation":
				if (!isNumericType(rightType)) {
					System.err.println("Error: Orientation value must be a real or integer value.");
					System.exit(1);
				}
				break;
			case "position":
				if (rightType != Type.POINT) {
					System.err.println("Error: Position value must be a point value.");
					System.exit(1);
				}
				break;
		
			default:
				System.err.println("Error: Invalid property " + property);
				break;

		}
		return null;
	}

	@Override public Type visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
		Type leftType = visit(ctx.expression(0));
		Type rightType = visit(ctx.expression(1));
		if (!isNumericType(leftType) || !isNumericType(rightType)) {
			System.err.println("Operands of '+' or '-' must be numeric.");
			System.exit(1);   
		}
      	return (leftType == Type.REAL || rightType == Type.REAL) ? Type.REAL : Type.INTEGER;
  }

  	@Override public Type visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
		Type exprType = visit(ctx.expression());
		if (exprType != Type.INTEGER && exprType != Type.REAL) {
			System.err.println("Error: Conversion to radian can only be applied to real or integer values.");
			System.exit(1);
		}
		return Type.REAL;
	}

	@Override
	public Type visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
		Type exprType = visit(ctx.expression());
		if (exprType != Type.PEN) {
			System.err.println("Error: Pen Unary operator can only be applied to pen values.");
			System.exit(1);
		}
		return Type.PEN;
	}

	@Override public Type visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
		Type exprType = visit(ctx.expression());
		if (!isNumericType(exprType)) {
			System.err.println("Error: Unary operator can only be applied to real or integer values.");
			System.exit(1);
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
		if (symbolTable.getSymbol(id).getType() != exprType) {
			System.err.println(""); // TODO: accumulate errors
			System.exit(1);
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
			System.err.println("Error: Point values must be integer values.");
			System.exit(1);
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
}
