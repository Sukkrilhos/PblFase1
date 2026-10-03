// Personagem.java
public class Personagem {
    private String nome = "Valter Branco"; // Protagonista fixo do roteiro
    private int idade = 50;

    // Atributos do jogo "Reação em Cadeia"
    private int dinheiro = 0;
    private int saude = 100; // Começa com 100, mas o câncer vai drenar isso
    private int moralEtica = 100;
    private int relacaoFamiliar = 100;

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
    }
}