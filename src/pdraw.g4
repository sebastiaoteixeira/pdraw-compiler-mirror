grammar pdraw;

// Define the grammar for the parser
program: statement* EOF;

statement: (define | expression | declaration | stdout | pause | execution | if | for | until | while | block) ';';

coumpound: statement*;

block: '{' coumpound '}';

// Statements and expressions
define: 'define' (penDefinition | canvasDefinition);

penDefinition: 'pen' ID '{' propertyDefinition* '}';
propertyDefinition: Property '=' expression ';';

canvasDefinition: 'canvas' ID String point;

declaration: type=('pen'|'real'|'int'|'string'|'point') ID ('=' expression)?;

while: 'while' '(' expression ')' statement;

until: 'until' '(' expression ')' statement;

for: 'for' '(' declaration ';' expression ';' expression ')' statement; 

if: 'if' '(' expression ')' statement ('else'  statement )?;

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
    | expression op=('*'|'/'|'//'|'\\\\') expression #ExprMultDivMod
    | expression op=('+'|'-') expression #ExprAddSub

    // Concatenation
    | expression expression #ExprStringConcat

    // Type conversion
    | 'int' '(' expression ')' #ExprToInt
    | 'real' '(' expression ')' #ExprToReal
    | 'string' '(' expression ')' #ExprToString
    | 'bool' '(' expression ')' #ExprToBool

    // Pen instructions (the pen itself should be returned to allow operations chain)
    | expression op=('down'|'up') #ExprPenUnary
    | expression op=('forward'|'left'|'right') expression #ExprPenOperator
    | expression op='<-' Property expression #ExprSetProperty

    // stdin
    | 'stdin' String #ExprStdin

    // Comparison
    | expression op=('=='|'!='|'<'|'<='|'>'|'>=') expression #ExprComp

    // Boolean expressions
    | expression op=('and'|'or') expression #ExprBoolOp

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
    | boolean #ExprBool

    // Parentized expression
    | '(' expression ')' #ExprParent
;


// Types
point: '(' x=expression ',' y=expression ')';

Property: ('color'|'pressure'|'thickness'|'orientation'|'position');
Color: ('white'|'black'|'green'|'red'|'blue'|'yellow');

String: '"' (EscapeSequence | ~['"\\])* '"';
fragment EscapeSequence: '\\' . ;

Integer: [0-9]+;
Real: [0-9]+ '.' [0-9]+;

boolean: 'true' | 'false';


// Identifiers
ID: [a-zA-Z_][a-zA-Z_0-9]*;

// Comments '%'
COMMENT: '%' ~[\n]* -> skip;


// Whitespace
WS: [ \t\r\n]+ -> skip;
