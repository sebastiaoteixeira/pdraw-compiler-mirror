
import symbols.Type;
import symbols.SymbolTable;
import symbols.Symbol;

@SuppressWarnings("CheckReturnValue")
public class semanticVerifier extends pdrawBaseVisitor<Type> {

	private SymbolTable symbolTable = new SymbolTable();

	@Override
	public Type visitProgram(pdrawParser.ProgramContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitStatement(pdrawParser.StatementContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitDefine(pdrawParser.DefineContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitPenDefinition(pdrawParser.PenDefinitionContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitDeclaration(pdrawParser.DeclarationContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitExpression(pdrawParser.ExpressionContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitExecution(pdrawParser.ExecutionContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitPause(pdrawParser.PauseContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitStdout(pdrawParser.StdoutContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitExprToString(pdrawParser.ExprToStringContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
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
      Type exprType = visit(ctx.expr());
      if (stringType != Type.STRING || (exprType != Type.STRING && !isConvertibleToString(exprType))) {
         System.err.println("Concatenation requires at least one operand to be a string or convertible to string.");
         System.exit(1);
      }
      return Type.STRING;
  }

	@Override
	public Type visitExprToInt(pdrawParser.ExprToIntContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitExprToReal(pdrawParser.ExprToRealContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override
	public Type visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override public Type visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      Type leftType = visit(ctx.expr(0));
      Type rightType = visit(ctx.expr(1));
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
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override public Type visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
      Type leftType = visit(ctx.expr(0));
      Type rightType = visit(ctx.expr(1));
      if (!isNumericType(leftType) || !isNumericType(rightType)) {
            System.err.println("Operands of '+' or '-' must be numeric.");
            System.exit(1);   
      }
      return (leftType == Type.REAL || rightType == Type.REAL) ? Type.REAL : Type.INTEGER;
  }

  @Override public Type visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
   Type exprType = visit(ctx.expr());
   if (exprType != Type.INTEGER && exprType != Type.REAL) {
      System.err.println("Error: Conversion to radian can only be applied to real or integer values.");
      System.exit(1);
   }
   return Type.REAL;
}

	@Override
	public Type visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
		Type res = null;
		return visitChildren(ctx);
		// return res;
	}

	@Override public Type visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      Type exprType = visit(ctx.expr());
      if (exprType != Type.REAL && exprType != Type.INTEGER) {
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
		Type exprType = visit(ctx.expr());
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
		return symbolTable.get(ctx.ID().getText());
	}

	@Override
	public Type visitPoint(pdrawParser.PointContext ctx) {
		visitChildren(ctx);
		return null;
	}

   //private function to see if expr is INTEGER or REAL
   private boolean isNumericType(Type type) {
      return type == Type.INTEGER || type == Type.REAL;
  }
  
  //types that can be converted besides string
  private boolean isConvertibleToString(Type type) {
      return type == Type.INTEGER || type == Type.REAL || type == Type.STRING;
  }
}
