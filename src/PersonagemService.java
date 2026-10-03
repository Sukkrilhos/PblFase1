// PersonagemService.java
public class PersonagemService {
    private PersonagemRepository repository;

    public PersonagemService(PersonagemRepository repository) {
        this.repository = repository;
    }

    public Personagem inicializarValter() {
        Personagem p = new Personagem();
        repository.salvar(p);
        return p;
    }

    // Regra de negócio: Atualizar atributos baseados na escolha
    public void aplicarConsequencia(int deltaDinheiro, int deltaSaude, int deltaMoral, int deltaRelacao) {
        Personagem p = repository.buscar();
        if (p == null) return;

        p.alterarDinheiro(deltaDinheiro);
        p.alterarSaude(deltaSaude);
        p.alterarMoral(deltaMoral);
        p.alterarRelacao(deltaRelacao);
    }

    // Verifica se os atributos zeraram (Game Over prematuro)
    public boolean isGameOver() {
        Personagem p = repository.buscar();
        return p.getSaude() <= 0 || p.getRelacaoFamiliar() <= 0;
    }

    public Personagem getPersonagem() {
        return repository.buscar();
    }

    public void setPersonagem(Personagem personagem) {
    }
}