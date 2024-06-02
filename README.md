# Tema **PDraw**, grupo **pdraw-t12**
-----

## Indíce
- ...

## 1.Introdução
- O trabalho que nos foi atribuido tinha como objetivo o desenvolvimento de uma linguagem que permite desenho de imagens, usando como abstração o desenho livre com canetas.

- A ideia segue a abstração inerente á biblioteca turtle do python. Em conversas dentro do grupo decidimos que a linguagem que ia gerar o código ia ser java, e o código iria ser gerado em python, a bibliteca gráfica utilizada foi a biblioteca graphics.py. Decisão tomada com o auxilio do professor Miguel.

- As ferramentas utilizadas por nós para concretizar os objetivos foram o ANTLR4 lecionado nas aulas, StringTemplates para geração de código e o conhecimento em java e python. Como ambiente de desenvolvimento utilizámos o Visual Studio Code e usámos o Git para controlo de versões.

## 2.Constituição dos grupos e participação individual global

| NMec | Nome | Participação |
|:---:|:---|:---:|
| 113786 | GABRIEL MARTINS SILVA | 0.0% |
| 114614 | MARTIM JOSÉ SOUTO MARQUES DOS SANTOS | 0.0% |
| 115665 | RAFAEL JORGE CRAVEIRO MIRANDA SEMEDO | 0.0% |
| 113765 | RUI DE FARIA MACHADO | 0.0% |
| 114624 | SEBASTIÃO LEITE RESENDE TEIXEIRA | 0.0% |
| 108615 | TIAGO FONSECA CRUZ | 0.0% |

## 3.PDraw, visão geral da linguagem
- Linguagem principal do projeto em desenvolvimento, que permite o desenho de imagens usando como abstração o desenho livre através de canetas.

- A caneta tem como propriedades, *color*, *pressure*, *thickness*, *orientation* e *position*.

-Para inicializarmos uma caneta temos que:
```pdraw

```
## 4.IPDraw, visão geral da linguagem
- IPDraw é uma versão simplificada e interpretada da linguagem PDraw. Nesta linguagem, existe apenas uma caneta implícita, facilitando o desenho através de comandos diretos e simples.

- ...

## 5. Arquitetura do Sistema
- A arquitetura do sistema é composta por diversos componentes que incluem a definição da gramática, a geração do código, e a execução do código gerado.

### 5.1 Definição da Gramática
- Utilizámos o ANTLR4 para definir a gramática das linguagens PDraw e IPDraw. A gramática está definida nos arquivos pdraw.g4 e pen.g4.
- A gramática da linguagem PDraw está definida no arquivo pdraw.g4. A gramática da linguagem IPDraw está definida no arquivo pen.g4.

### 5.2 Geração de Código
- A geração de código é feita utilizando StringTemplates em Java. O código gerado é Python, utilizando a biblioteca graphics.py para desenhar as imagens.

### 5.3 Execução do Código
- A execução do código é feita através de um interpretador.
- O interpretador foi implementado em Python e está definido nos arquivos interpreter.py e penMain.py. Este interpretador é responsável por executar os comandos das linguagens PDraw e IPDraw.

## 6. Requisitos e Características Implementadas

#### Mínimos:
| Requisito | Status | Descrição | Funcionalidades | 
|:---:|:---:|:---|:---|
| Definição de Canetas | Fully Completed | As canetas podem ser definidas com propriedades como cor, posição, orientação, espessura e pressão. Cada definição de caneta tem um identificador único. | [x] Definição de canetas;<br/>[x] Atributos de canetas (cor, posição, orientação, espessura, pressão). |
| Tipos de Dados | Fully Completed | São suportados os tipos de dados Integer, Real, String, Boolean e Point. | [x] Integer;<br/>[x] Real;<br/>[x] String;<br/>[x] Boolean;<br/>[x] Point. |
| Expressões Aritméticas | Fully Completed | Suporte para operações aritméticas envolvendo tipos de dados Integer e Real. | [x] Operações do mesmo tipo;<br/>[x] Operações entre tipos. |
| Escrita no Standard Output | Fully Completed | Suporte para escrita e concatenação de variáveis e texto no standard output. | [x] Escrita no output;<br/>[x] Concatenação de expressões. |
| Leitura do Standard Input | Fully Completed | Permite a leitura de valores a partir do standard input. | [x] Leitura stdin. |
| Conversão de Tipos | Fully Completed | Suporte para conversão de variáveis entre tipos, desde que a conversão seja possível. | [x] Conversão para Integer;<br/>[x] Conversão para Real;<br/>[x] Conversão para String;<br/>[x] Conversão para Boolean. |
| Movimentação e Rotação de Canetas | Fully Completed | Instruções para mover e rotacionar as canetas no canvas. | [x] Movimentar para frente e para trás;<br/>[x] Rotacionar para esquerda e direita. |
| Mudança de Atributos de Canetas | Fully Completed | Permite mudar atributos das canetas como cor, espessura, pressão e orientação. | [x] Mudança de cor;<br/>[x] Mudança de espessura;<br/>[x] Mudança de pressão;<br/>[x] Mudança de orientação. |
| Instrução de Pausa | Fully Completed | Implementação da instrução de pausa para interromper a execução por um tempo especificado. | [x] Instrução de pausa. |
| Verificação Semântica | Fully Completed | Verificação da consistência de tipos de dados e detecção de colisões entre tipos diferentes. | [x] Verificação de tipos de dados;<br/>[x] Detecção de colisões de tipos. |

#### Desejáveis:
| Requisito | Status | Descrição | Funcionalidades | 
|:---:|:---:|:---|:---|
| Operações Booleanas | Fully Completed | Expressões booleanas do tipo x==y, x!=y, x<y, x>y, x<=y e x>=y estão implementadas, com suporte para variáveis Boolean. | [x] Comparações booleanas;<br/>[x] Variáveis booleanas. |
| Instrução Condicional | Fully Completed | Estruturas 'if' com suporte de 'else' opcional e leitura de expressões booleanas. | [x] Instruções if;<br/>[x] Instruções else. |
| Instruções Iterativas | Fully Completed | Suporte para loops do tipo 'for', 'while' e 'until'. | [x] Loop for;<br/>[x] Loop while;<br/>[x] Loop until. |
| Múltiplos Canvas | Fully Completed | Suporte para múltiplos canvas e seleção do canvas ativo. | [x] Múltiplos canvas;<br/>[x] Seleção do canvas ativo. |

#### Avançadas:
| Requisito | Status | Descrição | Funcionalidades | 
|:---:|:---:|:---|:---|
| Funções e Variáveis Locais | Fully Completed | Implementação de funções e suporte para variáveis locais dentro das funções. | [x] Definição de funções;<br/>[x] Variáveis locais em funções. |
| Tabela de Símbolos | Fully Completed | Implementação de uma tabela de símbolos para resolver contextos de declaração e escopo de variáveis. | [x] Tabela de símbolos;<br/>[x] Resolução de escopo. |


## Contribuições

- Use esta secção para expôr as contribuições individuais dos vários elementos do grupo e que
  justificam as participações individuais globais apresentadas no início.


Este README fornece um resumo detalhado das funcionalidades implementadas no projeto PDraw e IPDraw, refletindo as características mínimas, desejáveis e avançadas especificadas nas diretrizes do projeto.