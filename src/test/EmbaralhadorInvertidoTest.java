package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import classes.EmbaralhadorInvertido;
import interfaces.Embaralhador;;

class EmbaralhadorInvertidoTest extends EmbaralhadorContratoTest {

    @Override
    protected Embaralhador criarEmbaralhador() {
        return new EmbaralhadorInvertido();
    }

    @Test
    void deveInverterAPalavra() {
        assertEquals("ananab", criarEmbaralhador().embaralhar("banana"));
    }

    @Test
    void deveInverterPalavraComAcento() {
        assertEquals("oãmil", criarEmbaralhador().embaralhar("limão"));
    }

    @Test
    void palindromoContinuaIgual() {
        assertEquals("arara", criarEmbaralhador().embaralhar("arara"));
    }

    @Test
    void inverterDuasVezesVoltaAOriginal() {
        Embaralhador embaralhador = criarEmbaralhador();
        String palavra = "elefante";
        assertEquals(palavra, embaralhador.embaralhar(embaralhador.embaralhar(palavra)));
    }
}