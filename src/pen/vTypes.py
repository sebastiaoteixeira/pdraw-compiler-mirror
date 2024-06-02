from enum import Enum

class Type(Enum):
    Integer = 1
    Real = 2
    String = 3
    Bool = 4
    Point = 5
    
    def __str__(self):
        return super().__str__().split('.')[1]
