import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersonagemRepository {
    private static final String FICHEIRO_SAVE = "savegame.json";
    private Gson gson;

    public PersonagemRepository() {
        this.gson = new Gson();
    }

    // Método para salvar o personagem (substitui o antigo guardarProgresso)
    public void salvar(Personagem personagem) {
        try (FileWriter writer = new FileWriter(FICHEIRO_SAVE)) {
            gson.toJson(personagem, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    // Método para buscar/carregar o personagem gravado
    public Personagem buscar() {
        try (FileReader reader = new FileReader(FICHEIRO_SAVE)) {
            return gson.fromJson(reader, Personagem.class);
        } catch (IOException e) {
            // Se o ficheiro ainda não existir, retorna null
            return null;
        }
    }
}