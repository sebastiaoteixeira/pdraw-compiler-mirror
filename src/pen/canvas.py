from graphics import *
import math

class Canvas:
    def __init__(self, title, width, height) -> None:
        self.title = title
        self.width = width
        self.height = height
        self.win = GraphWin(title, width, height)

    def drawLine(self, point1, point2, color, thickness):
        line = Line(Point(*point1), Point(*point2))
        line.setFill(color)
        line.setWidth(thickness)
        line.draw(self.win)
        return line

    def waitUntilClose(self):
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
    
    def createCanvas(self, title, width, height):
        canvas = Canvas(title, width, height)
        self.canvases.append(canvas)
        return canvas

    def waitUntilClose(self):
        for canvas in self.canvases:
            canvas.waitUntilClose()
            
    def getCanvas(self):
        return self.canvases[-1]

        
    
        