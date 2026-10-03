public class Progresso {
    private Personagem personagem;
    private int temporadaAtual; // Salva se o jogador está na TemporadaUm, TemporadaDois, etc.

    public Progresso(Personagem personagem, int temporadaAtual) {
        this.personagem = personagem;
        this.temporadaAtual = temporadaAtual;
    }

    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }

    public int getTemporadaAtual() { return temporadaAtual; }
    public void setTemporadaAtual(int temporadaAtual) { this.temporadaAtual = temporadaAtual; }
}