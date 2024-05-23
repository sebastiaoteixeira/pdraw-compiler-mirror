from graphics import *
from point import Point as pdPoint
import math

class Canvas:
    def __init__(self, title, width, height) -> None:
        self.title = title
        self.width = width
        self.height = height
        self.win = None

    def drawLine(self, point1, point2, color, thickness):
        if self.win is None:
            self.win = GraphWin(self.title, self.width, self.height)
        
        line = Line(Point(*point1), Point(*point2))
        line.setFill(color)
        line.setWidth(thickness)
        line.draw(self.win)
        return line

    def waitUntilClose(self):
        if self.win is None:
            return
        try:
            self.win.getMouse()
        except GraphicsError:
            pass
        finally:
            self.win.close()
            
class CanvasManager:
    def __init__(self) -> None:
        self.canvases = []
        self.canvases.append(Canvas("", 500, 500))
    
    def createCanvas(self, title, dimensions: pdPoint):
        canvas = Canvas(title, dimensions[0], dimensions[1])
        self.canvases.append(canvas)
        return canvas

    def waitUntilClose(self):
        for canvas in self.canvases:
            canvas.waitUntilClose()
            
    def getCanvas(self):
        return self.canvases[-1]

        
    
        