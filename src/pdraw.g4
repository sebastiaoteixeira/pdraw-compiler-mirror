grammar pdraw;

// Define the grammar for the parser
program: statement* EOF;

statement: (define | expression | declaration | stdout | pause | execution) ';';


// Statements and expressions
define: 'define' (penDefinition | canvasDefinition);

penDefinition: 'pen' ID '{' propertyDefinition* '}';
propertyDefinition: Property '=' expression ';';

canvasDefinition: 'canvas' ID String point;

declaration: type=('pen'|'real'|'int'|'string'|'point') ID ('=' expression)?;



execution: ID '<-' 'execute' expression;

pause: 'pause' expression;

stdout: expression '->' 'stdout';


// Expressions
expression : 
    // pen instance
    'new' expression? #ExprNew

    // Math
    | op=('+'|'-') expression #ExprUnary
    | expression op='º' #ExprConvToRad
    | expression op=('*'|'/'|'//'|'%') expression #ExprMultDivMod
    | expression op=('+'|'-') expression #ExprAddSub

    // Concatenation
    | String expression #ExprStringConcat

    // Type conversion
    | 'int' '(' expression ')' #ExprToInt
    | 'real' '(' expression ')' #ExprToReal
    | 'string' '(' expression ')' #ExprToString

    // Pen instructions (the pen itself should be returned to allow operations chain)
    | expression op=('down'|'up') #ExprPenUnary
    | expression op=('forward'|'left'|'right') expression #ExprPenOperator
    | expression op='<-' Property expression #ExprSetProperty

    // stdin
    | 'stdin' String #ExprStdin

    // Assign
    | ID op='=' expression #ExprAssign

    // Identifier
    | ID #ExprId

    // Litterals
    | Integer #ExprInteger
    | Real #ExprReal
    | String #ExprString
    | Color #ExprColor
    | point #ExprPoint

    // Parentized expression
    | '(' expression ')' #ExprParent
;


// Types
point: '(' x=expression ',' y=expression ')';

Property: ('color'|'pressure'|'thickness'|'orientation'|'position');
Color: ('red'|'green'|'blue'|'black'|'white'|'yellow'|'cyan'|'magenta');

String: '"' (EscapeSequence | ~['"\\])* '"';
fragment EscapeSequence: '\\' . ;

Integer: [0-9]+;
Real: [0-9]+ '.' [0-9]+;


// Identifiers
ID: [a-zA-Z_][a-zA-Z_0-9]*;

// Comments '%'
COMMENT: '%' ~[\n]* -> skip;


// Whitespace
WS: [ \t\r\n]+ -> skip;
