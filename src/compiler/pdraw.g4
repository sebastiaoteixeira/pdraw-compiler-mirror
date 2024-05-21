grammar pdraw;

// Define the grammar for the parser
program: compound EOF;

function: type=('pen'|'real'|'int'|'string'|'point') ID '(' parameter_list ')' block;

statement: (define | expression | parameter_list | stdout | pause | execution | if | for | until | while | block | function) ';';

compound: statement*;

block: '{' compound '}';

// Statements and expressions
define: 'define' (penDefinition | canvasDefinition);

penDefinition: 'pen' ID '{' propertyDefinition* '}';

propertyDefinition: Property '=' expression ';';

canvasDefinition: 'canvas' ID String point;

parameter_list: parameter (',' declaration)* ;

parameter: type=('pen'|'real'|'int'|'string'|'point') ID;

declaration_element returns [String identifier] : (ID | assign);

declaration: type=('pen'|'real'|'int'|'string'|'point') declaration_element (',' declaration_element)*;

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
    | expression op=('forward'|'left'|'right') expression #ExprPenOperator
    | expression op='<-' Property expression #ExprSetProperty

    // stdin
    | 'stdin' String #ExprStdin

    // Comparison
    | expression op=('=='|'!='|'<'|'<='|'>'|'>=') expression #ExprComp

    // Boolean expressions
    | expression op=('and'|'or') expression #ExprBoolOp

    // Assign
    | assign #ExprAssign

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

    // Pen backward
    | expression op=('down'|'up'|'forward'|'left'|'right'|'backward') expression #ExprPenOperator
;

// Assign 
assign returns [String identifier]: ID op='=' expression;

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
