# Syntax-Analyzer-1
Simple Syntax Analyzer

Data la seguente grammatica

(
 N = {Program, Stmt, Expr, Term},
 T = {ID, IF, THEN, RELOP, NUMBER, ;, ASSIGN},   
 S = Program
 
 P = {
          Program -> Program ; Stmt
                           |  Stmt
           Stmt -> IF Expr THEN Stmt
                     | ID ASSIGN Expr
          Expr ->  Term  RELOP Term
                    |   Term
          Term ->  ID
                     |  NUMBER
       }
)

Dove i tokens sono per lo più quelli definiti in Lexical Analyzer https://github.com/Francesco182g/Lexical-Analyzer-1. 

Esempi di frasi corrette : 
•	a := 5
•	if a > 1 then if x < y then z := x >= 3;   a := 5; b := a <= b


Il parser deve invocare la funzione next_token() sviluppato in Lexical Analyzer.
Esempio di parser top-down a discesa ricorsiva (potete direttamente modificare questo).
La richiesta dei token avviene on demand durante il parsing e non prima.
