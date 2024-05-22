from pen import Pen,Point
from math import radians
from canvas import Canvas

def main():
    canvas = Canvas("Canvas", 500, 500)
    PenType1 = {

        "color": '#00FF00' ,
        "position": Point(10,10) ,
        "orientation": radians(45) ,
        "thickness": 10 ,
        "pressure": -1 
    }



    p1 = None
    p1 = Pen(canvas,PenType1)
    p1.down()
    p1.forward(40)
    p1.left(radians(90))
    p1.forward(40)
    p1.left(radians(90))
    p1.forward(40)
    p1.left(radians(90))
    p1.forward(40)
    p1.left(radians(90))
    #psec = None
    #exec("p1.ipdraw", psec)

    canvas.waitUntilClose()

if __name__ == "__main__":
    main()