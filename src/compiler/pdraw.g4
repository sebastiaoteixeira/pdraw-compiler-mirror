grammar pdraw;

// Define the grammar for the parser
program: compound EOF;

function: type=Type ID '(' parameter_list ')' statement;

statement: (define | declaration | expression | parameter_list | stdout | pause | execution | if | for | until | while | block | function | return) ';';

return: 'return' expression;

compound: statement*;

block: '{' compound '}';

// Statements and expressions
define: 'define' (penDefinition | canvasDefinition);

penDefinition: 'pen' ID '{' propertyDefinition* '}';

propertyDefinition: Property '=' expression ';';

canvasDefinition: 'canvas' ID String point;

parameter_list: parameter (',' parameter)* ;

parameter returns [String id]: type=Type ID;

declaration_element: (ID | assign);

declaration: type=Type declaration_element (',' declaration_element)*;

while: 'while' '(' expression ')' statement;

until: 'until' '(' expression ')' statement;

for: 'for' '(' declaration ';' expression ';' expression ')' statement; 

if: 'if' '(' expression ')' statement ('else'  statement )?;

execution: ID '<-' 'execute' expression;

pause: 'pause' expression;

stdout: expression '->' 'stdout';


// Expressions
expression : 
    // Function Call
    ID '(' expression (',' expression)* ')' #ExprFunctionCall

    // pen instance
    |'new' expression? #ExprNew

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
    | expression op=('forward'|'backward'|'left'|'right') expression #ExprPenOperator
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

// Assign 
assign: ID op='=' expression;

// Types
Type: ('pen'|'real'|'int'|'string'|'point'|'bool');
point: '(' x=expression ',' y=expression ')';

Property: ('color'|'pressure'|'thickness'|'orientation'|'position');

String: '"' (EscapeSequence | ~['"\\])* '"';
fragment EscapeSequence: '\\' . ;

Integer: [0-9]+;
Real: [0-9]+ '.' [0-9]+;

Boolean: 'true' | 'false';

Color: ('white'|'black'|'green'|'red'|'blue'|'yellow')
| '#' HEX HEX HEX HEX HEX HEX;

PI: 'PI';

fragment HEX: [0-9a-fA-F];

// Identifiers
ID: [a-zA-Z_][a-zA-Z_0-9]*;

// Comments '%'
COMMENT: '%' ~[\n]* -> skip;


// Whitespace
WS: [ \t\r\n]+ -> skip;
