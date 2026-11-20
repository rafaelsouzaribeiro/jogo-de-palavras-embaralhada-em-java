package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import classes.EmbaralhadorTrocaPares;
import interfaces.Embaralhador;

class EmbaralhadorTrocaParesTest extends EmbaralhadorContratoTest {

    @Override
    protected Embaralhador criarEmbaralhador() {
        return new EmbaralhadorTrocaPares();
    }

    @Test
    void deveTrocarParesComTamanhoPar() {
        assertEquals("badc", criarEmbaralhador().embaralhar("abcd"));
    }

    @Test
    void ultimaLetraFicaNoLugarQuandoTamanhoForImpar() {
        assertEquals("badce", criarEmbaralhador().embaralhar("abcde"));
    }

    @Test
    void deveTrocarParesDeBanana() {
        assertEquals("abanan", criarEmbaralhador().embaralhar("banana"));
    }

    @Test
    void palavraDeDuasLetrasInverte() {
        assertEquals("ba", criarEmbaralhador().embaralhar("ab"));
    }

    @Test
    void trocarDuasVezesVoltaAOriginal() {
        Embaralhador embaralhador = criarEmbaralhador();
        String palavra = "computador";
        assertEquals(palavra, embaralhador.embaralhar(embaralhador.embaralhar(palavra)));
    }
}