package classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class BancoDePalavras {

    private final List<String> palavras = new ArrayList<>();
    private final Random random = new Random();

   
    public BancoDePalavras(String caminhoArquivo) throws IOException {
        Path caminho = Paths.get(caminhoArquivo);
        List<String> linhas = Files.readAllLines(caminho, StandardCharsets.UTF_8);

        for (String linha : linhas) {
            String palavra = linha.trim();
            if (!palavra.isEmpty()) {
                palavras.add(palavra);
            }
        }

        if (palavras.isEmpty()) {
            throw new IllegalStateException("O arquivo não contém palavras: " + caminhoArquivo);
        }
    }

    
    public String sortearPalavra() {
        int indice = random.nextInt(palavras.size());
        return palavras.get(indice);
    }

 
    public int getQuantidade() {
        return palavras.size();
    }

   
    public List<String> getPalavras() {
        return Collections.unmodifiableList(palavras);
    }
}