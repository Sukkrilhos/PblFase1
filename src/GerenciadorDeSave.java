import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeSave {
    private static final String DIRETORIO_SAVES = "saves/";
    private Gson gson;
    private String nomeSaveAtual; // Guarda o nome do save que está a ser jogado

    public GerenciadorDeSave() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        // Cria a pasta "saves/" automaticamente na raiz do projeto, se não existir
        File diretorio = new File(DIRETORIO_SAVES);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void setNomeSaveAtual(String nomeSave) {
        this.nomeSaveAtual = nomeSave;
    }

    // Gera o nome do ficheiro limpando caracteres especiais para evitar erros no Windows/Linux
    private String getCaminhoArquivoAtual() {
        String nomeArquivo = nomeSaveAtual.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        return DIRETORIO_SAVES + nomeArquivo + ".json";
    }

    private String obterDataHoraAtual() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    public void salvarJogo(Progresso progresso) {
        progresso.setNomeSave(this.nomeSaveAtual);
        progresso.setDataHora(obterDataHoraAtual()); // Carimba a hora exata sempre que salva

        try (FileWriter writer = new FileWriter(getCaminhoArquivoAtual())) {
            gson.toJson(progresso, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    private Progresso carregarJogoAtual() {
        if (nomeSaveAtual == null) return null;
        try (FileReader reader = new FileReader(getCaminhoArquivoAtual())) {
            return gson.fromJson(reader, Progresso.class);
        } catch (IOException e) {
            return null;
        }
    }

    public void atualizarPersonagem(Personagem personagem) {
        if (nomeSaveAtual == null) return; // Proteção

        Progresso progresso = carregarJogoAtual();
        if (progresso == null) {
            progresso = new Progresso(personagem, 1);
        } else {
            progresso.setPersonagem(personagem);
        }
        salvarJogo(progresso); // Grava atualizando a data e hora
    }

    public void apagarSave() {
        if (nomeSaveAtual != null) {
            File arquivo = new File(getCaminhoArquivoAtual());
            if (arquivo.exists()) {
                arquivo.delete();
            }
            nomeSaveAtual = null; // Limpa a referência
        }
    }

    // --- NOVO: MÉTODOS PARA MÚLTIPLOS SAVES ---

    // Lê todos os ficheiros .json dentro da pasta "saves/" e devolve uma lista
    public List<Progresso> listarSaves() {
        List<Progresso> saves = new ArrayList<>();
        File pasta = new File(DIRETORIO_SAVES);
        File[] arquivos = pasta.listFiles((dir, nome) ->
                nome.toLowerCase().endsWith(".json") && !nome.equalsIgnoreCase("conquistas.json"));

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                try (FileReader reader = new FileReader(arquivo)) {
                    Progresso p = gson.fromJson(reader, Progresso.class);
                    if (p != null) {
                        saves.add(p);
                    }
                } catch (Exception e) {
                    // Ignora ficheiros corrompidos ou mal formatados sem quebrar o programa
                }
            }
        }
        return saves;
    }
}