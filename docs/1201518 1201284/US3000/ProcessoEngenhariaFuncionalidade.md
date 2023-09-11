# US3000

# 1. Requisitos

**US3000** As Project Manager, I want the team to specify a grammar allowing to express several kinds of questionnaires.

# 2. Explicação da nossa Linguagem/Gramática

---

### Gramática desenvolvida

```
grammar questionnaire ;

/********* UTILS *********/

alfanumerico : PALAVRA | DIGITO ;
/* frase: the 2nd condition PALAVRA VIRGULA is for the specific case where it's adequate to only use a word and a comma, usually in the beginning of a message
 For example: Hello,
*/
frase : PALAVRA (VIRGULA? ESPACO (PALAVRA|DIGITO)+)*
       | DIGITO+ (VIRGULA? ESPACO (PALAVRA|DIGITO)+)*
       | PALAVRA VIRGULA ;
//title: it is a mandatory short sentence (for questionnaire and section)
title : frase   #lengthTitle
      ;

message : frase
        | (frase NEWLINE)+ ;


/********* QUESTIONNAIRE *********/

survey : questionnaire_id ESPACO title NEWLINE message? (NEWLINE section)+ NEWLINE NEWLINE message ;


//id -> mandatory alphanumeric value to univocally identify a questionnaire (E.g.: "COSM22-01")
questionnaire_id : alfanumerico+ ;


/********* SECTION *********/

section : numeric_id title NEWLINE message? 'Section Obligatoriness: ' obligatoriness (NEWLINE 'Repeatability: ' repeatability)? NEWLINE (question)+;

numeric_id : (DIGITO)+ ;

obligatoriness : MANDATORY
               | OPTIONAL
               | CONDITION_DEPENDENT DOIS_PONTOS ESPACO frase;

repeatability : DIGITO+ ;


/********* QUESTION *********/

question: numeric_id question_text PARENTESIS_ESQUERDO obligatoriness PARENTESIS_DIREITO (NEWLINE message)? NEWLINE 'Type: ' type NEWLINE message?;

option: numeric_id PARENTESIS_DIREITO frase (DOIS_PONTOS)? NEWLINE;

question_text : frase PONTO_INTERROGACAO ;

type: FREE_TEXT NEWLINE
    | NUMERIC (ESPACO PARENTESIS_ESQUERDO DECIMALS_ALLOWED PARENTESIS_DIREITO)? NEWLINE
    | SINGLE_CHOICE NEWLINE (option)+
    | MULTIPLE_CHOICE NEWLINE (option)+
    | SINGLE_CHOICE_WITH_INPUT NEWLINE (option)+
    | MULTIPLE_CHOICE_WITH_INPUT NEWLINE (option)+
    | SORTING_OPTION NEWLINE (option)+
    | SCALING_OPTION NEWLINE 'Scale: ' frase NEWLINE (option)+
    ;
```

---

### Variáveis Utilizadas na Gramática desenvolvida

```
DECIMALS_ALLOWED: 'Decimal numbers are allowed!';
SINGLE_CHOICE_WITH_INPUT: 'single choice with input';
MULTIPLE_CHOICE_WITH_INPUT: 'multiple choice with input';
FREE_TEXT: 'free text' ;
NUMERIC: 'numeric' ;
SINGLE_CHOICE: 'single choice' ;
MULTIPLE_CHOICE: 'multiple choice' ;
SORTING_OPTION: 'sorting option' ;
SCALING_OPTION: 'scaling option' ;
MANDATORY: 'mandatory';
OPTIONAL: 'optional';
CONDITION_DEPENDENT: 'condition dependent';
DIGITO : [0-9] ;
PALAVRA : [a-zA-Z]+;
PARENTESIS_DIREITO: ')';
PARENTESIS_ESQUERDO: '(';
VIRGULA : ',' ;
ESPACO : ' ' ;
DOIS_PONTOS : ':' ;
PONTO_INTERROGACAO : '?' ;
PONTO_EXCLAMACAO: '!';
NEWLINE:'\r'?'\n' ;         //return end of line
WS : [ \t\r.?!*'-]+ -> skip ; //skip spaces, tabs, newlines
```

---



