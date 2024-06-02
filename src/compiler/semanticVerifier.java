import symbols.*;

import java.util.function.Function;

import org.antlr.v4.runtime.ParserRuleContext;

@SuppressWarnings("CheckReturnValue")
public class semanticVerifier extends pdrawBaseVisitor<IType> {

   private SymbolTable symbolTable = new SymbolTable();

   public IType switchFunctionType(String name){
      IType type = null;
		switch (name) {
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
         case "bool":
            type = new BooleanType();
            break;
      }

      return type;
   }

   @Override
	public IType visitProgram(pdrawParser.ProgramContext ctx) {
		visitChildren(ctx);
      return null;
	}

   @Override public IType visitStatement(pdrawParser.StatementContext ctx) {
      visitChildren(ctx);
      return null;
   }

   @Override public IType visitCompound(pdrawParser.CompoundContext ctx) {
      visitChildren(ctx);
      return null;
   }

   @Override public IType visitBlock(pdrawParser.BlockContext ctx) {
      symbolTable.enterScope();
      visitChildren(ctx);
      symbolTable.exitScope();
      return null;
   }

   @Override public IType visitDefine(pdrawParser.DefineContext ctx) {
      visitChildren(ctx);
      return null;
   }

   @Override public IType visitPenDefinition(pdrawParser.PenDefinitionContext ctx) {
      String id = ctx.ID().getText();
      IType type = new PenType();
		Symbol penType = new Symbol(id, type);
		symbolTable.addSymbol(penType);
		return type;
   }

   @Override public IType visitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx) {
      String property = ctx.Property().getText();
		IType exprType = visit(ctx.expression());

		switch (property) {
			case "color":
				if (exprType.getType() != Type.INTEGER) {
					ErrorHandler.error("Color value must be a integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
			case "pressure":
				if (!isNumericType(exprType.getType())) {
					ErrorHandler.error("Pressure value must be a real or integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
			case "thickness":
				if (exprType.getType() != Type.INTEGER) {
					ErrorHandler.error("Thickness value must be a integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;	
			case "orientation":
				if (!isNumericType(exprType.getType())) {
					ErrorHandler.error("Orientation value must be a real or integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
			case "position":
				if (exprType.getType() != Type.POINT) {
					ErrorHandler.error("Position value must be a point value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
		
			default:
				ErrorHandler.error("Invalid property " + property, ctx, symbolTable.getCurrentFunction());
				break;
		}
		return exprType;
	}

   @Override public IType visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
      String id = ctx.ID().getText();
      IType title = visit(ctx.expression(0));
      IType dimension = visit(ctx.expression(1));

      if (title.getType() != Type.STRING) {
         ErrorHandler.error("Canvas title must be a string value.", ctx, symbolTable.getCurrentFunction());
      }

      if (dimension.getType() != Type.POINT) {
         ErrorHandler.error("Canvas dimension must be a point value.", ctx, symbolTable.getCurrentFunction());
      }

      symbolTable.addSymbol(new Symbol(id, new Canvas()));

      return null;
   }

   @Override public IType visitDeclaration(pdrawParser.DeclarationContext ctx) {
      IType type = switchFunctionType(ctx.type.getText());

      for (pdrawParser.Declaration_elementContext element : ctx.declaration_element()) {
         String id = element.ID() != null ? element.ID().getText() : element.assign().ID().getText();
         if (symbolTable.getSymbol(id) != null) {
            ErrorHandler.error("Variable with id " + id + " already exists.", ctx, symbolTable.getCurrentFunction());
         } else {
            Symbol variable = new Symbol(id, type, ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
            symbolTable.addSymbol(variable);
         }

         visit(element);
      }
		return type;
   }

   @Override public IType visitDeclaration_element(pdrawParser.Declaration_elementContext ctx) {
      if (ctx.assign() != null)
         visit(ctx.assign());

      return null;
   }

   @Override public IType visitWhile(pdrawParser.WhileContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error("While condition must be a boolean value.", ctx, symbolTable.getCurrentFunction());
      }
      return null;
   }

   @Override public IType visitUntil(pdrawParser.UntilContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error("Until condition must be a boolean value.", ctx, symbolTable.getCurrentFunction());
      }
      return null;
   }

   @Override public IType visitFor(pdrawParser.ForContext ctx) {
      // check if the loop range is an integer
      if (visit(ctx.expression(0)).getType() != Type.BOOLEAN) {
         ErrorHandler.error("Loop range must be a boolean value.", ctx, symbolTable.getCurrentFunction());
      }
      return null;
   }

   @Override public IType visitIf(pdrawParser.IfContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error("If condition must be a boolean value.", ctx, symbolTable.getCurrentFunction());
      }
      return null;
   }

   @Override public IType visitExecution(pdrawParser.ExecutionContext ctx) {
      String id = ctx.ID().getText();
      Symbol s = symbolTable.getSymbol(id);
      if (s == null) {
         ErrorHandler.error(id + " was not declared.", ctx, symbolTable.getCurrentFunction());
      }
      if (s.getType() != Type.PEN) {
         ErrorHandler.error("Execution must be applied to pen values.", ctx, symbolTable.getCurrentFunction());
      }

      IType exprType = visit(ctx.expression());
      if (exprType.getType() != Type.STRING) {
         ErrorHandler.error("Execution filename must be a string value.", ctx, symbolTable.getCurrentFunction());
      }

      return null;
   }

   @Override public IType visitPause(pdrawParser.PauseContext ctx) {
      IType exprType = visit(ctx.expression());
		if (exprType.getType() != Type.INTEGER) {
			ErrorHandler.error("Pause time must be an integer value.", ctx, symbolTable.getCurrentFunction());
		}
		return exprType;
   }

   @Override public IType visitStdout(pdrawParser.StdoutContext ctx) {
      IType exprType = visit(ctx.expression());
		if (exprType.getType() != Type.STRING && exprType.getType() != Type.PEN && exprType.getType() != Type.INTEGER && exprType.getType() != Type.REAL) {
			ErrorHandler.error("Stdout value must be convertible to string.", ctx, symbolTable.getCurrentFunction());
		}
		return null;
   }

   @Override public IType visitExprToString(pdrawParser.ExprToStringContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
		if (!isConvertibleToString(exprType)) {
			ErrorHandler.error("Conversion to string can only be applied to real, integer, or string values.", ctx, symbolTable.getCurrentFunction());
		}
		return new StringType();
   }

   @Override public IType visitExprToBool(pdrawParser.ExprToBoolContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.BOOLEAN && exprType != Type.INTEGER && exprType != Type.REAL) {
         ErrorHandler.error("Conversion to boolean can only be applied to boolean values.", ctx, symbolTable.getCurrentFunction());
      }

      return new BooleanType();
   }

   @Override public IType visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
		if (!isConvertibleToNumeric(exprType)) {
			ErrorHandler.error("Conversion to integer can only be applied to real, integer, or string values.", ctx, symbolTable.getCurrentFunction());
		}
		return new IntegerType();
   }

   @Override public IType visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
		Type rightType = visit(ctx.expression(1)).getType();
		if (!isNumericType(leftType) || !isNumericType(rightType)) {
			ErrorHandler.error("Operands of '*' or '/' must be numeric.", ctx, symbolTable.getCurrentFunction());
      }
		if (ctx.op.getText().equals("//") || ctx.op.getText().equals("\\\\")) {
			return new IntegerType();
		} else if (leftType == Type.REAL || rightType == Type.REAL) {
			return new Real();
      } else if (ctx.op.getText().equals("/")){
         return new Real();
		}

		return new IntegerType();
   }

   @Override public IType visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
		Type leftType = visit(ctx.expression(0)).getType();
		Type rightType = visit(ctx.expression(1)).getType();
		if (!((isNumericType(leftType) && isNumericType(rightType)) || (leftType == Type.PEN && rightType == Type.POINT) || (leftType == Type.POINT && rightType == Type.POINT))) {
			ErrorHandler.error("Operands of '+' or '-' must be numeric.", ctx, symbolTable.getCurrentFunction());
		}
      return (leftType == Type.PEN ? new Pen() : leftType == Type.POINT ? new Point() : (leftType == Type.REAL || rightType == Type.REAL) ? new Real() : new IntegerType());
   }

   @Override public IType visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
		Type rightType = visit(ctx.expression(1)).getType();

		if (leftType != Type.PEN) {
			ErrorHandler.error("Set property operator can only be applied to pen values.", ctx, symbolTable.getCurrentFunction());
		}

		String property = ctx.Property().getText();
		switch (property) {
			case "color":
				if (rightType != Type.INTEGER) {
					ErrorHandler.error("Color value must be a integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
			case "pressure":
				if (!isNumericType(rightType)) {
					ErrorHandler.error("Pressure value must be a real or integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
			case "thickness":
				if (rightType != Type.INTEGER) {
					ErrorHandler.error("Thickness value must be a integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;	
			case "orientation":
				if (!isNumericType(rightType)) {
					ErrorHandler.error("Orientation value must be a real or integer value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
			case "position":
				if (rightType != Type.POINT) {
					ErrorHandler.error("Position value must be a point value.", ctx, symbolTable.getCurrentFunction());
				}
				break;
		
			default:
				ErrorHandler.error("Invalid property " + property, ctx, symbolTable.getCurrentFunction());
				break;

		}
		return new Pen();
   }

   @Override public IType visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
      return new IntegerType();
   }

   @Override public IType visitExprId(pdrawParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();
      Symbol s = symbolTable.getSymbol(id);
      if (s == null) {
         ErrorHandler.error(id + " was not declared.", ctx, symbolTable.getCurrentFunction());

         return null;
      }

      return s.getIType();
   }

   @Override public IType visitExprString(pdrawParser.ExprStringContext ctx) {
      return new StringType();
   }

   @Override public IType visitExprParent(pdrawParser.ExprParentContext ctx) {
      return visit(ctx.expression());
   }

   @Override public IType visitExprComp(pdrawParser.ExprCompContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      return new BooleanType();
   }

   @Override public IType visitExprPoint(pdrawParser.ExprPointContext ctx) {
      return new Point();
   }

   @Override public IType visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      if (leftType != Type.STRING || rightType != Type.STRING) {
         ErrorHandler.error("Operands of '++' must be strings.", ctx, symbolTable.getCurrentFunction());
      }
      return new StringType();
   }

   @Override public IType visitExprColor(pdrawParser.ExprColorContext ctx) {
      return new IntegerType();
   }

   @Override public IType visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (!isConvertibleToNumeric(exprType)) {
         ErrorHandler.error("Conversion to real can only be applied to real, integer, or string values.", ctx, symbolTable.getCurrentFunction());
      }
      return new Real();
   }

   @Override public IType visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      if (leftType != Type.PEN || !isNumericType(rightType)) {
         ErrorHandler.error("Operands of pen operator must be pen values.", ctx, symbolTable.getCurrentFunction());
      }
      return new Pen();
   }

   @Override public IType visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (!isConvertibleToNumeric(exprType)) {
         ErrorHandler.error("Conversion to radian can only be applied to real, integer, or string values.", ctx, symbolTable.getCurrentFunction());
      }
      return new Real();
   }

   @Override public IType visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.PEN) {
         ErrorHandler.error("Unary operator can only be applied to pen values.", ctx, symbolTable.getCurrentFunction());
      }
      return new Pen();
   }

   @Override public IType visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      IType exprType = visit(ctx.expression());
      if (!isNumericType(exprType.getType())) {
         ErrorHandler.error("Unary operator can only be applied to numeric values.", ctx, symbolTable.getCurrentFunction());
      }
      return exprType;
   }

   @Override public IType visitExprNew(pdrawParser.ExprNewContext ctx) {
      if (ctx.expression() != null) {
         Type exprType = visit(ctx.expression()).getType();
         if (exprType != Type.PENTYPE) {
            ErrorHandler.error("New operator can only be applied to pen type values.", ctx, symbolTable.getCurrentFunction());
         }
      }
      return new Pen();
   }

   @Override public IType visitExprBoolOp(pdrawParser.ExprBoolOpContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      if (leftType != Type.BOOLEAN || rightType != Type.BOOLEAN) {
         ErrorHandler.error("Operands of boolean operators must be boolean values.", ctx, symbolTable.getCurrentFunction());
      }
      return new BooleanType();
   }

   @Override public IType visitExprStdin(pdrawParser.ExprStdinContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.STRING) {
         ErrorHandler.error("Stdin operator can only be applied to string values.", ctx, symbolTable.getCurrentFunction());
      }
      return new StringType();
   }

   @Override public IType visitExprAssign(pdrawParser.ExprAssignContext ctx) {
      return visit(ctx.assign());
   }

   @Override public IType visitAssign(pdrawParser.AssignContext ctx) {
      String id = ctx.ID().getText();
      Symbol symbol = symbolTable.getSymbol(id);
      if (symbol == null) {
         ErrorHandler.warning("Variable with id " + id + " was not declared.", ctx, symbolTable.getCurrentFunction());

         // Implicit declaration
         symbol = new Symbol(id, new IntegerType(), ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
         symbolTable.addSymbol(symbol);
      }
      IType exprType = visit(ctx.expression());
      if (!hasImplicitConvertion(exprType.getType(), symbol.getIType().getType())) {
         ErrorHandler.error("Type mismatch in assignment. Cannot assign " + exprType.getType() + " to " + symbol.getIType().getType() + ".", ctx, symbolTable.getCurrentFunction());
         if (symbol.getCodeLocation() != null)
            ErrorHandler.info("Symbol `\033[1m" + id + "\033[0m` was declared at line " + symbol.getCodeLocation().getStartLine() + " column " + symbol.getCodeLocation().getStartColumn(), ctx, symbolTable.getCurrentFunction(), symbol);
      }
      return exprType;
   }

   @Override public IType visitExprReal(pdrawParser.ExprRealContext ctx) {
      return new Real();
   }

   @Override public IType visitExprBool(pdrawParser.ExprBoolContext ctx) {
      return new BooleanType();
   }

   @Override public IType visitPoint(pdrawParser.PointContext ctx) {
      Type exprType1 = visit(ctx.expression(0)).getType();
      Type exprType2 = visit(ctx.expression(1)).getType();
      if (!isNumericType(exprType1) || !isNumericType(exprType2)) {
         ErrorHandler.error("Point values must be integer or real values.", ctx, symbolTable.getCurrentFunction());
      }
      return new Point();
   }

   @Override public IType visitReturn(pdrawParser.ReturnContext ctx) {
      Symbol function = symbolTable.getCurrentFunction();
      if (function == null) {
         ErrorHandler.error("Return statement must be inside a function.", ctx, symbolTable.getCurrentFunction());
      }

      IType returnType = function.getReturnType();
      IType exprType = visit(ctx.expression());
      if (!hasImplicitConvertion(exprType.getType(), returnType.getType())) {
         ErrorHandler.error("Return type " + exprType.getType() + " does not match function type " + returnType.getType() + ".", ctx, symbolTable.getCurrentFunction());
      }
      return null;
   }

   @Override public IType visitFunction(pdrawParser.FunctionContext ctx) {
      String id = ctx.ID().getText();
      IType returnType = switchFunctionType(ctx.type.getText());

      ParameterList parameterSymbols;
      if (ctx.parameter_list() != null)
         parameterSymbols = (ParameterList) visit(ctx.parameter_list());
      else
         parameterSymbols = new ParameterList(); // empty parameter list

      IType type = new FunctionType(returnType, parameterSymbols);
      Symbol function = new Symbol(id, type, ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
      symbolTable.addSymbol(function);

      symbolTable.enterScope(function);
      for (Symbol parameterSymbol : parameterSymbols.getParameterSymbols()) {
         symbolTable.addSymbol(parameterSymbol);
      }

      visit(ctx.statement());
      symbolTable.exitScope();
      return null;
   }

   @Override public IType visitParameter(pdrawParser.ParameterContext ctx) {
      ctx.id = ctx.ID().getText();
      IType type = switchFunctionType(ctx.type.getText());
      
      return type;
   }

   @Override public IType visitParameter_list(pdrawParser.Parameter_listContext ctx){
      ParameterList res = new ParameterList();

      for (pdrawParser.ParameterContext parameter : ctx.parameter()) {
         IType parType = visit(parameter);
         res.add(new Symbol(parameter.id, parType));
      }

      return res;
   }

   @Override public IType visitExprFunctionCall(pdrawParser.ExprFunctionCallContext ctx){
      String id = ctx.ID().getText();
      Symbol function = symbolTable.getSymbol(id);
      if (function == null) {
         ErrorHandler.error("Function with id " + id + " does not exist.", ctx, symbolTable.getCurrentFunction());
      }

      FunctionType functionType = (FunctionType) function.getIType();
      ParameterList parameterLists = functionType.getParameterList();
      if (ctx.expression().size() != parameterLists.getParameterSymbols().size()) {
         ErrorHandler.error("Function call " + id + " has wrong number of arguments.", ctx, symbolTable.getCurrentFunction());
      }

      for (int i = 0; i < ctx.expression().size(); i++) {
         IType exprType = visit(ctx.expression(i));
         IType parameterList = parameterLists.getParameterSymbols().get(i).getIType();
         if (!hasImplicitConvertion(exprType.getType(), parameterList.getType())) {
            ErrorHandler.error("Function call " + id + " has wrong argument type.", ctx, symbolTable.getCurrentFunction());
         }
      }

      return functionType.getReturnType();
   }

   @Override public IType visitExprPi(pdrawParser.ExprPiContext ctx) {
      return new Real();
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

   private boolean hasImplicitConvertion(Type from, Type to) {
      return from == to || (from == Type.INTEGER && to == Type.REAL);
   }
   
}
