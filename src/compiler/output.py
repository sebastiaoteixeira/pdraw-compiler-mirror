from math import radians, pi
from time import sleep
from canvas import Canvas
from pen import Pen, Point

def main():
    defaultCanvas = Canvas("", 500, 500)
    PenType1 = {

        "color": #00FF00 ,
        "position": Point(10,10) ,
        "orientation": radians(45) ,
        "thickness": 10 ,
        "pressure": -1 
    }
    p1 = Pen(defaultCanvas, PenType1)
    p1.down()
    p1.forward(10)
    p1.left(radians(90))
    p1.forward(10)
    p1.left(radians(90))
    p1.forward(10)
    p1.left(radians(90))
    p1.forward(10)
    p1.left(radians(90))
    psec = Pen(defaultCanvas, {})
    exec("p1.ipdraw", psec)

if __name__ == "__main__":
    main()