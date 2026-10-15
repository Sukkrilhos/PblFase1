import java.util.ArrayList;
import java.util.List;

public class Personagem {
    private String nome = "Valter Branco";
    private int idade = 50;

    private int dinheiro = 0;
    private int saude = 100;
    private int moralEtica = 100;
    private int relacaoFamiliar = 100;

    // NOVO: Sistema de Inventário
    private List<String> inventario = new ArrayList<>();

    public String getNome() { return nome; }
    public int getIdade() { return idade; }

    public int getDinheiro() { return dinheiro; }
    public void alterarDinheiro(int valor) { this.dinheiro += valor; }

    public int getSaude() { return saude; }
    public void alterarSaude(int valor) { this.saude += valor; }

    public int getMoralEtica() { return moralEtica; }
    public void alterarMoral(int valor) { this.moralEtica += valor; }

    public int getRelacaoFamiliar() { return relacaoFamiliar; }
    public void alterarRelacao(int valor) { this.relacaoFamiliar += valor; }

    public void setNome(String nomeEscolhido) {
        this.nome = nomeEscolhido;
    }

    // --- MÉTODOS DO INVENTÁRIO ADICIONADOS ---
    public List<String> getInventario() { return inventario; }

    public void adicionarItem(String item) {
        if (!inventario.contains(item)) {
            inventario.add(item);
        }
    }

    public boolean possuiItem(String item) {
        return inventario.contains(item);
    }
}