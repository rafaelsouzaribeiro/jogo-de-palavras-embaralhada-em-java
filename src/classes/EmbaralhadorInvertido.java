package classes;

import interfaces.Embaralhador;

public class EmbaralhadorInvertido implements Embaralhador {

    @Override
    public String embaralhar(String palavra) {
        if (palavra == null) {
            return null;
        }
        return new StringBuilder(palavra).reverse().toString();
    }
    
    @Override
 	public String getNivel() {
 		return Embaralhador.NIVEL.FACIL.toString();
 	}
}