# 🔤 Jogo das Palavras Embaralhadas (Tarefa final do curso)

Jogo em **Java**, jogado no terminal. O programa sorteia uma palavra de um arquivo de texto, embaralha as letras e o jogador tenta descobrir qual é a palavra original.

A cada rodada, a forma de embaralhar (e o nível de dificuldade) é sorteada. As regras da partida — quantas palavras, quantas tentativas e como pontuar — são definidas pela **mecânica de jogo** escolhida.

---

## 📋 Índice

- [Como jogar](#-como-jogar)
- [Exemplo de execução](#-exemplo-de-execução)
- [Estrutura do projeto](#-estrutura-do-projeto)
- [Embaralhadores e níveis](#-embaralhadores-e-níveis)
- [Mecânicas do jogo](#-mecânicas-do-jogo)
- [Padrões de projeto utilizados](#-padrões-de-projeto-utilizados)
- [Como executar](#-como-executar)
- [Testes unitários](#-testes-unitários)

---

## 🎮 Como jogar

1. O jogo mostra a palavra embaralhada, o nível de dificuldade e o embaralhador usado.
2. Digite a palavra que você acha que é a correta.
3. **Acertou?** Ganha pontos e passa para a próxima palavra.
4. **Errou?** Perde uma tentativa daquela palavra.
5. O jogo termina quando você completa todas as palavras ou esgota as tentativas.

> A resposta não diferencia maiúsculas de minúsculas e ignora espaços nas pontas.

---

## 🖥️ Exemplo de execução

```text
Palavras carregadas: 20

Nível: MEDIO
Embaralhador usado: EmbaralhadorTrocaPares
Palavra embaralhada: idonssuaor
Número total de palavras: 1 de 5
Número de tentativa por palavra 1 de 5
Qual é a palavra?: dinossauro
Você Acertou!

Nível: DIFICIL
Embaralhador usado: EmbaralhadorAleatorio
Palavra embaralhada: tveseor
Número total de palavras: 2 de 5
Número de tentativa por palavra 1 de 5
Qual é a palavra?: a
Você Errou!
Número de tentativa por palavra 2 de 5
Qual é a palavra?: a
Você Errou!
Número de tentativa por palavra 3 de 5
Qual é a palavra?: a
Você Errou!
Número de tentativa por palavra 4 de 5
Qual é a palavra?: a
Você Errou!
Número de tentativa por palavra 5 de 5
Qual é a palavra?: a
Você Errou!
Número de tentativa por palavra 6 de 5
----------FIM DO JOGO---------
Pontos: 10
```

Nesta partida, o jogador acertou `dinossauro` (+10 pontos) e depois esgotou as 5 tentativas na palavra `sorvete`, encerrando o jogo.

---

## 📁 Estrutura do projeto

```text
JogoDasPalavrasEmbaralhadas/
├── src/
│   ├── main/
│   │   ├── Principal.java                 # ponto de entrada (main)
│   │   ├── BancoDePalavras.java           # lê o arquivo e sorteia palavras
│   │   └── Embaralhador.java              # interface + enum NIVEL
│   │
│   └── interfaces/
│       ├── EmbaralhadorInvertido.java     # nível FACIL
│       ├── EmbaralhadorTrocaPares.java    # nível MEDIO
│       ├── EmbaralhadorAleatorio.java     # nível DIFICIL
│       ├── FabricaEmbaralhadores.java     # sorteia um embaralhador
│       ├── MecanicaDoJogo.java            # classe abstrata com as regras base
│       ├── MecanicaDoJogo3.java           # partida com 3 palavras
│       ├── MecanicaDoJogo5.java           # partida com 5 palavras
│       └── FabricaMecanicaDoJogo.java     # sorteia a mecânica
│
├── test/
│   └── interfaces/
│       ├── EmbaralhadorContratoTest.java  # testes comuns a todo Embaralhador
│       ├── EmbaralhadorInvertidoTest.java
│       ├── EmbaralhadorTrocaParesTest.java
│       ├── EmbaralhadorAleatorioTest.java
│       └── FabricaEmbaralhadoresTest.java
│
└── README.md
```

### Responsabilidade de cada classe

| Classe | Tipo | Responsabilidade |
|---|---|---|
| `Principal` | classe | Monta os objetos, lê as respostas e controla o laço do jogo |
| `BancoDePalavras` | classe | Lê o arquivo `palavras.txt` (UTF-8) e sorteia palavras |
| `Embaralhador` | interface | Contrato: `embaralhar(palavra)` e `getNivel()` |
| `EmbaralhadorInvertido` / `TrocaPares` / `Aleatorio` | classes | Algoritmos de embaralhamento |
| `FabricaEmbaralhadores` | fábrica | Devolve um `Embaralhador` aleatório |
| `MecanicaDoJogo` | classe abstrata | Guarda pontos, tentativas e contagem de palavras |
| `MecanicaDoJogo3` / `MecanicaDoJogo5` | classes | Regras específicas de cada modo |
| `FabricaMecanicaDoJogo` | fábrica | Devolve a `MecanicaDoJogo` a ser usada |

---

## 🔀 Embaralhadores e níveis

| Nível | Embaralhador | Algoritmo | Exemplo (`futebol`) |
|---|---|---|---|
| 🟢 `FACIL` | `EmbaralhadorInvertido` | Inverte a ordem das letras | `lobetuf` |
| 🟡 `MEDIO` | `EmbaralhadorTrocaPares` | Troca cada par de letras vizinhas | `ufetobl` |
| 🔴 `DIFICIL` | `EmbaralhadorAleatorio` | Permuta as letras aleatoriamente | `bfoteul` |

- **Invertido:** padrão fixo; basta ler de trás para frente.
- **Troca pares:** cada letra anda no máximo uma posição; em palavras de tamanho ímpar, a última letra fica no lugar.
- **Aleatório:** sem padrão; tenta até 10 vezes gerar um resultado diferente da palavra original.

O nível é definido pelo enum dentro da interface:

```java
public interface Embaralhador {
    enum NIVEL { FACIL, MEDIO, DIFICIL }

    String embaralhar(String palavra);
    String getNivel();
}
```

---

## ⚙️ Mecânicas do jogo

| Mecânica | Palavras por partida | Tentativas por palavra | Pontos por acerto |
|---|---|---|---|
| `MecanicaDoJogo3` | 3 | 3 | 10 |
| `MecanicaDoJogo5` | 5 | 5 | 10 |

**O jogo termina quando:**
- o jogador completa todas as palavras da partida; **ou**
- esgota as tentativas de uma palavra.

A classe abstrata `MecanicaDoJogo` concentra os atributos comuns (`pontos`, `tentativas`, `numerosPalavras`…). As subclasses só alteram os valores no **construtor** e implementam as regras:

```java
public class MecanicaDoJogo3 extends MecanicaDoJogo {
    public MecanicaDoJogo3() {
        this.numeroFixoDePalavras = 3;
    }
    // ...
}
```

> ⚠️ Não declare novamente na subclasse os atributos da classe mãe — isso cria uma *segunda* variável com o mesmo nome (ocultação de atributo) e as regras deixam de funcionar.

---

## 🧩 Padrões de projeto utilizados

| Padrão | Onde | Benefício |
|---|---|---|
| **Strategy** | `Embaralhador` e suas implementações | Troca o algoritmo de embaralhamento sem mudar quem o usa |
| **Factory** | `FabricaEmbaralhadores`, `FabricaMecanicaDoJogo` | A `Principal` não conhece as classes concretas, só as abstrações |
| **Template / Herança** | `MecanicaDoJogo` (abstrata) | Estado e métodos comuns em um só lugar; subclasses mudam só as regras |

Princípios **SOLID** aplicados:
- **Aberto/Fechado:** para criar um novo embaralhador ou mecânica, basta adicionar uma classe e registrá-la na fábrica.
- **Inversão de Dependência:** a `Principal` trabalha com `Embaralhador` e `MecanicaDoJogo`, nunca com as implementações.

---

## ▶️ Como executar

### Pré-requisitos
- Java **11** ou superior (`java -version`)

### Arquivo de palavras
Crie o arquivo `palavras.txt` com **uma palavra por linha**. Por padrão, o jogo procura em:

| Sistema | Caminho |
|---|---|
| macOS | `/Users/<seu-usuario>/Downloads/palavras.txt` |
| Linux | `/home/<seu-usuario>/Downloads/palavras.txt` |
| Windows | `C:\Users\<seu-usuario>\Downloads\palavras.txt` |

Exemplo de conteúdo:

```text
abacaxi
banana
computador
dinossauro
elefante
```

### Pelo terminal

```bash
# compilar (a partir da pasta src)
cd src
javac main/*.java interfaces/*.java

# executar usando o arquivo padrão (~/Downloads/palavras.txt)
java main.Principal

# ou informar outro arquivo
java main.Principal /caminho/para/minhas-palavras.txt
```

### Pelo Eclipse
1. **File → Import → Existing Projects into Workspace** e selecione a pasta do projeto.
2. Clique com o botão direito em `Principal.java` → **Run As → Java Application**.
3. Para usar outro arquivo: **Run Configurations → Arguments → Program arguments**.

---

## ✅ Testes unitários

Os testes usam **JUnit 5** e ficam na pasta `test/interfaces`.

| Classe de teste | O que verifica |
|---|---|
| `EmbaralhadorContratoTest` | Regras que **todo** embaralhador deve cumprir: mesmo tamanho, mesmas letras, tratamento de `null`, vazio, 1 letra e letras repetidas |
| `EmbaralhadorInvertidoTest` | `banana → ananab`, acentos, palíndromos, inverter 2× volta ao original |
| `EmbaralhadorTrocaParesTest` | `abcd → badc`, tamanho ímpar, trocar 2× volta ao original |
| `EmbaralhadorAleatorioTest` | Resultado diferente do original, variação entre chamadas, letras preservadas |
| `FabricaEmbaralhadoresTest` | Nunca retorna `null` e sorteia os 3 tipos |

### Rodando no Eclipse
1. Botão direito no projeto → **Build Path → Add Libraries → JUnit → JUnit 5**.
2. Botão direito na pasta `test` → **Run As → JUnit Test**.

Para testar um **novo embaralhador**, basta estender o contrato:

```java
class MeuEmbaralhadorTest extends EmbaralhadorContratoTest {
    @Override
    protected Embaralhador criarEmbaralhador() {
        return new MeuEmbaralhador();
    }
}
```

---

## 🚀 Ideias de melhorias

- Pontuação por nível (`FACIL` = 5, `MEDIO` = 10, `DIFICIL` = 20).
- Evitar repetir palavras na mesma partida.
- Mecânica de "morte súbita" (1 tentativa por palavra, termina após 3 erros).
- Ranking com os melhores resultados salvos em arquivo.
