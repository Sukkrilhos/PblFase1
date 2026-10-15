public class PersonagemService {
    private GerenciadorDeSave gerenciadorDeSave;
    private Personagem personagemAtual; // Mantém o personagem carregado na memória

    public PersonagemService(GerenciadorDeSave gerenciadorDeSave) {
        this.gerenciadorDeSave = gerenciadorDeSave;
        // O carregamento do save foi removido daqui, pois agora é o Controlador que escolhe qual ficheiro carregar.
    }

    public Personagem inicializarValter() {
        this.personagemAtual = new Personagem();
        gerenciadorDeSave.atualizarPersonagem(this.personagemAtual);
        return this.personagemAtual;
    }

    // Regra de negócio: Atualizar atributos baseados na escolha
    public void aplicarConsequencia(int deltaDinheiro, int deltaSaude, int deltaMoral, int deltaRelacao) {
        if (this.personagemAtual == null) return;

        this.personagemAtual.alterarDinheiro(deltaDinheiro);
        this.personagemAtual.alterarSaude(deltaSaude);
        this.personagemAtual.alterarMoral(deltaMoral);
        this.personagemAtual.alterarRelacao(deltaRelacao);

        // Guarda instantaneamente no JSON do save atual
        gerenciadorDeSave.atualizarPersonagem(this.personagemAtual);
    }

    // Verifica se os atributos zeraram (Game Over prematuro)
    public boolean isGameOver() {
        if (this.personagemAtual == null) return false;
        return this.personagemAtual.getSaude() <= 0 || this.personagemAtual.getRelacaoFamiliar() <= 0;
    }

    public Personagem getPersonagem() {
        return this.personagemAtual;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagemAtual = personagem;
        // Ao carregar um jogo, garante que o gerenciador sabe qual é o estado mais recente
        gerenciadorDeSave.atualizarPersonagem(personagem);
    }

    // ==========================================
    // SISTEMA DE INVENTÁRIO
    // ==========================================

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