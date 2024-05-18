
@SuppressWarnings("CheckReturnValue")
public class codeGen extends pdrawBaseVisitor<ST> {

   private STGroup templates = new STGroupFile("templates.stg");
   
   @Override public ST visitProgram(pdrawParser.ProgramContext ctx) {
      ST mainTemplate = templates.getInstanceOf("main");

      // Visit all the children of the program
      for (pdrawParser.StatementContext statement : ctx.statement()) {
         ST code = visit(statement);
         if (code != null) {
            mainTemplate.add("statements", code.render());
         }
      }

      System.out.println(mainTemplate.render());
      
      return mainTemplate;
   }

   @Override public ST visitStatement(pdrawParser.StatementContext ctx) {
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
      String res = null;
      return visitChildren(ctx);
      //TO DO
      //return res;
   }

   @Override public ST visitDeclaration(pdrawParser.DeclarationContext ctx) {
      ST declaration = templates.getInstanceOf("declarationContext");
      declaration.add("var", ctx.ID().getText());
      return declaration;
   }

   @Override public ST visitExecution(pdrawParser.ExecutionContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitPause(pdrawParser.PauseContext ctx) {
      ST pause = templates.getInstanceOf("pause");
      pause.add("time", ctx.expression().getText());
      return pause;
   }

   @Override public ST visitStdout(pdrawParser.StdoutContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprToString(pdrawParser.ExprToStringContext ctx) {
      ST toString = templates.getInstanceOf("toString");
      toString.add("op", visit(ctx.expression()));
      return toString;
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

   @Override public ST visitExprPoint(pdrawParser.ExprPointContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      ST concat = templates.getInstanceOf("concat");
      concat.add("op1", visit(ctx.expression(0)));
      concat.add("op2", visit(ctx.expression(1)));
      return concat;
   }

   @Override public ST visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      ST toInt = templates.getInstanceOf("toInt");
      toInt.add("op", visit(ctx.expression()));
      return toInt;
   }

   @Override public ST visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      ST toReal = templates.getInstanceOf("toReal");
      toReal.add("op", visit(ctx.expression()));
      return toReal;
   }

   @Override public ST visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      
      binary.add("op1", visit(ctx.expression(0)));
      binary.add("op2", visit(ctx.expression(1)));
      binary.add("operator", ctx.op.getText());

      return binary;
   }

   @Override public ST visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      
      binary.add("op1", visit(ctx.expression(0)));
      binary.add("op2", visit(ctx.expression(1)));
      binary.add("operator", ctx.op.getText());

      return binary; 
   }

   @Override public ST visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      ST conv_rad = templates.getInstanceOf("conv_rad");
      conv_rad.add("op", visit(ctx.expression()));
      return conv_rad;
   }

   @Override public ST visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      ST unary = templates.getInstanceOf("unary_operations");
      unary.add("op", visit(ctx.expression()));
      unary.add("operator", ctx.op.getText());
      return unary;
   }

   @Override public ST visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      ST unary = templates.getInstanceOf("unary_operations");
      unary.add("op", visit(ctx.expression()));
      unary.add("operator", ctx.op.getText());
      return unary;
   }

   @Override public ST visitExprNew(pdrawParser.ExprNewContext ctx) {
      ST res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public ST visitExprStdin(pdrawParser.ExprStdinContext ctx) {
      ST stdin = templates.getInstanceOf("stdin");
      return stdin;
   }

   @Override public ST visitExprAssign(pdrawParser.ExprAssignContext ctx) {
      ST assign = templates.getInstanceOf("assign");
      assign.add("var", ctx.ID().getText());
      assign.add("value", visit(ctx.expression()));
      return assign;
   }

   @Override public ST visitExprReal(pdrawParser.ExprRealContext ctx) {
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

   @Override public ST visitPoint(pdrawParser.PointContext ctx) {
      ST point = templates.getInstanceOf("unary_operations");
      point.add("op1", visit(ctx.x));
      point.add("op2", visit(ctx.y));
      return point;
   }
}
