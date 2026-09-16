import java.util.Scanner;

public class TemporadaUm {
    private Dialogos dialogos;
    private PersonagemService personagemService;
    private Scanner scanner;

    public TemporadaUm(Dialogos dialogos, PersonagemService personagemService, Scanner scanner) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
        this.scanner = scanner;
    }

    public boolean executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("          TEMPORADA 1: A QUÍMICA DA NECESSIDADE");
        dialogos.mostrarMensagem("========================================================");

        // --- Cena 1: O Jantar ---
        dialogos.mostrarCenaJantarParte1(valter.getNome());
        int fala1 = lerInteiroSeguro();

        dialogos.mostrarCenaJantarParte2(valter.getNome(), fala1);
        int escolhaCriticaJantar = lerInteiroSeguro();

        if (escolhaCriticaJantar == 1) {
            personagemService.aplicarConsequencia(0, 0, -10, -10);
            dialogos.mostrarMensagem("\nMARISA: — Você está mentindo pra mim de novo. (Ela levanta e sai da mesa).");
            dialogos.mostrarMensagem("[Efeito: Moral -10 | Relação Familiar -10]");
        } else {
            personagemService.aplicarConsequencia(0, 0, 0, +15);
            dialogos.mostrarMensagem("\nMARISA: — Nós vamos dar um jeito. Nós sempre damos. (Ela te abraça).");
            dialogos.mostrarMensagem("[Efeito: Relação Familiar +15]");
        }
        pausarParaLeitura();

        // --- Cena 2: A Escolha ---
        dialogos.mostrarCenaAEscolha(valter.getNome());
        int escolhaPrincipal = lerInteiroSeguro();

        if (personagemService.isGameOver()) return true;

        if (escolhaPrincipal == 1) {
            dialogos.mostrarMensagem("\nROTA A INICIADA: Você recusou o mundo do crime.");
            dialogos.mostrarMensagem("Valter tenta seguir a vida de forma honesta, lidando com as dívidas e o peso da doença.");
            dialogos.mostrarMensagem("Sua moral permanece intacta, mas a pressão financeira sufoca a família dia após dia.");
            dialogos.mostrarMensagem("\n[FIM DA ROTA A: Uma vida comum, difícil, mas dentro da lei.]");
            pausarParaLeitura();
            return true; // Encerra a Temporada 1 imediatamente e volta ao menu
        } else {
            dialogos.mostrarMensagem("\nROTA B/C INICIADA: Você aceitou a proposta de Kadu.");
            personagemService.aplicarConsequencia(5000, 0, -30, 0);
            dialogos.mostrarMensagem("[Efeito: Dinheiro +5000 | Moral -30]");
            pausarParaLeitura();
        }

        // --- Cena 3: A Primeira Fornada ---
        dialogos.mostrarCenaFornada(valter.getNome());
        int escolhaFornada = lerInteiroSeguro();

        if (escolhaFornada == 1) {
            personagemService.aplicarConsequencia(-500, 0, 0, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -500 | Saúde Preservada]");
        } else {
            personagemService.aplicarConsequencia(3000, -20, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +3000 | Saúde -20 | Moral -10]");
        }

        pausarParaLeitura();
        return false; // Continua para a Temporada 2 normalmente se escolheu o crime
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