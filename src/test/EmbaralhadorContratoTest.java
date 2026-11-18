package test;

import main.Embaralhador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;



public abstract class EmbaralhadorContratoTest {

   
   protected abstract Embaralhador criarEmbaralhador();

   @Test
   void naoDeveRetornarNullParaPalavraValida() {
       assertNotNull(criarEmbaralhador().embaralhar("banana"));
   }

   @Test
   void deveManterOMesmoTamanho() {
       String palavra = "computador";
       String resultado = criarEmbaralhador().embaralhar(palavra);
       assertEquals(palavra.length(), resultado.length());
   }

   @Test
   void deveManterAsMesmasLetras() {
       String palavra = "tartaruga";
       String resultado = criarEmbaralhador().embaralhar(palavra);
       assertEquals(ordenarLetras(palavra), ordenarLetras(resultado));
   }

   @Test
   void deveRetornarNullQuandoPalavraForNull() {
       assertNull(criarEmbaralhador().embaralhar(null));
   }

   @Test
   void deveRetornarVazioQuandoPalavraForVazia() {
       assertEquals("", criarEmbaralhador().embaralhar(""));
   }

   @Test
   void palavraDeUmaLetraNaoMuda() {
       assertEquals("a", criarEmbaralhador().embaralhar("a"));
   }

   @Test
   void palavraComLetrasIguaisNaoMuda() {
       assertEquals("aaaa", criarEmbaralhador().embaralhar("aaaa"));
   }

  
   protected static String ordenarLetras(String texto) {
       char[] letras = texto.toCharArray();
       Arrays.sort(letras);
       return new String(letras);
   }
}
