package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.Embaralhador;


public class EmbaralhadorAleatorio implements Embaralhador {
	

    @Override
    public String embaralhar(String palavra) {
        if (palavra == null || palavra.length() < 2) {
            return palavra;
        }

        List<Character> letras = new ArrayList<>();
        for (char c : palavra.toCharArray()) {
            letras.add(c);
        }

        String resultado;
        int tentativas = 0;
       
        do {
            Collections.shuffle(letras);
            StringBuilder sb = new StringBuilder();
            for (char c : letras) {
                sb.append(c);
            }
            resultado = sb.toString();
            tentativas++;
        } while (resultado.equals(palavra) && tentativas < 10);

        return resultado;
    }

	@Override
	public String getNivel() {
		return Embaralhador.NIVEL.DIFICIL.toString();
	}
}