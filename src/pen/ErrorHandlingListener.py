from antlr4.error.ErrorListener import ErrorListener

class ErrorHandlingListener(ErrorListener):
    def __init__(self, eHandler):
        super().__init__()
        self.eHandler = eHandler

    def syntaxError(self, recognizer, offendingSymbol, line, column, msg, e):
        self.eHandler.error(msg, line, column)

