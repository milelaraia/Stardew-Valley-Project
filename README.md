# Projeto de Banco de Dados - Stardew Valley

### Dupla: Giuliana Torres Batistella - 491

Este projeto consiste no **desenho e modelagem de um banco de dados relacional** inspirado no jogo de RPG **Stardew Valley**.  
O objetivo é armazenar informações sobre jogadores, aldeões, animais, cultivos, habilidades e interações do universo do jogo, permitindo análises, registros de progresso e expansão de funcionalidades.

---

## Escopo do Projeto

O banco de dados foi projetado para englobar os seguintes aspectos do jogo:

- **Jogadores**: dados básicos, status de energia/saúde, nome da fazenda e inventário.  
- **Inventário e Itens**: gerenciamento de ferramentas, armas, anéis, flores, frutas, vegetais e outros itens.  
- **Aldeões (NPCs)**: personagens com hobbies, presentes favoritos e sistema de amizade.  
- **Animais**: criação de animais da fazenda e seus atributos.  
- **Cultivos e Estações**: lavouras gerais, grãos, hortaliças, turbéculos e a relação com estações (primavera, verão, outono, inverno).  
- **Habilidades e Níveis**: progressão de coleta, cultivo, pesca, mineração e combate.  

---

## Principais Entidades

- **Jogador**  
  - idJogador  
  - Nome  
  - Nome da Fazenda  
  - Animal Favorito  
  - Coisa Favorita  
  - Gênero  
  - Energia  
  - Saúde  
  - Relacionado com: `Inventário`  

- **Inventário**  
  - idInventário  
  - Contém: Itens (Armas, Ferramentas, Anéis etc.)  

- **Itens**  
  - idItens  
  - Ferramentas
  - Armas
  - Anéis  

- **Aldeões**  
  - idPersonagens  
  - Nome  
  - Hobby  
  - Presente que ama  
  - Relacionado com: `Amizade`  

- **Amizade**  
  - idAmizade  
  - Nível de amizade entre `Jogador` e `Aldeões`  

- **Animais**  
  - idAnimais  
  - Tipo
  - Nome  

- **Lavouras Geral**  
  - idLavouras Geral  
  - Fruta
  - Grão
  - Tubérculo
  - Flor
  - Vegetal
  - Hortaliça  
  - Associado a: `Estações`  

- **Estações**  
  - idEstações  
  - Primavera
  - Verão
  - Outono
  - Inverno  

- **Habilidades**  
  - idHabilidades  
  - Coleta
  - Cultivo
  - Pesca
  - Mineração
  - Combate    

---

## Relacionamentos (Resumo)

- `Jogador` 1—1 `Inventário`  
- `Inventário` 1—N `Itens`  
- `Jogador` 1—N `Aldeões` (via `Amizade`)  
- `Jogador` 1—N `Animais`  
- `Lavouras Geral` N—N `Estações`  
- `Jogador` 1—N `Habilidades`  

---
 
