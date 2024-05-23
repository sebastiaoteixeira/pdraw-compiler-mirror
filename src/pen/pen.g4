grammar pen;

program: statement* EOF;

declaration_element: (ID | assign);

declaration: type=Type declaration_element (',' declaration_element)*;

statement: (pause | stdout | expression | for | if | while | until | declaration) ';';

while: 'while' '(' expression ')' statement;

until: 'until' '(' expression ')' statement;

for: 'for' '(' declaration ';' expression ';' expression ')' statement; 

if: 'if' '(' expression ')' statement ('else'  statement )?;

pause: 'pause' expression;

stdout: expression '->' 'stdout';

expression:
    op=('+'|'-') expression #ExprUnary
    | expression op='º' #ExprConvToRad
    | expression op=('*'|'/'|'//'|'\\\\') expression #ExprMultDivMod
    | expression op=('+'|'-') expression #ExprAddSub

    | expression expression #ExprStringConcat

    | 'int' '(' expression ')' #ExprToInt
    | 'real' '(' expression ')' #ExprToReal
    | 'string' '(' expression ')' #ExprToString
    | 'bool' '(' expression ')' #ExprToBool

    | op=('down'|'up') #ExprPenUnary
    | op=('forward'|'left'|'right'|'backward') expression #ExprPenOperator
    | Property expression #ExprSetProperty

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