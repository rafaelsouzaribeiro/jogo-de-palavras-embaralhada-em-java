package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import classes.EmbaralhadorAleatorio;
import interfaces.Embaralhador;

class EmbaralhadorAleatorioTest extends EmbaralhadorContratoTest {

    @Override
    protected Embaralhador criarEmbaralhador() {
        return new EmbaralhadorAleatorio();
    }

    @Test
    void resultadoDeveSerDiferenteDaOriginal() {
        Embaralhador embaralhador = criarEmbaralhador();
        for (int i = 0; i < 100; i++) {
            assertNotEquals("abcdef", embaralhador.embaralhar("abcdef"));
        }
    }

    @Test
    void deveGerarResultadosVariados() {
        Embaralhador embaralhador = criarEmbaralhador();
        Set<String> resultados = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            resultados.add(embaralhador.embaralhar("abcdef"));
        }
        assertTrue(resultados.size() > 1, "O embaralhamento deveria variar entre as chamadas");
    }

    @Test
    void palavraDeDuasLetrasDistintasDeveInverter() {
        assertEquals("ba", criarEmbaralhador().embaralhar("ab"));
    }

    @Test
    void manterLetrasEmVariasExecucoes() {
        Embaralhador embaralhador = criarEmbaralhador();
        String palavra = "hipopotamo";
        for (int i = 0; i < 50; i++) {
            assertEquals(ordenarLetras(palavra), ordenarLetras(embaralhador.embaralhar(palavra)));
        }
    }
}