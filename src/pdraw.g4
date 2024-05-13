grammar pdraw;

// Define the grammar for the parser
program: statement* EOF;

statement: (define | expression | declaration | stdout | pause | execution) ';';


// Statements and expressions
define: 'define' (penDefinition | canvasDefinition);

penDefinition: 'pen' ID '{' propertyDefinition* '}';
propertyDefinition: Property '=' expr ';';

canvasDefinition: 'canvas' ID String point;


declaration: type=('pen'|'real'|'int'|'string'|'point') ID;

expression: expr;

execution: 'execute' expr;

pause: 'pause' expr;

stdout: expr '->' 'stdout';


// Expressions
expr : 
    // pen instance
    'new' expr? #ExprNew

    // Math
    | op=('+'|'-') expr #ExprUnary
    | expr op='º' #ExprConvToRad
    | expr op=('*'|'/'|'//'|'%') expr #ExprMultDivMod
    | expr op=('+'|'-') expr #ExprAddSub

    // Concatenation
    | String expr #ExprStringConcat

    // Type conversion
    | 'int' '(' expr ')' #ExprToInt
    | 'real' '(' expr ')' #ExprToReal
    | 'string' '(' expr ')' #ExprToString

    // Pen instructions (the pen itself should be returned to allow operations chain)
    | expr op=('down'|'up') #ExprPenUnary
    | expr op=('forward'|'left'|'right') expr #ExprPenOperator
    | expr op='<-' Property expr #ExprSetProperty

    // stdin
    | 'stdin' String #ExprStdin

    // Assign
    | ID op='=' expr #ExprAssign

    // Identifier
    | ID #ExprId

    // Litterals
    | Integer #ExprInteger
    | Real #ExprReal
    | String #ExprString
    | point #ExprPoint

    // Parentized expression
    | '(' expr ')' #ExprParent
;


// Types
point: '(' expr ',' expr ')';

Property: ('color'|'pressure'|'thickness'|'orientation'|'position');

String: '"' (EscapeSequence | ~['"\\])* '"';
EscapeSequence: '\\' . ;

Integer: [0-9]+;
Real: [0-9]+ '.' [0-9]+;


// Identifiers
ID: [a-zA-Z_][a-zA-Z_0-9]*;

// Comments '%'
COMMENT: '%' ~[\r\n]* -> skip;


// Whitespace
WS: [ \t\r\n]+ -> skip;
