import java.util.Scanner;

public class TemporadaTres {
    private Dialogos dialogos;
    private PersonagemService personagemService;
    private Scanner scanner;

    public TemporadaTres(Dialogos dialogos, PersonagemService personagemService, Scanner scanner) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
        this.scanner = scanner;
    }

    public boolean executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("      TEMPORADA 3: A EXPANSÃO DO IMPÉRIO");
        dialogos.mostrarMensagem("========================================================");

        // --- CAPÍTULO 5: O Novo Esconderijo (Interação 1) ---
        dialogos.mostrarCenaNovoEsconderijoParte1(valter.getNome());
        int fala1 = lerInteiroSeguro();

        dialogos.mostrarCenaNovoEsconderijoParte2(fala1);
        int escolhaEsconderijo = lerInteiroSeguro();

        if (escolhaEsconderijo == 1) {
            personagemService.aplicarConsequencia(0, -25, -5, 0);
            dialogos.mostrarMensagem("\n[Efeito: Saúde -25 | Você usou o dinheiro da radioterapia para pagar o aluguel do porão.]");
        } else {
            personagemService.aplicarConsequencia(0, 0, -20, 0);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Você voltou a roubar reagentes químicos da escola na madruga.]");
        }

        if (personagemService.isGameOver()) return true;
        pausarParaLeitura();

        // --- CAPÍTULO 6: O Atacadista (Interação 2) ---
        dialogos.mostrarCenaOAtacadistaParte1(valter.getNome());
        int fala2 = lerInteiroSeguro();

        dialogos.mostrarCenaOAtacadistaParte2(fala2);
        int escolhaAtacadista = lerInteiroSeguro();

        if (escolhaAtacadista == 1) {
            personagemService.aplicarConsequencia(10000, -30, -15, -15);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +10000 | Saúde -30 | Moral -15 | Relação Familiar -15]");
        } else {
            personagemService.aplicarConsequencia(3000, 0, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +3000 | Você irritou um distribuidor perigoso de alto escalão.]");
        }

        if (personagemService.isGameOver()) return true;
        pausarParaLeitura();

        return false; // Fim da Temporada 3 com sucesso
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