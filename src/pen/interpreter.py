from antlr4 import *
from penParser import penParser
from penVisitor import penVisitor
from ..libs.pen import Pen
from ..libs.canvas import Canvas
class interpreter(penVisitor):

   

   def visitProgram(self, ctx:penParser.ProgramContext):
      canvas = Canvas("Drawing Canvas", 400, 400)
      self.pen = Pen(canvas)
      return self.visitChildren(ctx)

   def visitCommand(self, ctx:penParser.CommandContext): 
      return self.visitChildren(ctx)

   def visitSetOrientation(self, ctx:penParser.SetOrientationContext):
      orientation = self.visit(ctx.expr())
      self.pen.orientation(orientation)
      return None

   def visitSetPressure(self, ctx:penParser.SetPressureContext):
      pressure = self.visit(ctx.expr())
      self.pen.pressure(pressure)
      return None

   def visitSetThickness(self, ctx:penParser.SetThicknessContext):
      thickness = self.visit(ctx.expr())
      self.pen.thickness(thickness)
      return None

   def visitSetColor(self, ctx:penParser.SetColorContext):
      color = self.visit(ctx.expr())
      self.pen.color(color)
      return None

   def visitSetPosition(self, ctx:penParser.SetPositionContext):
      x = self.visit(ctx.expr(0))
      y = self.visit(ctx.expr(1))
      self.pen.position((x, y))
      return None

   def visitPenControl(self, ctx:penParser.PenControlContext):
      command = ctx.getText()
      if command == 'down;':
         self.pen.down()
      elif command == 'up;':
         self.pen.up()
      return None

   def visitPenMove(self, ctx:penParser.PenMoveContext):
      distance = self.visit(ctx.expr())
      direction = ctx.getChild(0).getText()
      if direction == 'forward':
         self.pen.forward(distance)
      elif direction == 'backward':
         self.pen.backward(distance)
      return None

   def visitRotate(self, ctx:penParser.RotateContext):
      angle = self.visit(ctx.expr())
      direction = ctx.getChild(0).getText()
      if direction == 'left':
         self.pen.left(angle)
      elif direction == 'right':
         self.pen.right(angle)
      return None

   def visitExpr(self, ctx:penParser.ExprContext):
      if ctx.Integer():
         return int(ctx.Integer().getText())
      elif ctx.Real():
         return float(ctx.Real().getText())
      elif ctx.expr():
         return self.visit(ctx.expr())
      return None

