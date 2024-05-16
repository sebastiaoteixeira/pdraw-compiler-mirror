from graphics import *


class Canvas:
    def __init__(self,title,width,height) -> None:
        self.title = title
        self.width = width
        self.height = height
        self.win = GraphWin(title,width,height)

    def drawLine(self,point1,point2):
        line = Line(Point(*point1),Point(*point2))
        line.draw(self.win)
        return line

class Pen:
    def __init__(self,name,x,y,win : Canvas,color,pressure,orientation,thinkness) -> None:
        self.name = name
        self.x = x
        self.y = y
        self.win = win
        self.color = color
        self.pressure = pressure
        self.orientation = orientation 
        self.thinkness = thinkness
    
def main():
    


        
    
        