import java.util.Scanner;

public class TemporadaDois {
    private Dialogos dialogos;
    private PersonagemService personagemService;
    private Scanner scanner;

    public TemporadaDois(Dialogos dialogos, PersonagemService personagemService, Scanner scanner) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
        this.scanner = scanner;
    }

    public boolean executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("       TEMPORADA 2: A AMEAÇA NO RETROVISOR");
        dialogos.mostrarMensagem("========================================================");
        pausarParaLeitura();

        // --- CAPÍTULO 3: O Furgão Encurralado ---
        dialogos.mostrarCenaFurgaoEncurraladoParte1(valter.getNome());
        int escolhaFurgao = lerInteiroSeguro(); // Lê a única escolha do capítulo

        dialogos.mostrarCenaFurgaoEncurraladoParte2(escolhaFurgao); // Mostra o resultado baseado na escolha

        if (escolhaFurgao == 1) {
            personagemService.aplicarConsequencia(-2000, 0, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -2000 | Moral -10]");
        } else {
            personagemService.aplicarConsequencia(0, 0, -20, 0);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Fuga arriscada bem-sucedida]");
        }

        if (personagemService.isGameOver()) return true;
        pausarParaLeitura();

        // --- CAPÍTULO 4: O Sangue na Pia ---
        dialogos.mostrarCenaSanguePiaParte1(valter.getNome());
        int escolhaPia = lerInteiroSeguro(); // Lê a única escolha do capítulo 4

        dialogos.mostrarCenaSanguePiaParte2(escolhaPia); // Mostra o resultado baseado na escolha

        if (escolhaPia == 1) {
            personagemService.aplicarConsequencia(0, +10, -5, +10);
            dialogos.mostrarMensagem("\n[Efeito: Saúde +10 | Relação Familiar +10]");
        } else {
            personagemService.aplicarConsequencia(0, -15, -10, -25);
            dialogos.mostrarMensagem("\n[Efeito: Saúde -15 | Relação Familiar -25]");
        }

        if (personagemService.isGameOver()) return true;

        dialogos.mostrarMensagem("\n[Fim da Temporada 2]");
        pausarParaLeitura();

        return false;
    }

    private void pausarParaLeitura() {
        dialogos.mostrarMensagem("(Pressione Enter para continuar...)");
        scanner.nextLine();
    }

    private int lerInteiroSeguro() {
        while (!scanner.hasNextInt()) {
            dialogos.mostrarMensagem("Entrada inválida! Digite o número correspondente.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}