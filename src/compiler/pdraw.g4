grammar pdraw;

// Define the grammar for the parser
program: compound EOF;

function: type=tname ID '(' parameter_list? ')' statement;

statement: (define | declaration | expression | stdout | pause | execution | block | return) ';' | (if | for | until | while | function);

return: 'return' expression;

compound: statement*;

block: '{' compound '}';

// Statements and expressions
define: 'define' (penDefinition | canvasDefinition);

penDefinition: 'pen' ID '{' propertyDefinition* '}';

propertyDefinition: Property '=' expression ';';

canvasDefinition: 'canvas' ID expression expression;

parameter_list: parameter (',' parameter)* ;

parameter returns [String id]: type=tname ID;

declaration_element: (ID | assign);

declaration: type=tname declaration_element (',' declaration_element)*;

while: 'while' '(' expression ')' statement;

until: 'until' '(' expression ')' statement;

for: 'for' '(' statement expression ';' expression ')' statement; 

if: 'if' '(' expression ')' statement ('else'  statement )?;

execution: ID '<-' 'execute' expression;

stdout: expression '->' 'stdout';


// Expressions
expression : 
    // Function Call
    ID '(' (expression (',' expression)*)? ')' #ExprFunctionCall

    // pen instance
    | 'new' expression? #ExprNew

    // Math
    | op=('+'|'-') expression #ExprUnary
    | (Integer|Real) op='º' #ExprConvToRad
    | expression op=('*'|'/'|'//'|'\\\\') expression #ExprMultDivMod
    | expression op=('+'|'-') expression #ExprAddSub

    // Concatenation
    | expression expression #ExprStringConcat

    // tname conversion
    | 'int' '(' expression ')' #ExprToInt
    | 'real' '(' expression ')' #ExprToReal
    | 'string' '(' expression ')' #ExprToString
    | 'bool' '(' expression ')' #ExprToBool

    // Pen instructions (the pen itself should be returned to allow operations chain)
    | expression op=('down'|'up') #ExprPenUnary
    | expression op=('forward'|'backward'|'left'|'right'|'pause') expression #ExprPenOperator
    | expression op='<-' Property expression #ExprSetProperty

    // stdin
    | 'stdin' expression #ExprStdin

    // Comparison
    | expression op=('=='|'!='|'<'|'<='|'>'|'>=') expression #ExprComp

    // Boolean expressions
    | expression op=('and'|'or') expression #ExprBoolOp

    // Assign
    | assign #ExprAssign

    // Litterals
    | Integer #ExprInteger
    | Real #ExprReal
    | String #ExprString
    | Color #ExprColor
    | point #ExprPoint
    | Boolean #ExprBool
    | PI #ExprPi

    // Identifier
    | ID #ExprId

    // Parentized expression
    | '(' expression ')' #ExprParent
;

pause: 'pause' expression;

// Assign 
assign: ID op='=' expression;

// tnames
point: '(' x=expression ',' y=expression ')';

Property: ('color'|'pressure'|'thickness'|'orientation'|'position');

String: '"' (EscapeSequence | ~["\\])* '"'
    | '\'' (EscapeSequence | ~['\\])* '\'';
fragment EscapeSequence: '\\' . ;

Integer: [0-9]+;
Real: [0-9]+ '.' [0-9]+;

Boolean: 'true' | 'false';

Color: (
        'white'|'black'|'gray'|'lightgray'|'darkgray'
        |'green'|'lime'|'darkgreen'|'lightgreen'
        |'red'|'darkred'|'orange'|'darkorange'|'brown'
        |'blue'|'darkblue'|'lightblue'|'skyblue'|'lightskyblue'|'deepskyblue'
        |'yellow'|'lightyellow'
        |'cyan'|'darkcyan'
        |'magenta'|'darkmagenta'|'lightmagenta'
        |'violet'|'darkviolet'
        |'lightcyan'|'purple'|'pink'|'deeppink'|'lightpink'
        |'silver'|'gold'|'goldenrod'|'darkgoldenrod'
    )
    | '#' HEX HEX HEX HEX HEX HEX;

PI: 'PI';

tname: (PEN | REAL | INT | STRING | BOOL | POINT);
PEN: 'pen';
REAL: 'real';
INT: 'int';
STRING: 'string';
POINT: 'point';
BOOL: 'bool';

fragment HEX: [0-9a-fA-F];

// Identifiers
ID: [a-zA-Z_][a-zA-Z_0-9]*;

// Comments '%'
COMMENT: '%' ~[\n]* -> skip;


// Whitespace
WS: [ \t\r\n]+ -> skip;
