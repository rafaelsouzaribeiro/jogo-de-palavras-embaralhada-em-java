# 🔤 Jogo das Palavras Embaralhadas

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
- [Problemas comuns](#-problemas-comuns)

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
Palavra embaralhada: ufetobl
Número total de palavras: 3
Número de tentativa por palavra: 3
Qual é a palavra?: futebol
Você Acertou!

Nível: MEDIO
Embaralhador usado: EmbaralhadorTrocaPares
Palavra embaralhada: ilomanad
Número total de palavras: 3
Número de tentativa por palavra: 3
Número total de palavras: 1 de 3
Número de tentativa por palavra 1 de 3
Qual é a palavra?: limonada
Você Acertou!

Nível: DIFICIL
Embaralhador usado: EmbaralhadorAleatorio
Palavra embaralhada: opmipthooa
Número total de palavras: 3
Número de tentativa por palavra: 3
Número total de palavras: 2 de 3
Número de tentativa por palavra 1 de 3
Qual é a palavra?: k
Você Errou!
Número de tentativa por palavra 1 de 3
Qual é a palavra?: k
Você Errou!
Número de tentativa por palavra 2 de 3
Qual é a palavra?: hiopotamo
Você Errou!
Número de tentativa por palavra 3 de 3
----------FIM DO JOGO---------
Pontos: 20

```

Nesta partida, o jogador acertou `dinossauro` (+10 pontos) e depois esgotou as 5 tentativas na palavra `sorvete`, encerrando o jogo.

---

## 📁 Estrutura do projeto

```text
JogoDasPalavrasEmbaralhadas/
├── src/
│   ├── main/
│   │   └── Principal.java                 # ponto de entrada (main)
│   │
│   ├── classes/
│   │   ├── BancoDePalavras.java           # lê o arquivo e sorteia palavras
│   │   ├── EmbaralhadorInvertido.java     # nível FACIL
│   │   ├── EmbaralhadorTrocaPares.java    # nível MEDIO
│   │   ├── EmbaralhadorAleatorio.java     # nível DIFICIL
│   │   ├── MecanicaDoJogo.java            # classe abstrata com as regras base
│   │   ├── MecanicaDoJogo3.java           # partida com 3 palavras
│   │   └── MecanicaDoJogo5.java           # partida com 5 palavras
│   │
│   ├── factory/
│   │   ├── FabricaEmbaralhadores.java     # sorteia um embaralhador
│   │   └── FabricaMecanicaDoJogo.java     # sorteia a mecânica
│   │
│   └── interfaces/
│       └── Embaralhador.java              # interface + enum NIVEL
│
├── test/
│   ├── EmbaralhadorContratoTest.java      # testes comuns a todo Embaralhador
│   ├── EmbaralhadorInvertidoTest.java
│   ├── EmbaralhadorTrocaParesTest.java
│   ├── EmbaralhadorAleatorioTest.java
│   └── FabricaEmbaralhadoresTest.java
│
├── palavras.txt                           # banco de palavras (uma por linha)
└── README.md
```

> ℹ️ O projeto **não** usa `module-info.java`. Se o Eclipse tiver criado esse arquivo, apague-o (veja [Problemas comuns](#-problemas-comuns)).

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
Crie o arquivo `palavras.txt` na **raiz do projeto** (mesmo nível de `src/`), com **uma palavra por linha**.

O jogo procura o arquivo na pasta de onde é executado:

```java
String arquivoPadrao = Paths.get(System.getProperty("user.dir"), "palavras.txt").toString();
```

No Eclipse, essa pasta já é a raiz do projeto.

Exemplo de conteúdo:

```text
abacaxi
banana
computador
dinossauro
elefante
```



### Pelo Eclipse
1. **File → Import → Existing Projects into Workspace** e selecione a pasta do projeto.
2. Clique com o botão direito em `Principal.java` → **Run As → Java Application**.
3. Para usar outro arquivo: **Run Configurations → Arguments → Program arguments**.

---

## ✅ Testes unitários

Os testes usam **JUnit 5** e ficam na pasta `test/`.

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

## 🛠️ Problemas comuns

### Erro nos testes por causa do `module-info.java`

Ao criar o projeto, o Eclipse pode gerar o arquivo **`src/module-info.java`** (sistema de módulos do Java 9+). Com ele, toda biblioteca precisa ser declarada como `requires`, e os testes com JUnit passam a dar erro de compilação.

**Solução:** apague o arquivo.

1. No **Package Explorer**, abra `src/`.
2. Clique com o botão direito em `module-info.java` → **Delete**.
3. **Project → Clean…** para recompilar.

> Para não acontecer em projetos novos: em **File → New → Java Project**, desmarque **"Create module-info.java file"**.

### `Erro ao ler o arquivo: palavras.txt`

O arquivo não está na pasta de execução.
- Confira se `palavras.txt` está na **raiz do projeto**, e não dentro de `src/`.
- No Eclipse: **Run → Run Configurations → Arguments → Working directory** deve estar em **Default**.

---

## 🚀 Ideias de melhorias

- Pontuação por nível (`FACIL` = 5, `MEDIO` = 10, `DIFICIL` = 20).
- Evitar repetir palavras na mesma partida.
- Mecânica de "morte súbita" (1 tentativa por palavra, termina após 3 erros).
- Ranking com os melhores resultados salvos em arquivo.