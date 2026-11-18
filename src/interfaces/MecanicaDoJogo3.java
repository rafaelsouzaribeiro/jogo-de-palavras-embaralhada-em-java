package interfaces;

public class MecanicaDoJogo3 extends MecanicaDoJogo{
	
	public MecanicaDoJogo3() {
        this.tentativas = 3;            
        this.numeroFixoDePalavras = 3;
    }
	
	@Override
	public boolean fimDoJogo() {
		
		if(numerosTentativas>=tentativas) {
			fimDoJogo= false;
		}
		
		if(numerosPalavras>=numeroFixoDePalavras) {
			fimDoJogo= false;
		}
		
		return fimDoJogo;
	}

	@Override
	public void pontuar() {
		pontos=pontos+10;
	}


	@Override
	public void incrementarTentativas() {
		numerosTentativas++;
		
	}

	
	@Override
	public void zeraTentativasPorPalavras() {
		this.numerosTentativas=0;
		
	}

	@Override
	public void incrementarNumeroDePalavras() {
		this.numerosPalavras++;
		
	}
	
	@Override
	public int getNumeroDePalavra() {
		if(numerosPalavras>=numeroFixoDePalavras)return numeroFixoDePalavras;
		return numerosPalavras+1;
	}
	
	@Override
	public int getNumeroTotalDePalavras() {
		return numeroFixoDePalavras;
	}

	public int getTentativas() {
		if(numerosTentativas>=tentativas)return tentativas;
		return numerosTentativas+1;
	}
	
	@Override
	public int getTentativaEsperada() {
		return tentativas;
	}
	

}
