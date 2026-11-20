package interfaces;


public interface Embaralhador {
	public enum NIVEL{
		FACIL,
	    MEDIO,
	    DIFICIL
	}
	
    String embaralhar(String palavra);
    String getNivel();
}