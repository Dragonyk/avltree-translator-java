# Translator with AVL Tree

A text translation program that uses an AVL Tree data structure to efficiently store and retrieve dictionary translations.

## Features

- Single word translation between Portuguese and English
- User-friendly graphical interface
- Dynamic addition of new translations
- Dictionary persistence in file (`dicionario.dat`)
- Unsaved changes verification on exit

## Technologies Used

- Java Swing for the graphical interface
- AVL Tree data structure for efficient storage
- Serialization for data persistence

## How to Use

1. Run the program (`GUI_Tradutor` class)
2. Enter a word in the text field
3. Click "Traduzir" (Translate) to see available translations
4. If the word doesn't exist in the dictionary, you'll have the option to add new translations
5. Use "Salvar Dicionario" (Save Dictionary) to persist changes

## Code Structure

- `GUI_Tradutor`: Main class implementing the graphical interface
  - Component event handling
  - Interaction with the `Tradutor` class
  - Dictionary persistence

## Requirements

- Java 8 or higher

## Notes

The program checks for unsaved changes when closing and offers the option to save before exiting.

Translations are stored in the `dicionario.dat` file in the same directory as the program.


# Tradutor com Árvore AVL

Um programa de tradução de textos que utiliza um dicionário implementado com estrutura de dados Árvore AVL para armazenamento e recuperação eficiente de traduções.

## Funcionalidades

- Tradução de palavras individuais entre português e inglês
- Interface gráfica amigável
- Adição de novas traduções dinamicamente
- Persistência do dicionário em arquivo (`dicionario.dat`)
- Verificação de alterações não salvas ao sair

## Tecnologias Utilizadas

- Java Swing para a interface gráfica
- Estrutura de dados Árvore AVL para armazenamento eficiente
- Serialização para persistência dos dados

## Como Usar

1. Execute o programa (classe `GUI_Tradutor`)
2. Digite uma palavra no campo de texto
3. Clique em "Traduzir" para ver as traduções disponíveis
4. Se a palavra não existir no dicionário, você terá a opção de adicionar novas traduções
5. Use "Salvar Dicionario" para persistir as alterações

## Estrutura do Código

- `GUI_Tradutor`: Classe principal que implementa a interface gráfica
  - Manipulação de eventos dos componentes
  - Interação com a classe `Tradutor`
  - Persistência do dicionário

## Requisitos

- Java 8 ou superior

## Observações

O programa verifica se há alterações não salvas ao fechar e oferece a opção de salvar antes de sair.

As traduções são armazenadas no arquivo `dicionario.dat` no mesmo diretório do programa.
