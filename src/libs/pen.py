from canvas import Canvas
import math

class Pen:
    def __init__(self, canvas: Canvas, penType: dict):
        self.__canvas = canvas
        self.__position = penType.get("position", (0, 0))
        self.__color = penType.get("color", "black")
        self.__pressure = penType.get("pressure", 1)
        self.__orientation = penType.get("orientation", 0)
        self.__thickness = penType.get("thickness", 1)
        self.is_down = True 

    def __str__(self) -> str:
        return f"Pen(position={self.__position}, color={self.__color}, pressure={self.__pressure}, orientation={self.__orientation}, thickness={self.__thickness}, is_down={self.is_down})"

    def backward(self, distance):
        rad_orientation = math.radians(self.__orientation)
        new_x = self.__position[0] - distance * math.cos(rad_orientation)
        new_y = self.__position[1] - distance * math.sin(rad_orientation)
        new_position = (new_x, new_y)

        if self.is_down:
            self.__canvas.drawLine(self.__position, new_position, self.__color, 1 + (self.__thickness - 1) * self.__pressure)

        self.position = new_position
        return self

    def forward(self, distance):
        rad_orientation = math.radians(self.__orientation)
        new_x = self.__position[0] + distance * math.cos(rad_orientation)
        new_y = self.__position[1] + distance * math.sin(rad_orientation)
        new_position = (new_x, new_y)

        if self.is_down:
            self.__canvas.drawLine(self.__position, new_position, self.__color, 1 + (self.__thickness - 1) * self.__pressure)

        self.position = new_position
        return self

    def right(self, angle):
        self.__orientation -= angle
        return self

    def left(self, angle):
        self.__orientation += angle
        return self

    def down(self):
        self.is_down = True
        return self

    def up(self):
        self.is_down = False
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

def main():
    canvas = Canvas("Drawing Canvas", 400, 400)
    
    penType = {"position":(200, 200), "color":"green", "orientation":45, "thickness":10, "pressure":1}
    pen = Pen(canvas, penType)

    print(str(pen))

    pen.down().forward(50).left(90).forward(50).left(90).forward(50).left(90).forward(50)
    
    canvas.waitUntilClose()

if __name__ == "__main__":
    main()
