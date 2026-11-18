package interfaces;

import main.Embaralhador;

public class EmbaralhadorTrocaPares implements Embaralhador {

    @Override
    public String embaralhar(String palavra) {
        if (palavra == null) {
            return null;
        }

        char[] letras = palavra.toCharArray();
        for (int i = 0; i + 1 < letras.length; i += 2) {
            char temp = letras[i];
            letras[i] = letras[i + 1];
            letras[i + 1] = temp;
        }
        return new String(letras);
    }
    
    @Override
	public String getNivel() {
		return Embaralhador.NIVEL.MEDIO.toString();
	}
}