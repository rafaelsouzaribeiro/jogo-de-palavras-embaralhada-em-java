package main;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import interfaces.*;

public class Principal {

    public static void main(String[] args) {
        try {
        	String arquivoPadrao = Paths.get(System.getProperty("user.dir"), "palavras.txt").toString();
          String arquivo = args.length > 0 ? args[0] : arquivoPadrao;

          BancoDePalavras banco = new BancoDePalavras(arquivo);
          FabricaEmbaralhadores fabrica = new FabricaEmbaralhadores();
          FabricaMecanicaDoJogo mecanicas = new FabricaMecanicaDoJogo();
          MecanicaDoJogo mecanica = mecanicas.getMecanicaDojogo();
          Scanner scan = new Scanner(System.in);

          System.out.println("Palavras carregadas: " + banco.getQuantidade());
          String palavra = novaPalavra(banco, fabrica,mecanica);   

          while (mecanica.fimDoJogo()) {                 
              System.out.print("Qual é a palavra?: ");
              String resposta = scan.nextLine();
            

              if (resposta.trim().equalsIgnoreCase(palavra)) {
            	  System.out.println("Você Acertou!");
            	  mecanica.pontuar();
                  mecanica.zeraTentativasPorPalavras();
                  mecanica.incrementarNumeroDePalavras();
                  palavra = novaPalavra(banco, fabrica,mecanica); 
                  System.out.printf("Número total de palavras: %d de %d%n", mecanica.getNumeroDePalavra(), mecanica.getNumeroTotalDePalavras());
                  System.out.printf("Número de tentativa por palavra %d de %d%n", mecanica.getTentativas(), mecanica.getTentativaEsperada());
              }else{
            	  System.out.println("Você Errou!");
            	  System.out.printf("Número de tentativa por palavra %d de %d%n", mecanica.getTentativas(), mecanica.getTentativaEsperada());
                  mecanica.incrementarTentativas();
              }
          }

          System.out.println("----------FIM DO JOGO---------");
          System.out.printf("Pontos: %d%n", mecanica.getPontos());
          scan.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }
    

    private static String novaPalavra(BancoDePalavras banco, FabricaEmbaralhadores fabrica,MecanicaDoJogo mecanica) {
        String palavra = banco.sortearPalavra();
        Embaralhador embaralhador = fabrica.getEmbaralhador();
        

        System.out.println();
        System.out.println("Nível: " + embaralhador.getNivel());
        System.out.println("Embaralhador usado: " + embaralhador.getClass().getSimpleName());
        System.out.println("Palavra embaralhada: " + embaralhador.embaralhar(palavra));
        System.out.printf("Número total de palavras: %d%n", mecanica.getNumeroTotalDePalavras());
        System.out.printf("Número de tentativa por palavra: %d%n", mecanica.getTentativaEsperada());
       
        return palavra;
    }
}