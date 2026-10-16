import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorDeConquistas {
    private static final String ARQUIVO_CONQUISTAS = "saves/conquistas.json";
    private Set<String> conquistas;
    private Gson gson;

    public GerenciadorDeConquistas() {
        this.gson = new Gson();
        this.conquistas = new HashSet<>();
        carregarConquistas();
    }

    private void carregarConquistas() {
        try {
            File arquivo = new File(ARQUIVO_CONQUISTAS);
            if (arquivo.exists()) {
                FileReader reader = new FileReader(arquivo);
                Type tipo = new TypeToken<HashSet<String>>(){}.getType();
                Set<String> carregado = gson.fromJson(reader, tipo);
                if (carregado != null) {
                    this.conquistas = carregado;
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar conquistas.");
        }
    }

    public void registrarConquista(String id) {
        if (!conquistas.contains(id)) {
            conquistas.add(id);
            salvarConquistas();
        }
    }

    private void salvarConquistas() {
        try {
            File diretorio = new File("saves/");
            if (!diretorio.exists()) diretorio.mkdirs();

            FileWriter writer = new FileWriter(ARQUIVO_CONQUISTAS);
            gson.toJson(conquistas, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao salvar conquistas.");
        }
    }

    public boolean possuiConquista(String id) {
        return conquistas.contains(id);
    }
}