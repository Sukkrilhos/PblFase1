public class Main {
    public static void main(String[] args) {
        //  Instancia o Banco de Dados (Repository)
        PersonagemRepository repository = new PersonagemRepository();

        // 2. Instancia as Regras de Negócio (Service), passando o Repository
        PersonagemService service = new PersonagemService(repository);

        // 3. Instancia a Interface (View)
        Dialogos dialogos = new Dialogos();

        // 4. Instancia o Controlador, unindo a View e o Service
        Controlador controlador = new Controlador(dialogos, service);

        // Inicia a aplicação
        controlador.iniciarSistema();
    }
}