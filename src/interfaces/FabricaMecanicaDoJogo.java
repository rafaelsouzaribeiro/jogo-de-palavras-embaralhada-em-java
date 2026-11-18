package interfaces;

import java.util.Random;

public class FabricaMecanicaDoJogo {
	private final Random random = new Random();
	
	public MecanicaDoJogo getMecanicaDojogo() {
		int posicao = random.nextInt(2);
		
		
		switch (posicao) {
			case 0: 
				return new MecanicaDoJogo3();
			case 1: 
				return new MecanicaDoJogo5();
			default:
				return new MecanicaDoJogo3();
			
		}
		
	}

}
