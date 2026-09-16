import java.util.Scanner;

public class TemporadaCinco {
    private Dialogos dialogos;
    private PersonagemService personagemService;
    private Scanner scanner;

    public TemporadaCinco(Dialogos dialogos, PersonagemService personagemService, Scanner scanner) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
        this.scanner = scanner;
    }

    public void executarTemporada(Personagem valter, int escolhaRotaFinal) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("          TEMPORADA 5: O FIM DA LINHA");
        dialogos.mostrarMensagem("========================================================");
        pausarParaLeitura();

        if (escolhaRotaFinal == 1) {
            // --- ROTA B: A QUEDA (Capítulos 9 e 10) ---
            dialogos.mostrarCenaFuga();
            personagemService.aplicarConsequencia(-valter.getDinheiro(), -valter.getSaude(), 0, -valter.getRelacaoFamiliar());
            dialogos.mostrarMensagem("\n[FIM DA ROTA B: Desastre total. O império ruiu e a família foi destruída.]");
        } else {
            // --- ROTA C: A VINGANÇA (Capítulos 9 e 10) ---
            dialogos.mostrarCenaAtaque();
            dialogos.mostrarMensagem("\n[FIM DA ROTA C: O último ato de química e redenção. O Cartel foi aniquilado.]");
        }

        pausarParaLeitura();
    }

    private void pausarParaLeitura() {
        dialogos.mostrarMensagem("(Pressione Enter para continuar...)");
        scanner.nextLine();
    }
}