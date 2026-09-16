// Personagem.java
public class Personagem {
    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome = "Valter Branco"; // Protagonista fixo do roteiro


    // Atributos do jogo "Reação em Cadeia"
    private int dinheiro = 0;
    private int saude = 100; // Começa com 100, mas o câncer vai drenar isso
    private int moralEtica = 100;
    private int relacaoFamiliar = 100;

    public String getNome() { return nome; }

    public int getDinheiro() { return dinheiro; }
    public void alterarDinheiro(int valor) { this.dinheiro += valor; }

    public int getSaude() { return saude; }
    public void alterarSaude(int valor) { this.saude += valor; }

    public int getMoralEtica() { return moralEtica; }
    public void alterarMoral(int valor) { this.moralEtica += valor; }

    public int getRelacaoFamiliar() { return relacaoFamiliar; }
    public void alterarRelacao(int valor) { this.relacaoFamiliar += valor; }


}