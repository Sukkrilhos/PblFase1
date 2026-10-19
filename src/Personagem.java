import java.util.ArrayList;
import java.util.List;

public class Personagem {
    private String nome;
    private String antecedente; // NOVO CAMPO
    private int dinheiro;
    private int saude;
    private int moralEtica;
    private int relacaoFamiliar;
    private java.util.List<String> inventario;

    public Personagem() {
        this.nome = "Valter";
        this.antecedente = "Professor Tradicional";
        this.dinheiro = 0;
        this.saude = 100;
        this.moralEtica = 100;
        this.relacaoFamiliar = 100;
        this.inventario = new java.util.ArrayList<>();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getAntecedente() { return antecedente; }
    public void setAntecedente(String antecedente) { this.antecedente = antecedente; }

    public int getDinheiro() { return dinheiro; }
    public void setDinheiro(int dinheiro) { this.dinheiro = dinheiro; }

    public int getSaude() { return saude; }
    public void setSaude(int saude) { this.saude = saude; }

    public int getMoralEtica() { return moralEtica; }
    public void setMoralEtica(int moralEtica) { this.moralEtica = moralEtica; }

    public int getRelacaoFamiliar() { return relacaoFamiliar; }
    public void setRelacaoFamiliar(int relacaoFamiliar) { this.relacaoFamiliar = relacaoFamiliar; }

    public java.util.List<String> getInventario() { return inventario; }

    public void adicionarItem(String item) {
        if (!inventario.contains(item)) {
            inventario.add(item);
        }
    }

    public boolean possuiItem(String item) {
        return inventario.contains(item);
    }

    public void alterarDinheiro(int valor) { this.dinheiro += valor; }
    public void alterarSaude(int valor) {
        this.saude = Math.min(100, Math.max(0, this.saude + valor));
    }
    public void alterarMoral(int valor) {
        this.moralEtica = Math.min(100, Math.max(0, this.moralEtica + valor));
    }
    public void alterarRelacao(int valor) {
        this.relacaoFamiliar = Math.min(100, Math.max(0, this.relacaoFamiliar + valor));
    }
}