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
		return visitChildren(ctx);
	}

   @Override public IType visitStatement(pdrawParser.StatementContext ctx) {
      return visitChildren(ctx);
   }

   @Override public IType visitCompound(pdrawParser.CompoundContext ctx) {
      return visitChildren(ctx);
   }

   @Override public IType visitBlock(pdrawParser.BlockContext ctx) {
      return visitChildren(ctx);
   }

   @Override public IType visitDefine(pdrawParser.DefineContext ctx) {
      return visitChildren(ctx);
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

   @Override public IType visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
      IType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public IType visitDeclaration(pdrawParser.DeclarationContext ctx) {
      IType type = switchFunctionType(ctx.type.getText());

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

   @Override public IType visitDeclaration_element(pdrawParser.Declaration_elementContext ctx) {
      if (ctx.assign() != null)
         visit(ctx.assign());

      return null;
   }

   @Override public IType visitWhile(pdrawParser.WhileContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "While condition must be a boolean value.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
   }

   @Override public IType visitUntil(pdrawParser.UntilContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "Until condition must be a boolean value.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
   }

   @Override public IType visitFor(pdrawParser.ForContext ctx) {
      // check if the loop range is an integer
      if (visit(ctx.expression(0)).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "Loop range must be a boolean value.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
   }

   @Override public IType visitIf(pdrawParser.IfContext ctx) {
      if (visit(ctx.expression()).getType() != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "If condition must be a boolean value.", 
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return null;
   }

   @Override public IType visitExecution(pdrawParser.ExecutionContext ctx) {
      IType res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public IType visitPause(pdrawParser.PauseContext ctx) {
      IType exprType = visit(ctx.expression());
		if (exprType.getType() != Type.INTEGER) {
			ErrorHandler.error(getFileName(ctx), "Pause time must be an integer value.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return exprType;
   }

   @Override public IType visitStdout(pdrawParser.StdoutContext ctx) {
      IType exprType = visit(ctx.expression());
		if (exprType.getType() != Type.STRING && exprType.getType() != Type.PEN && exprType.getType() != Type.INTEGER && exprType.getType() != Type.REAL) {
			ErrorHandler.error(getFileName(ctx), "Stdout value must be convertible to string.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return null;
   }

   @Override public IType visitExprToString(pdrawParser.ExprToStringContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
		if (!isConvertibleToString(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Conversion to string can only be applied to real, integer, or string values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new StringType();
   }

   @Override public IType visitExprToBool(pdrawParser.ExprToBoolContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "Conversion to boolean can only be applied to boolean values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }

      return new BooleanType();
   }

   @Override public IType visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
		if (!isConvertibleToNumeric(exprType)) {
			ErrorHandler.error(getFileName(ctx), "Conversion to integer can only be applied to real, integer, or string values.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());
		}
		return new IntegerType();
   }

   @Override public IType visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
		Type rightType = visit(ctx.expression(1)).getType();
		if (!isNumericType(leftType) || !isNumericType(rightType)) {
			ErrorHandler.error(getFileName(ctx), "Operands of '*' or '/' must be numeric.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine());}
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
		if (!isNumericType(leftType) || !isNumericType(rightType)) {
			ErrorHandler.error(getFileName(ctx), "Operands of '+' or '-' must be numeric.",
				ctx.start.getLine(), ctx.start.getCharPositionInLine()); 
		}
      return (leftType == Type.REAL || rightType == Type.REAL) ? new IntegerType() : new Real();
   }

   @Override public IType visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
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

   @Override public IType visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
      return new IntegerType();
   }

   @Override public IType visitExprId(pdrawParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();
      Symbol s = symbolTable.getSymbol(id);
      if (s == null) {
         ErrorHandler.error(getFileName(ctx), id + " was not declared.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
         
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
         ErrorHandler.error(getFileName(ctx), "Operands of '++' must be strings.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new StringType();
   }

   @Override public IType visitExprColor(pdrawParser.ExprColorContext ctx) {
      return new IntegerType();
   }

   @Override public IType visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (!isConvertibleToNumeric(exprType)) {
         ErrorHandler.error(getFileName(ctx), "Conversion to real can only be applied to real, integer, or string values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Real();
   }

   @Override public IType visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      if (leftType != Type.PEN || !isNumericType(rightType)) {
         ErrorHandler.error(getFileName(ctx), "Operands of pen operator must be pen values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Pen();
   }

   @Override public IType visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (!isConvertibleToNumeric(exprType)) {
         ErrorHandler.error(getFileName(ctx), "Conversion to radian can only be applied to real, integer, or string values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Real();
   }

   @Override public IType visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.PEN) {
         ErrorHandler.error(getFileName(ctx), "Unary operator can only be applied to pen values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Pen();
   }

   @Override public IType visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      IType exprType = visit(ctx.expression());
      if (!isNumericType(exprType.getType())) {
         ErrorHandler.error(getFileName(ctx), "Unary operator can only be applied to numeric values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return exprType;
   }

   @Override public IType visitExprNew(pdrawParser.ExprNewContext ctx) {
      if (ctx.expression() != null) {
         Type exprType = visit(ctx.expression()).getType();
         if (exprType != Type.PENTYPE) {
            ErrorHandler.error(getFileName(ctx), "New operator can only be applied to pen type values.",
               ctx.start.getLine(), ctx.start.getCharPositionInLine());
         }
      }
      return new Pen();
   }

   @Override public IType visitExprBoolOp(pdrawParser.ExprBoolOpContext ctx) {
      Type leftType = visit(ctx.expression(0)).getType();
      Type rightType = visit(ctx.expression(1)).getType();
      if (leftType != Type.BOOLEAN || rightType != Type.BOOLEAN) {
         ErrorHandler.error(getFileName(ctx), "Operands of boolean operators must be boolean values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new BooleanType();
   }

   @Override public IType visitExprStdin(pdrawParser.ExprStdinContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      if (exprType != Type.STRING) {
         ErrorHandler.error(getFileName(ctx), "Stdin operator can only be applied to string values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
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
         ErrorHandler.error(getFileName(ctx), "Variable with id " + id + " does not exist.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      IType exprType = visit(ctx.expression());
      if (symbol.getType() != exprType.getType()) {
         ErrorHandler.error(getFileName(ctx), "Type mismatch in assignment.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
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
         ErrorHandler.error(getFileName(ctx), "Point values must be integer or real values.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Point();
   }

   @Override public IType visitReturn(pdrawParser.ReturnContext ctx) {
      return null;
      //Não tá acabada
   }

   @Override public IType visitFunction(pdrawParser.FunctionContext ctx) {
      String id = ctx.ID().getText();
      IType returnType = switchFunctionType(ctx.type.getText());
      ParameterList parameterSymbols = (ParameterList) visit(ctx.parameter_list());
      IType type = new FunctionType( returnType,parameterSymbols);
      Symbol function = new Symbol(id,type);
      symbolTable.addSymbol(function);

      symbolTable.enterScope();
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
         ErrorHandler.error(getFileName(ctx), "Function with id " + id + " does not exist.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }

      //se estiver a dar problemas revejam esta parte

      FunctionType functionType = (FunctionType) function.getIType();
      ParameterList parameterLists = functionType.getParameterList();
      if (ctx.expression().size() != parameterLists.getParameterSymbols().size()) {
         ErrorHandler.error(getFileName(ctx), "Function call " + id + " has wrong number of arguments.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }

      // e esta

      for (int i = 0; i < ctx.expression().size(); i++) {
         IType exprType = visit(ctx.expression(i));
         IType parameterList = parameterLists.getParameterSymbols().get(i).getIType();
         if (exprType.getType() != parameterList.getType()) {
            ErrorHandler.error(getFileName(ctx), "Function call " + id + " has wrong argument type.",
               ctx.start.getLine(), ctx.start.getCharPositionInLine());
         }
      }

      return functionType.getReturnType();
   }

   @Override public IType visitExprPi(pdrawParser.ExprPiContext ctx) {
      return new Real();
   }

   @Override public IType visitExprSleep(pdrawParser.ExprSleepContext ctx) {
      Type exprType = visit(ctx.expression()).getType();
      visit(ctx.pause());
      if (exprType != Type.PEN) {
         ErrorHandler.error(getFileName(ctx), "Sleep time must be a pen.",
            ctx.start.getLine(), ctx.start.getCharPositionInLine());
      }
      return new Pen();
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
