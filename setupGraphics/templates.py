from graphics import *


class Canvas:
    def __init__(self,title,width,height) -> None:
        self.title = title
        self.width = width
        self.height = height
        self.win = GraphWin(title,width,height)
    
    def drawLine(self, point1, point2):
        line = Line(Point(*point1),Point(*point2))
        line.draw(self.win)
        return line

class PenType:
    def __init__(self, position = (0,0), color="black", pressure = 1, orientation = 0, thinkness = 1) -> None:
        self.position = position
        self.color = color
        self.pressure = pressure
        self.orientation = orientation 
        self.thinkness = thinkness

class Pen():
    def __init__(self, win : Canvas, penType : PenType):
        self.win = win
        self.position = penType.position
        self.color = penType.color
        self.pressure = penType.pressure
        self.orientation = penType.orientation
        self.thinkness = penType.thinkness
                
    def forward(self, distance):
        # TODO: forward pen
        # should return self
        pass
    
    def right(self, angle):
        # TODO: rotate pen right <angle> radians
        # should return self
        pass

    def left(self, angle):
        # TODO: rotate pen left <angle> radians
        # should return self
        pass
    
    def down(self):
        # TODO: set pen down
        # should return self
        pass
    
    def up(self):
        # TODO: set pen up
        # should return self
        pass
    
    def color(self, color = None):
        # TODO: set pen color
        # should return self
        # if color is None, return the current color
        pass
    
    def thinkness(self, thinkness = None):
        # TODO: set pen thinkness
        # should return self
        # if thinkness is None, return the current thinkness
        pass
    
    def position(self, position = None):
        # TODO: set pen position
        # should return self
        # if position is None, return the current position
        pass
    
    def orientation(self, orientation = None):
        # TODO: set pen orientation
        # should return self
        # if orientation is None, return the current orientation
        pass
    
    def pressure(self, pressure = None):
        # TODO: set pen pressure
        # should return self
        # if pressure is None, return the current pressure
        pass
    
    
def main():
    


        
    
        