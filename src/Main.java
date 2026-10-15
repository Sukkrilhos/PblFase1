public class Main {
    public static void main(String[] args) {
        // 1. Instancia o sistema de gravação único
        GerenciadorDeSave gerenciadorDeSave = new GerenciadorDeSave();

        // 2. Instancia as Regras de Negócio, passando o Gerenciador de Save
        PersonagemService service = new PersonagemService(gerenciadorDeSave);

        // 3. Instancia a Interface (View)
        Dialogos dialogos = new Dialogos();

        // 4. Instancia o Controlador
        Controlador controlador = new Controlador(dialogos, service, gerenciadorDeSave);

        // Inicia a aplicação
        controlador.iniciarSistema();
    }
}