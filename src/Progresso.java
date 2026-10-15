public class Progresso {
    private Personagem personagem;
    private int temporadaAtual;
    private String nomeSave; // NOVO: Nome do slot
    private String dataHora; // NOVO: Data e hora do save

    public Progresso(Personagem personagem, int temporadaAtual) {
        this.personagem = personagem;
        this.temporadaAtual = temporadaAtual;
    }

    public Personagem getPersonagem() { return personagem; }
    public void setPersonagem(Personagem personagem) { this.personagem = personagem; }

    public int getTemporadaAtual() { return temporadaAtual; }
    public void setTemporadaAtual(int temporadaAtual) { this.temporadaAtual = temporadaAtual; }

    public String getNomeSave() { return nomeSave; }
    public void setNomeSave(String nomeSave) { this.nomeSave = nomeSave; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
}