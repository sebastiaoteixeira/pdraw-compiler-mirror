import org.stringtemplate.v4.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
   public class codeGen extends pdrawBaseVisitor<ST> {

   private STGroup templates = new STGroupFile("templates.stg");

   // HashMap with color names and their respective #HEX values
   private static final HashMap<String, String> colors = new HashMap<String, String>() {{
      put("white", "#FFFFFF");
      put("black", "#000000");
      put("green", "#00FF00");
      put("red", "#FF0000");
      put("blue", "#0000FF");
      put("yellow", "#FFFF00");
   }};

   @Override
   public ST visitProgram(pdrawParser.ProgramContext ctx) {
      ST mainTemplate = templates.getInstanceOf("main");
      mainTemplate.add("compound", visit(ctx.compound()).render());

      // Escrever a saída renderizada em um arquivo chamado "output.py"
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.py"))) {
         writer.write(mainTemplate.render());
      } catch (IOException e) {
         e.printStackTrace();
      }

      return mainTemplate;
   }

   @Override public ST visitFunction(pdrawParser.FunctionContext ctx) {
      ST functionTemplate = templates.getInstanceOf("function");
      functionTemplate.add("name", ctx.ID().getText());
      functionTemplate.add("parameter_list", visit(ctx.parameter_list()).render());
      functionTemplate.add("statement", visit(ctx.statement()).render());
      return functionTemplate;
   }

   @Override public ST visitReturn(pdrawParser.ReturnContext ctx) {
      ST returnTemplate = templates.getInstanceOf("return");
      returnTemplate.add("expression", visit(ctx.expression()).render());
      return returnTemplate;
   }

   @Override public ST visitParameter_list(pdrawParser.Parameter_listContext ctx) {
      ST parameterList = templates.getInstanceOf("parameter_list");
      for (pdrawParser.ParameterContext parameter : ctx.parameter()) {
         parameterList.add("parameter", visit(parameter).render());
      }
      return parameterList;
   }

   @Override public ST visitExprFunctionCall(pdrawParser.ExprFunctionCallContext ctx) {
      ST functionCall = templates.getInstanceOf("functionCall");
      functionCall.add("ID", ctx.ID().getText());
      for (pdrawParser.ExpressionContext expression : ctx.expression()) {
         functionCall.add("expression", visit(expression).render());
      }
      return functionCall;
   }

   @Override public ST visitParameter(pdrawParser.ParameterContext ctx) {
      ST parameter = templates.getInstanceOf("ID");
      parameter.add("ID", ctx.ID().getText());
      return parameter;
   }

   @Override public ST visitStatement(pdrawParser.StatementContext ctx) {
      ST statement = visit(ctx.getChild(0));
      return statement;
   }

   @Override public ST visitCompound(pdrawParser.CompoundContext ctx) {
      ST mainTemplate = templates.getInstanceOf("compoundContext");

      // Visit all the children of the program
      for (pdrawParser.StatementContext statement : ctx.statement()) {
         ST code = visit(statement);
         if (code != null) {
            mainTemplate.add("statement", code.render());
         }
      }

      return mainTemplate;
   }

   @Override public ST visitBlock(pdrawParser.BlockContext ctx) {
      return visit(ctx.compound());
   }

   @Override public ST visitDefine(pdrawParser.DefineContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitPenDefinition(pdrawParser.PenDefinitionContext ctx) {
      ST penTemplate = templates.getInstanceOf("pentype");
      //passar o pentypename(id), dentro de loop ver todas as properties e adicionar ao penTemplate
      String id = ctx.ID().getText();
      penTemplate.add("pentypeName",id); 

      ST propertyDefinition;
      for(pdrawParser.PropertyDefinitionContext context : ctx.propertyDefinition()){
         propertyDefinition = visit(context);
         penTemplate.add("properties",propertyDefinition.render());
      }      
      return penTemplate;
   }

   @Override public ST visitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx) {
      ST property = templates.getInstanceOf("property");
      String propriedade = ctx.Property().getText();

      property.add("key", propriedade);
      property.add("value", visit(ctx.expression()).render());
      return property;
   }

   @Override public ST visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
      ST canvasTemplate = templates.getInstanceOf("canvasDefinition");
      String id = ctx.ID().getText();
      String title = ctx.String().getText();
      String measurements = visitPoint(ctx.point()).render();

      canvasTemplate.add("canvasName",id);
      canvasTemplate.add("title",title);
      canvasTemplate.add("measurements",measurements);

      return canvasTemplate;
   }

   @Override public ST visitDeclaration(pdrawParser.DeclarationContext ctx) {
      return visitChildren(ctx);
   }

   @Override public ST visitDeclaration_element(pdrawParser.Declaration_elementContext ctx) {
      ST declaration_element;
      if (ctx.ID() != null) {
         declaration_element = templates.getInstanceOf("assign");

         declaration_element.add("var", ctx.ID().getText());
         declaration_element.add("value", "None");
      }
      else {
         declaration_element = visit(ctx.assign());
      }

      return declaration_element;
   }

   @Override public ST visitWhile(pdrawParser.WhileContext ctx) {
      ST whileTemplate = templates.getInstanceOf("while");
      whileTemplate.add("condition", visit(ctx.expression()).render());
      whileTemplate.add("compound", visit(ctx.statement()).render());
      return whileTemplate;
   }

   @Override public ST visitUntil(pdrawParser.UntilContext ctx) {
      ST untilTemplate = templates.getInstanceOf("until");
      untilTemplate.add("condition", visit(ctx.expression()).render());
      untilTemplate.add("compound", visit(ctx.statement()).render());
      return untilTemplate;
   }

   @Override public ST visitFor(pdrawParser.ForContext ctx) {
      ST forTemplate = templates.getInstanceOf("for");
      forTemplate.add("init", visit(ctx.statement(0)).render());
      forTemplate.add("condition", visit(ctx.expression(0)).render());
      forTemplate.add("expression", visit(ctx.expression(1)).render());
      forTemplate.add("compound", visit(ctx.statement(1)).render());
      return forTemplate;
   }

   @Override public ST visitIf(pdrawParser.IfContext ctx) {
      ST ifTemplate = templates.getInstanceOf("if");
      ifTemplate.add("condition", visit(ctx.expression()).render());
      ifTemplate.add("compoundIf", visit(ctx.statement(0)).render());
      if (ctx.statement(1) != null) {
         ifTemplate.add("compoundElse", visit(ctx.statement(1)).render());
      }
      return ifTemplate;
   }

   @Override public ST visitExecution(pdrawParser.ExecutionContext ctx) {
      ST execution = templates.getInstanceOf("execution");
      execution.add("penId", ctx.ID().getText());
      execution.add("fileName", visit(ctx.expression()).render());
      return execution;
   }

   @Override public ST visitPause(pdrawParser.PauseContext ctx) {
      ST pause = templates.getInstanceOf("pause");
      ST codeST = visit(ctx.expression());
      String code = codeST.render();
      pause.add("time", code);
      return pause;
   }

   @Override public ST visitStdout(pdrawParser.StdoutContext ctx) {
      ST stdout = templates.getInstanceOf("stdout");
      stdout.add("message", visit(ctx.expression()).render());
      return stdout;
   }

   @Override public ST visitExprToString(pdrawParser.ExprToStringContext ctx) {
      ST toString = templates.getInstanceOf("toString");
      toString.add("op", visit(ctx.expression()).render());
      return toString;
   }

   @Override public ST visitExprToBool(pdrawParser.ExprToBoolContext ctx) {
      ST toBool = templates.getInstanceOf("toBool");
      toBool.add("op", visit(ctx.expression()).render());
      return toBool;
   }

   @Override public ST visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      ST toInt = templates.getInstanceOf("toInt");
      toInt.add("op", visit(ctx.expression()).render());
      return toInt;
   }

   @Override public ST visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      binary.add("op1", visit(ctx.expression(0)).render());
      binary.add("op2", visit(ctx.expression(1)).render());
      binary.add("operator", ctx.op.getText());
      return binary;
   }

   @Override public ST visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      binary.add("op1", visit(ctx.expression(0)).render());
      binary.add("op2", visit(ctx.expression(1)).render());
      binary.add("operator", ctx.op.getText());
      return binary; 
   }

   @Override public ST visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
      ST setProperty = templates.getInstanceOf("ExprPen");
      setProperty.add("name", visit(ctx.expression(0)).render());
      setProperty.add("op", ctx.Property().getText());
      setProperty.add("value", visit(ctx.expression(1)).render());
      return setProperty;
   }

   @Override public ST visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
      ST integer = templates.getInstanceOf("single");
      integer.add("content", ctx.Integer().getText());
      return integer;
   }

   @Override public ST visitExprId(pdrawParser.ExprIdContext ctx) {
      ST id = templates.getInstanceOf("single");
      id.add("content", ctx.ID().getText());
      return id;
   }

   @Override public ST visitExprString(pdrawParser.ExprStringContext ctx) {
      ST string = templates.getInstanceOf("single");
      string.add("content", ctx.String().getText());
      return string;
   }

   @Override public ST visitExprParent(pdrawParser.ExprParentContext ctx) {
      ST parent = templates.getInstanceOf("parent");
      parent.add("op", visit(ctx.expression()).render());
      return parent;
   }

   @Override public ST visitExprComp(pdrawParser.ExprCompContext ctx) {
      ST comparison = templates.getInstanceOf("binary_operations");
      comparison.add("op1", visit(ctx.expression(0)).render());
      comparison.add("op2", visit(ctx.expression(1)).render());
      comparison.add("operator", ctx.op.getText());
      return comparison;
   }

   @Override public ST visitExprPoint(pdrawParser.ExprPointContext ctx) {
      return visit(ctx.point());
   }

   @Override public ST visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      ST concat = templates.getInstanceOf("concat");
      concat.add("op1", visit(ctx.expression(0)).render());
      concat.add("op2", visit(ctx.expression(1)).render());
      return concat;
   }

   @Override public ST visitExprColor(pdrawParser.ExprColorContext ctx) {
      ST color = templates.getInstanceOf("color");
      String colorName = ctx.Color().getText();
      if (colorName.charAt(0) == '#') {
         color.add("content", colorName);
      } else {
         color.add("content", colors.get(colorName));
      }
      return color;
   }

   @Override public ST visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      ST toReal = templates.getInstanceOf("toReal");
      toReal.add("op", visit(ctx.expression()).render());
      return toReal;
   }

   @Override public ST visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      ST penOperator = templates.getInstanceOf("ExprPen");
      penOperator.add("name", ctx.expression(0).getText());
      penOperator.add("op", ctx.op.getText());
      penOperator.add("value", visit(ctx.expression(1)).render());
      return penOperator;
   }

   @Override public ST visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      ST conv_rad = templates.getInstanceOf("conv_rad");
      conv_rad.add("op", visit(ctx.expression()).render());
      return conv_rad;
   }

   @Override public ST visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      ST unary = templates.getInstanceOf("ExprPenUnary");
      unary.add("penName", visit(ctx.expression()).render());
      unary.add("op", ctx.op.getText());
      return unary;
   }

   @Override public ST visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      ST unary = templates.getInstanceOf("unary_operations");
      unary.add("op", visit(ctx.expression()).render());
      unary.add("operator", ctx.op.getText());
      return unary;
   }

   @Override public ST visitExprNew(pdrawParser.ExprNewContext ctx) {
      ST penCreation = templates.getInstanceOf("penCreation");
      if (ctx.expression() != null) {
         penCreation.add("penType", visit(ctx.expression()).render());
      } else {
         penCreation.add("penType", "{}");
      }
      return penCreation;
   }

   @Override public ST visitExprBoolOp(pdrawParser.ExprBoolOpContext ctx) {
      ST bool = templates.getInstanceOf("binary_operations");
      bool.add("op1", visit(ctx.expression(0)).render());
      bool.add("op2", visit(ctx.expression(1)).render());
      bool.add("operator", ctx.op.getText());
      return bool;
   }

   @Override public ST visitExprStdin(pdrawParser.ExprStdinContext ctx) {
      ST stdin = templates.getInstanceOf("stdin");
      stdin.add("message", visit(ctx.expression()).render());
      return stdin;
   }

   @Override public ST visitExprAssign(pdrawParser.ExprAssignContext ctx) {
      return visit(ctx.assign());
   }

   @Override public ST visitAssign(pdrawParser.AssignContext ctx) {
      ST assign = templates.getInstanceOf("assign");
      assign.add("var", ctx.ID().getText());
      assign.add("value", visit(ctx.expression()).render());
      return assign;
   }

   @Override public ST visitExprReal(pdrawParser.ExprRealContext ctx) {
      ST real = templates.getInstanceOf("single");
      real.add("content", ctx.Real().getText());
      return real;
   }

   @Override public ST visitExprBool(pdrawParser.ExprBoolContext ctx) {
      ST bool = templates.getInstanceOf("single");
      bool.add("content", ctx.Boolean().getText());
      return bool;
   }

   @Override public ST visitPoint(pdrawParser.PointContext ctx) {
      ST point = templates.getInstanceOf("point");
      point.add("op1", visit(ctx.x).render());
      point.add("op2", visit(ctx.y).render());
      return point;
   }

   @Override public ST visitExprPi(pdrawParser.ExprPiContext ctx) {
      ST pi = templates.getInstanceOf("single");
      pi.add("content", "pi");
      return pi;
   }
}
