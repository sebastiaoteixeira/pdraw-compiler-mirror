
class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    
    def __add__(self, other):
        if isinstance(other, Point):
            return Point(self.x + other.x, self.y + other.y)
        else:
            raise TypeError("Unsupported operand type(s) for +: 'Point' and '{}'".format(type(other).__name__))
    
    def __sub__(self, other):
        if isinstance(other, Point):
            return Point(self.x - other.x, self.y - other.y)
        else:
            raise TypeError("Unsupported operand type(s) for -: 'Point' and '{}'".format(type(other).__name__))
    
    def __str__(self) -> str:
        return f"({self.x},{self.y})"
    
    def __mul__(self, other):
        if isinstance(other, int):
            return Point(self.x * other, self.y * other)
        else:
            raise TypeError("Unsupported operand type(s) for *: 'Point' and '{}'".format(type(other).__name__))
    
    def __rmul__(self, other):
        return self.__mul__(other)
    
    def __getitem__(self, index):
        if index == 0:
            return self.x
        elif index == 1:
            return self.y
        else:
            raise IndexError("Index out of range")
        
    def __setitem__(self, index, value):
        if index == 0:
            self.x = value
        elif index == 1:
            self.y = value
        else:
            raise IndexError("Index out of range")
    
    def __eq__(self, other):
        if isinstance(other, Point):
            return self.x == other.x and self.y == other.y
        else:
            return False
    
    def __ne__(self, other):
        return not self.__eq__(other)
