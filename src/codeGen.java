import org.stringtemplate.v4.*;

import .antlr.pdrawParser;

import .antlr.pdrawParser;

@SuppressWarnings("CheckReturnValue")
public class codeGen extends pdrawBaseVisitor<String> {

   private STGroup templates = new STGroupFile("templates.stg");

   @Override 
   public String visitProgram(pdrawParser.ProgramContext ctx) {
      ST mainTemplate = templates.getInstanceOf("main");

      // Visit all the children of the program
      for (pdrawParser.StatementContext statement : ctx.statement()) {
         String code = visit(statement);
         if (code != null) {
            mainTemplate.add("statements", code);
         }
      }

      String res = mainTemplate.render();
      System.out.println(res);
      
      return res;
   }

   @Override public String visitStatement(pdrawParser.StatementContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitDefine(pdrawParser.DefineContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPenDefinition(pdrawParser.PenDefinitionContext ctx) {
      ST penTemplate = templates.getInstanceOf("pentype");
      //passar o pentypename(id), dentro de loop ver todas as properties e adicionar ao penTemplate
      String id = ctx.ID.getText();
      penTemplate.add("pentypeName",id); 

      ST propertyDefinition;
      for(pdrawParser.PropertyDefinitionContext context : ctx.propertyDefinition()){
         propertyDefinition = visit(context);
         penTemplate.add("properties",propertyDefinition.render());
      }      
      return penTemplate;
   }

   @Override public String visitPropertyDefinition(pdrawParser.PropertyDefinitionContext ctx) {
      ST property = templates.getInstanceOf("property");

      String propriedade = ctx.Property.getText();
      property.add("key", propriedade);
      property.add("value",visit(ctx.expr()).render());
      return property;
   }

   @Override public String visitCanvasDefinition(pdrawParser.CanvasDefinitionContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //TO DO
      //return res;
   }

   @Override public String visitDeclaration(pdrawParser.DeclarationContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //TO DO
      //return res;
   }

   @Override public String visitExpression(pdrawParser.ExpressionContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitExecution(pdrawParser.ExecutionContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //TO DO, depois
      //return res;
   }

   @Override public String visitPause(pdrawParser.PauseContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //TO DO, chamar função split
      //return res;
   }

   @Override public String visitStdout(pdrawParser.StdoutContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //TO DO, chamar função print
      //return res;
   }

   //TO DO
   @Override public String visitExprToString(pdrawParser.ExprToStringContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprString(pdrawParser.ExprStringContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprParent(pdrawParser.ExprParentContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprPoint(pdrawParser.ExprPointContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprStringConcat(pdrawParser.ExprStringConcatContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprToInt(pdrawParser.ExprToIntContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprToReal(pdrawParser.ExprToRealContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprPenOperator(pdrawParser.ExprPenOperatorContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprMultDivMod(pdrawParser.ExprMultDivModContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      
      binary.add("op1", visit(ctx.expr(0)));
      binary.add("op2", visit(ctx.expr(1)));
      binary.add("operator", ctx.op.getText());

      return binary.render(); 
   }

   @Override public String visitExprSetProperty(pdrawParser.ExprSetPropertyContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprAddSub(pdrawParser.ExprAddSubContext ctx) {
      ST binary = templates.getInstanceOf("binary_operations");
      
      binary.add("op1", visit(ctx.expr(0)));
      binary.add("op2", visit(ctx.expr(1)));
      binary.add("operator", ctx.op.getText());

      return binary.render(); 
   }

   @Override public String visitExprConvToRad(pdrawParser.ExprConvToRadContext ctx) {
      ST conv_rad = templates.getInstanceOf("conv_rad");
      conv_rad.add("op", visit(ctx.expr()));
      return conv_rad.render();
   }

   @Override public String visitExprPenUnary(pdrawParser.ExprPenUnaryContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprUnary(pdrawParser.ExprUnaryContext ctx) {
      ST unary = templates.getInstanceOf("unary_operations");
      unary.add("op", visit(ctx.expr()));
      unary.add("operator", ctx.op.getText());
      return unary.render();
   }

   @Override public String visitExprNew(pdrawParser.ExprNewContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprStdin(pdrawParser.ExprStdinContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprAssign(pdrawParser.ExprAssignContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprReal(pdrawParser.ExprRealContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprInteger(pdrawParser.ExprIntegerContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExprId(pdrawParser.ExprIdContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitPoint(pdrawParser.PointContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }
}
