import sys

class ErrorHandler:
    def __init__(self, fname) -> None:
        self.success = True
        self.filename = fname

    def error(self, msg, line, column):
        ErrorHandler.success = False
        print(f"\033[31m{self.filename}:{line}:{column}: error: {msg}\033[0m")

    def warning(self, msg, line, column):
        print(f"\033[33m{self.filename}:{line}:{column}: warning: {msg}\033[0m")

    def info(self, msg, line, column):
        print(f"\033[37m{self.filename}:{line}:{column}: info: {msg}\033[0m")



    def execute(self):
        if not self.success:
            sys.exit(1)
