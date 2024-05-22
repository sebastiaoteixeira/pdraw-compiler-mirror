def main():
    PenType1 = {

        "color": #00FF00 ,
        "position": Point(10,10) ,
        "orientation": radians(45) ,
        "thickness": 10 ,
        "pressure": -1 
    }
    p1 = None
    p1.down()
    p1.forward(10)
    p1.left(radians(90))
    p1.forward(10)
    p1.left(radians(90))
    p1.forward(10)
    p1.left(radians(90))
    p1.forward(10)
    p1.left(radians(90))
    psec = None
    exec("p1.ipdraw", psec)

if __name__ == "__main__":
    main()