grammar pen;

program: command* EOF;

command: setOrientation
        | setPressure
        | setThickness
        | setColor
        | setPosition
        | penControl
        | penMove
        | rotate
        ;

setOrientation: 'orientation' expr ('º')? ';';
setPressure: 'pressure' expr ';';
setThickness: 'thickness' expr ';';
setColor: 'color'  expr ';';
setPosition: 'position' '(' expr ',' expr ')' ';';
penControl: ('up' | 'down') ';';
penMove: ('forward' | 'backward') expr ';';
rotate: ('left' | 'right') expr ('º')? ';';

expr: Integer 
    | Real
    | '(' expr ')'
    ;

Integer: [0-9]+;
Real: [0-9]+ '.' [0-9]+;

COMMENT: '%' ~[\n]* -> skip;
WS: [ \t\r\n]+ -> skip;
