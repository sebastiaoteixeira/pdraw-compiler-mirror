grammar pen;

pen: statement;

statement: (compound|expression) ';';

compound: statement*;

expression: ';';