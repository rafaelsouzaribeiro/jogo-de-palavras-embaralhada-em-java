package interfaces;

import java.util.Random;
import main.Embaralhador;


public class FabricaEmbaralhadores {

    private final Random random = new Random();

    public Embaralhador getEmbaralhador() {
        int opcao = random.nextInt(3); 

        switch (opcao) {
            case 0:
                return new EmbaralhadorAleatorio();
            case 1:
                return new EmbaralhadorInvertido();
            default:
                return new EmbaralhadorTrocaPares();
        }
    }
}