from canvas import Canvas
import math
from time import sleep
from point import Point

    
def ColorToInt(colorHex):
    r = int(colorHex[1:3], 16)
    g = int(colorHex[3:5], 16)
    b = int(colorHex[5:7], 16)
    return r<<24 | g<<16 | b<<8

def IntToColor(colorInt):
    r = (colorInt >> 24) & 0xFF
    g = (colorInt >> 16) & 0xFF
    b = (colorInt >> 8) & 0xFF
    return f"#{r:02x}{g:02x}{b:02x}"

class Pen:
    def __init__(self, canvas: Canvas, penType: dict = {}):
        self.__canvas = canvas
        self.__position = penType.get("position", Point(0, 0))
        self.__color = penType.get("color", "black")
        self.__pressure = penType.get("pressure", 1)
        self.__orientation = penType.get("orientation", 0)
        self.__thickness = penType.get("thickness", 1)
    
    def __add__(self, other):
        if isinstance(other, Point):
            new_position = self.__position + other
            if self.__pressure != -1:
                self.__canvas.drawLine(self.__position, new_position, IntToColor(self.__color), 1 + (self.__thickness - 1) * self.__pressure)
            self.__position = new_position
        return self
    
    def __sub__(self, other):
        if isinstance(other, Point):
            new_position = self.__position - other
            if self.__pressure != -1:
                self.__canvas.drawLine(self.__position, new_position, IntToColor(self.__color), 1 + (self.__thickness - 1) * self.__pressure)
            self.__position = new_position
        return self

    def __str__(self) -> str:
        return f"Pen(position={self.__position}, color={IntToColor(self.__color)}, pressure={self.__pressure}, orientation={self.__orientation}, thickness={self.__thickness})"

    def forward(self, distance):
        distance *= 10
        new_position = self.__position + Point(distance * math.cos(self.__orientation), distance * math.sin(self.__orientation))
        
        if self.__pressure != -1:
            self.__canvas.drawLine(self.__position, new_position, IntToColor(self.__color), 1 + (self.__thickness - 1) * self.__pressure)

        self.__position = new_position
        return self
    
    def backward(self, distance):
        distance *= 10
        new_position = self.__position - Point(distance * math.cos(self.__orientation), distance * math.sin(self.__orientation))

        if self.__pressure != -1:
            self.__canvas.drawLine(self.__position, new_position, IntToColor(self.__color), 1 + (self.__thickness - 1) * self.__pressure)

        self.__position = new_position
        return self

    def right(self, angle):
        self.__orientation += angle
        return self

    def left(self, angle):
        self.__orientation -= angle
        return self

    def down(self):
        self.__pressure = 1
        return self

    def up(self):
        self.__pressure = -1
        return self

    def color(self, color=None):
        if color is not None:
            self.__color = color
        return self.__color

    def thickness(self, thickness=None):
        if thickness is not None:
            self.__thickness = thickness
        return self.__thickness

    def position(self, position=None):
        if position is not None:
            self.__position = position
        return self.__position

    def orientation(self, orientation=None):
        if orientation is not None:
            self.__orientation = orientation
        return self.__orientation

    def pressure(self, pressure=None):
        if pressure is not None:
            self.__pressure = pressure
        return self.__pressure
    
    def pause(self, time):
        sleep(time/1_000_000)
        return self


def main():
    canvas = Canvas("Drawing Canvas", 400, 400)
    
    penType = {"position": Point(200, 200), "color": "green", "orientation": 45, "thickness": 10, "pressure": 1}
    pen = Pen(canvas, penType)

    print(str(pen))

    pen.down().forward(50).left(90)
    sleep(2)
    pen += Point(50, 50)
    pen.forward(50).left(90).forward(50).left(90)
    sleep(3)
    pen.forward(50)
    
    canvas.waitUntilClose()

if __name__ == "__main__":
    main()

