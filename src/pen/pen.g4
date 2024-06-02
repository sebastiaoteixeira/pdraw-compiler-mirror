grammar pen;

program: compound EOF;

statement: (pause | stdout | expression | declaration | block | penOperation) ';' | (if | for | until | while);

compound: statement*;

block: '{' compound '}';

declaration_element: (assign | ID);

declaration: type=tname declaration_element (',' declaration_element)*;

while: 'while' '(' expression ')' statement;

until: 'until' '(' expression ')' statement;

for: 'for' '(' statement expression ';' expression ')' statement; 

if: 'if' '(' expression ')' statement ('else'  statement )?;

pause: 'pause' expression;

stdout: expression '->' 'stdout';

// Pen operations (in this case they are not expressions)
penOperation:
    op=('down'|'up') #PenUnary
    | op=('forward'|'left'|'right'|'backward') expression #PenOperator
    | Property expression #SetProperty
    ;

expression:
    op=('+'|'-') expression #ExprUnary
    | (Integer|Real) op='º' #ExprConvToRad
    | expression op=('*'|'/'|'//'|'\\\\') expression #ExprMultDivMod
    | expression op=('+'|'-') expression #ExprAddSub

    | expression expression #ExprStringConcat

    | 'int' '(' expression ')' #ExprToInt
    | 'real' '(' expression ')' #ExprToReal
    | 'string' '(' expression ')' #ExprToString
    | 'bool' '(' expression ')' #ExprToBool

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


// Assign 
assign: ID op='=' expression;

// Types
tname: ('pen'|'real'|'int'|'string'|'point'|'bool');
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

fragment HEX: [0-9a-fA-F];

// Identifiers
ID: [a-zA-Z_][a-zA-Z_0-9]*;

// Comments '%'
COMMENT: '%' ~[\n]* -> skip;


// Whitespace
WS: [ \t\r\n]+ -> skip;