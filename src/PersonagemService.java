public class PersonagemService {
    private GerenciadorDeSave gerenciadorDeSave;
    private Personagem personagemAtual; // Mantém o personagem carregado na memória

    public PersonagemService(GerenciadorDeSave gerenciadorDeSave) {
        this.gerenciadorDeSave = gerenciadorDeSave;

        // Ao iniciar o serviço, tenta carregar o personagem guardado anteriormente
        Progresso p = gerenciadorDeSave.carregarJogo();
        if (p != null) {
            this.personagemAtual = p.getPersonagem();
        }
    }

    public Personagem inicializarValter() {
        this.personagemAtual = new Personagem();
        gerenciadorDeSave.atualizarPersonagem(this.personagemAtual);
        return this.personagemAtual;
    }

    public void aplicarConsequencia(int deltaDinheiro, int deltaSaude, int deltaMoral, int deltaRelacao) {
        if (this.personagemAtual == null) return;

        this.personagemAtual.alterarDinheiro(deltaDinheiro);
        this.personagemAtual.alterarSaude(deltaSaude);
        this.personagemAtual.alterarMoral(deltaMoral);
        this.personagemAtual.alterarRelacao(deltaRelacao);

        gerenciadorDeSave.atualizarPersonagem(this.personagemAtual); // Guarda instantaneamente no JSON
    }

    public boolean isGameOver() {
        if (this.personagemAtual == null) return false;
        return this.personagemAtual.getSaude() <= 0 || this.personagemAtual.getRelacaoFamiliar() <= 0;
    }

    public Personagem getPersonagem() {
        return this.personagemAtual;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagemAtual = personagem;
        gerenciadorDeSave.atualizarPersonagem(personagem);
    }

    // Métodos de Inventário adicionados anteriormente:
    public void adicionarItemInventario(String item) {
        if (this.personagemAtual != null) {
            this.personagemAtual.adicionarItem(item);
            gerenciadorDeSave.atualizarPersonagem(this.personagemAtual);
        }
    }

    public boolean verificarItem(String item) {
        return this.personagemAtual != null && this.personagemAtual.possuiItem(item);
    }
}