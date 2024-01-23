# Comparador de Arquivos Excel - CLI

Este é um projeto desenvolvido com o objetivo de otimizar uma tarefa diária por meio da programação, proporcionando uma solução prática e funcional. A ideia surgiu da necessidade de comparar dois arquivos do Excel, cada um contendo uma lista de usuários com nomes, e-mails, etc. O desafio era verificar a presença de um usuário em ambas as listas para facilitar o processo de trabalho.

## Funcionalidades

O programa desenvolvido é uma Interface de Linha de Comando (CLI) que simplifica o processo de comparação de arquivos do Excel. Aqui estão algumas das principais funcionalidades:

<ul>
  <li> <b> Entrada de Caminhos de Arquivos: </b> O usuário informa os caminhos dos 2 arquivos que deseja comparar.</li>
  <li> <b> Geração de Terceiro Arquivo: </b> A CLI gera um terceiro arquivo, destacando os usuários em verde se estiverem presentes em ambas as listas, e em vermelho se estiverem apenas em uma das listas.</li>
</ul>

## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando Java e as seguintes dependências:

<ul>
  <li> <b> picocli: </b> Biblioteca para criação de interfaces de linha de comando de forma simples e eficiente.</li>
  <li> <b> poi-ooxml: </b> Biblioteca Apache POI para manipulação de arquivos do Microsoft Office. </li>
  <li> <b> lombok: </b> Biblioteca que simplifica a escrita de código em Java, reduzindo a necessidade de boilerplate. </li>
  <li> <b> log4j-core: </b> Framework para logging em Java. </li>
</ul>

## Como Usar

<ul>
  <li> Clone o repositório.</li>
  <li> Certifique-se de ter as dependências instaladas em seu ambiente de desenvolvimento. </li>
  <li> Compile o código fonte. </li>
  <li> Execute a CLI, informando os caminhos dos arquivos que deseja comparar. </li>
</ul>

```
java -jar executavel.jar -n caminho1\\arquivo1.xlsx  -2 caminho2\\arquivo2.xlsx -s caminhoSaida\\output.xlsx
```
### OBS: 
```Substitua os "/" por "\\"  exemplo: caminho1/arquivo1.xlsx --> caminho1\\arquivo1.xlsx ```

## Experiência e Aprendizado
Este projeto não apenas otimizou o processo diário, reduzindo significativamente o tempo necessário, mas também proporcionou uma oportunidade valiosa de estudo. A manipulação de arquivos do Excel em Java e a criação de uma CLI eficiente foram aspectos-chave no desenvolvimento.

Espero que essa experiência inspire outros desenvolvedores a abordar projetos práticos e relevantes para aprimorar suas habilidades. Se você tiver sugestões ou quiser saber mais sobre o projeto, estou à disposição para compartilhar!

