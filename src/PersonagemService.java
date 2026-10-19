public class PersonagemService {
    private GerenciadorDeSave gerenciadorDeSave;
    private Personagem personagemAtual;
    private GerenciadorDeConquistas conquistas; // NOVO

    public PersonagemService(GerenciadorDeSave gerenciadorDeSave) {
        this.gerenciadorDeSave = gerenciadorDeSave;
        this.conquistas = new GerenciadorDeConquistas(); // NOVO
    }

    public Personagem inicializarPersonagemCustomizado(String nome, int opcaoAntecedente) {
        this.personagemAtual = new Personagem();
        this.personagemAtual.setNome(nome);

        switch (opcaoAntecedente) {
            case 1:
                this.personagemAtual.setAntecedente("Professor Tradicional");
                break;

            case 2:
                this.personagemAtual.setAntecedente("Ex-Químico Industrial");
                this.personagemAtual.alterarDinheiro(3000);
                this.personagemAtual.alterarMoral(-10);
                break;

            case 3:
                this.personagemAtual.setAntecedente("Químico Precavido");
                this.personagemAtual.adicionarItem("Máscara de Gás");
                break;
        }

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
    // ==========================================
    // SISTEMA DE SAVE MANUAL
    // ==========================================

    public void salvarProgressoManual(int temporadaAtual) {
        if (this.personagemAtual != null) {
            Progresso progresso = new Progresso(this.personagemAtual, temporadaAtual);
            gerenciadorDeSave.salvarJogo(progresso);
        }
    }
    public void desbloquearConquista(String id) {
        if (!conquistas.possuiConquista(id)) {
            conquistas.registrarConquista(id);
            System.out.println("\n*** CONQUISTA DESBLOQUEADA! Verifique a Galeria no Menu Principal. ***");
        }
    }

    public boolean possuiConquista(String id) {
        return conquistas.possuiConquista(id);
    }
}
