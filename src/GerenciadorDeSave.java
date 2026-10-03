import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GerenciadorDeSave {
    // Define o nome do arquivo JSON que será gerado na raiz do projeto
    private static final String ARQUIVO_SAVE = "save_ponto_ebulicao.json";
    private Gson gson;

    public GerenciadorDeSave() {
        // O GsonBuilder com setPrettyPrinting deixa o JSON formatado e legível para humanos
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarJogo(Progresso progresso) {
        try (FileWriter writer = new FileWriter(ARQUIVO_SAVE)) {
            gson.toJson(progresso, writer);
            System.out.println("Progresso salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    public Progresso carregarJogo() {
        try (FileReader reader = new FileReader(ARQUIVO_SAVE)) {
            return gson.fromJson(reader, Progresso.class);
        } catch (IOException e) {
            // Se o arquivo não existir, retorna null para indicar que é um Novo Jogo
            return null;
        }
    }
}