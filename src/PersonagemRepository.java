public class PersonagemRepository {
    private Personagem personagemSalvo;

    // Salva o personagem no "banco de dados" (memória)
    public void salvar(Personagem personagem) {
        this.personagemSalvo = personagem;
    }

    // Busca o personagem atual
    public Personagem buscar() {
        return this.personagemSalvo;
    }
}