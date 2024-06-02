import sys

class ErrorHandler:
    def __init__(self, fname):
        self.success = True
        self.file = fname
        self.out = sys.stdout

        self.colors = ["\033[37m", "\033[33m", "\033[31m"]
        self.message_types = [
            self.colors[0] + "info: " + "\033[0m",
            self.colors[1] + "warning: " + "\033[0m",
            self.colors[2] + "error: " + "\033[0m"
        ]

        self.counters = [0, 0, 0]

    def error(self, msg, line, column):
        self.success = False
        self.print_message(2, msg, line, column)

    def warning(self, msg, line, column):
        self.print_message(1, msg, line, column)

    def info(self, msg, line, column):
        self.print_message(0, msg, line, column)

    def print_message(self, type, msg, line, column):
        self.counters[type] += 1
        self.out.write(
            f"\033[1m{self.file}:{line}:{column}: {self.message_types[type]}{msg}\n")

    def execute(self):
        for i in range(2, -1, -1):
            if self.counters[i] > 0:
                self.out.write(f"{self.message_types[i]}{self.counters[i]}\n")

        if not self.success:
            sys.exit(1)
