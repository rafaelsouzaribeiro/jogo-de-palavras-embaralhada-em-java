package interfaces;

public  abstract class MecanicaDoJogo {
	protected int pontos = 0;
	protected int tentativas=3;
	protected int numerosTentativas=0;
	protected int numerosPalavras=0;
	protected int numeroFixoDePalavras=5;
	protected boolean fimDoJogo=true;
	
	public abstract boolean fimDoJogo();
	public abstract void pontuar();
	public abstract void incrementarTentativas();
	public abstract void zeraTentativasPorPalavras();
	public abstract void incrementarNumeroDePalavras();
	public abstract int getTentativas();
	public abstract int getTentativaEsperada();
	public abstract int getNumeroDePalavra();
	public abstract int getNumeroTotalDePalavras();
	
	public int getPontos() {
		return pontos;
	}
	


}
