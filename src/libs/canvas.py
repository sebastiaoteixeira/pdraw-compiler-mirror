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
        self.win.getMouse()
        self.win.close()

        
    
        