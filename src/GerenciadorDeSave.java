import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class GerenciadorDeSave {
    private static final String ARQUIVO_SAVE = "save_ponto_ebulicao.json";
    private Gson gson;

    public GerenciadorDeSave() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void salvarJogo(Progresso progresso) {
        try (FileWriter writer = new FileWriter(ARQUIVO_SAVE)) {
            gson.toJson(progresso, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    public Progresso carregarJogo() {
        try (FileReader reader = new FileReader(ARQUIVO_SAVE)) {
            return gson.fromJson(reader, Progresso.class);
        } catch (IOException e) {
            return null; // Retorna nulo se for um Novo Jogo
        }
    }


    public void atualizarPersonagem(Personagem personagem) {
        Progresso progresso = carregarJogo();
        if (progresso == null) {
            // Se o ficheiro ainda não existir, cria o progresso inicial na Temporada 1
            progresso = new Progresso(personagem, 1);
        } else {
            progresso.setPersonagem(personagem);
        }

        // Guarda silenciosamente (sem imprimir mensagem no terminal a cada escolha)
        try (FileWriter writer = new FileWriter(ARQUIVO_SAVE)) {
            gson.toJson(progresso, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar as consequências: " + e.getMessage());
        }
    }


    public void apagarSave() {
        File arquivo = new File(ARQUIVO_SAVE);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }
}