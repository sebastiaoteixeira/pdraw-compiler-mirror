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

class PenType:
    def __init__(self, position=(0, 0), color="black", pressure=1, orientation=0, thickness=1) -> None:
        self.position = position
        self.color = color
        self.pressure = pressure
        self.orientation = orientation 
        self.thickness = thickness

class Pen:
    def __init__(self, canvas: Canvas, penType: PenType):
        self.canvas = canvas
        self.position = penType.position
        self.color = penType.color
        self.pressure = penType.pressure
        self.orientation = penType.orientation
        self.thickness = penType.thickness
        self.is_down = True 

    def forward(self, distance):
        rad_orientation = math.radians(self.orientation)
        new_x = self.position[0] + distance * math.cos(rad_orientation)
        new_y = self.position[1] + distance * math.sin(rad_orientation)
        new_position = (new_x, new_y)

        if self.is_down:
            self.canvas.drawLine(self.position, new_position, self.color, self.thickness * self.pressure)

        self.position = new_position
        return self

    def right(self, angle):
        self.orientation -= angle
        return self

    def left(self, angle):
        self.orientation += angle
        return self

    def down(self):
        self.is_down = True
        return self

    def up(self):
        self.is_down = False
        return self

    def set_color(self, color=None):
        if color is not None:
            self.color = color
        return self.color

    def set_thickness(self, thickness=None):
        if thickness is not None:
            self.thickness = thickness
        return self.thickness

    def set_position(self, position=None):
        if position is not None:
            self.position = position
        return self.position

    def set_orientation(self, orientation=None):
        if orientation is not None:
            self.orientation = orientation
        return self.orientation

    def set_pressure(self, pressure=None):
        if pressure is not None:
            self.pressure = pressure
        return self.pressure

def main():
    canvas = Canvas("Drawing Canvas", 400, 400)
    
    penType1 = PenType(position=(200, 200), color="green", orientation=45, thickness=10, pressure=1)
    pen = Pen(canvas, penType1)

    pen.down().forward(50).left(90).forward(50).left(90).forward(50).left(90).forward(50)
    
    canvas.win.getMouse()
    canvas.win.close()

if __name__ == "__main__":
    main()


        
    
        