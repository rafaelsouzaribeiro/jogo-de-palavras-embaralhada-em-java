package factory;

import java.util.Random;

import classes.MecanicaDoJogo;
import classes.MecanicaDoJogo3;
import classes.MecanicaDoJogo5;

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
