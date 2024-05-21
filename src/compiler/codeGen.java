import org.stringtemplate.v4.*;

@SuppressWarnings("CheckReturnValue")
   public class codeGen extends pdrawBaseVisitor<ST> {

   private STGroup templates = new STGroupFile("templates.stg");

   @Override public ST visitProgram(pdrawParser.ProgramContext ctx) {
      ST mainTemplate = templates.getInstanceOf("main");

      mainTemplate.add("compound", visit(ctx.compound().render()));

      System.out.println(mainTemplate.render());
      
      return mainTemplate;
   }

   @Override public ST visitStatement(pdrawParser.StatementContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
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
      ST res = null;
      return visitChildren(ctx);
      //return res;
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
      property.add("value",visit(ctx.expression()).render());
      return property;
   }

   @Override public ST visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
      // define canvas Canvas1 "Example p2" (width,height);
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
      ST declaration_element = templates.getInstanceOf("declaration_element");
      declaration_element.add("identifier", ctx.ID().getText());
      return declaration_element;
   }

   @Override public ST visitWhile(pdrawParser.WhileContext ctx) {
      ST whileTemplate = templates.getInstanceOf("while");
      whileTemplate.add("condition", visit(ctx.expression().render()));
      whileTemplate.add("compound", visit(ctx.statement().render()));
      return whileTemplate;
   }

   @Override public ST visitUntil(pdrawParser.UntilContext ctx) {
      ST untilTemplate = templates.getInstanceOf("until");
      untilTemplate.add("condition", visit(ctx.expression().render()));
      untilTemplate.add("compound", visit(ctx.statement().render()));
      return untilTemplate;
   }

   @Override public ST visitFor(pdrawParser.ForContext ctx) {
      ST forTemplate = templates.getInstanceOf("for");
      forTemplate.add("init", visit(ctx.expression(0).render()));
      forTemplate.add("condition", visit(ctx.expression(1).render()));
      forTemplate.add("expression", visit(ctx.expression(2).render()));
      forTemplate.add("compound", visit(ctx.statement().render()));
      return forTemplate;
   }

   @Override public ST visitIf(pdrawParser.IfContext ctx) {
      ST ifTemplate = templates.getInstanceOf("if");
      ifTemplate.add("condition", visit(ctx.expression().render()));
      ifTemplate.add("compoundIf", visit(ctx.statement(0).render()));
      if (ctx.statement(1) != null) {
         ifTemplate.add("compoundElse", visit(ctx.statement(1).render()));
      }
      return ifTemplate;
   }

   @Override public ST visitExecution(pdrawParser.ExecutionContext ctx) {
      ST execution = templates.getInstanceOf("execution");
      execution.add("penId", ctx.ID().getText());
      execution.add("fileName", visit(ctx.expression().render()));
      return execution;
   }

   @Override public ST visitPause(pdrawParser.PauseContext ctx) {
      ST pause = templates.getInstanceOf("pause");
      pause.add("time", visit(ctx.expression()).render());
      return pause;
   }

   @Override public ST visitStdout(pdrawParser.StdoutContext ctx) {
      ST stdout = templates.getInstanceOf("stdout");
      stdout.add("message", visit(ctx.expression().render()));
      return stdout;
   }

   @Override public ST visitExprToString(pdrawParser.ExprToStringContext ctx) {
      ST toString = templates.getInstanceOf("toString");
      toString.add("op", visit(ctx.expression().render()));
      return toString;
   }

   @Override public ST visitExprToBool(pdrawParser.ExprToBoolContext ctx) {
      ST toBool = templates.getInstanceOf("toBool");
      toBool.add("op", visit(ctx.expression().render()));
      return toBool;
   }

   @Override public ST visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      ST toInt = templates.getInstanceOf("toInt");
      toInt.add("op", visit(ctx.expression().render()));
      return toInt;
   }

   @Override public ST visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      binary.add("op1", visit(ctx.expression(0).render()));
      binary.add("op2", visit(ctx.expression(1).render()));
      binary.add("operator", ctx.op.getText());
      return binary;
   }

   @Override public ST visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      
      binary.add("op1", visit(ctx.expression(0).render()));
      binary.add("op2", visit(ctx.expression(1).render()));
      binary.add("operator", ctx.op.getText());

      return binary; 
   }

   @Override public ST visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprId(pdrawParser.ExprIdContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprString(pdrawParser.ExprStringContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprParent(pdrawParser.ExprParentContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprComp(pdrawParser.ExprCompContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprPoint(pdrawParser.ExprPointContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      ST concat = templates.getInstanceOf("concat");
      concat.add("op1", visit(ctx.expression(0).render()));
      concat.add("op2", visit(ctx.expression(1).render()));
      return concat;
   }

   @Override public ST visitExprColor(pdrawParser.ExprColorContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      ST toReal = templates.getInstanceOf("toReal");
      toReal.add("op", visit(ctx.expression().render()));
      return toReal;
   }

   @Override public ST visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      ST penOperator = templates.getInstanceOf("ExprPen");
      penOperator.add("name", ctx.expression(0).getText());
      penOperator.add("op", ctx.op.getText());
      penOperator.add("value", visit(ctx.expression(1).render()));
      return penOperator;
   }

   @Override public ST visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      ST conv_rad = templates.getInstanceOf("conv_rad");
      conv_rad.add("op", visit(ctx.expression().render()));
      return conv_rad;
   }

   @Override public ST visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      ST unary = templates.getInstanceOf("pen_unary_operations");
      unary.add("penName", visit(ctx.expression().render()));
      unary.add("op", ctx.op.getText());
      return unary;
   }

   @Override public ST visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      ST unary = templates.getInstanceOf("unary_operations");
      unary.add("operator", visit(ctx.expression().render()));
      unary.add("op", ctx.op.getText());
      return unary;
   }

   @Override public ST visitExprNew(pdrawParser.ExprNewContext ctx) {
      ST penCreation = templates.getInstanceOf("penCreation");
      penCreation.add("penType", ctx.expression().getText());
      return penCreation;
   }

   @Override public ST visitExprBoolOp(pdrawParser.ExprBoolOpContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
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
      assign.add("value", visit(ctx.expression().render()));
      return assign;
   }

   @Override public ST visitExprReal(pdrawParser.ExprRealContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprBool(pdrawParser.ExprBoolContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitPoint(pdrawParser.PointContext ctx) {
      ST point = templates.getInstanceOf("unary_operations");
      point.add("op1", visit(ctx.x).render());
      point.add("op2", visit(ctx.y).render());
      return point;
   }

   @Override public ST visitBoolean(pdrawParser.BooleanContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }
}
