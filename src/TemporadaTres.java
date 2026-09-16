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
        pausarParaLeitura();

        // --- CAPÍTULO 5: O Novo Esconderijo ---
        dialogos.mostrarCenaNovoEsconderijoParte1(valter.getNome());
        int escolhaEsconderijo = lerInteiroSeguro();

        dialogos.mostrarCenaNovoEsconderijoParte2(escolhaEsconderijo);

        if (escolhaEsconderijo == 1) {
            personagemService.aplicarConsequencia(0, -25, -5, 0);
            dialogos.mostrarMensagem("\n[Efeito: Saúde -25 | Você usou o dinheiro da radioterapia para pagar o aluguel.]");
        } else {
            personagemService.aplicarConsequencia(0, 0, -20, 0);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Você voltou a roubar reagentes químicos da escola.]");
        }

        if (personagemService.isGameOver()) return true;
        pausarParaLeitura(); // <--- Pausa essencial para ler o resultado do Cap. 5 e apertar Enter

        // --- CAPÍTULO 6: O Atacadista ---
        dialogos.mostrarCenaOAtacadistaParte1(valter.getNome());
        int escolhaAtacadista = lerInteiroSeguro();

        dialogos.mostrarCenaOAtacadistaParte2(escolhaAtacadista);

        if (escolhaAtacadista == 1) {
            personagemService.aplicarConsequencia(10000, -30, -15, -15);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +10000 | Saúde -30 | Moral -15 | Relação Familiar -15]");
        } else {
            personagemService.aplicarConsequencia(3000, 0, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +3000 | Você irritou um distribuidor perigoso.]");
        }

        if (personagemService.isGameOver()) return true;

        dialogos.mostrarMensagem("\n[Fim da Temporada 3]");
        pausarParaLeitura(); // <--- Pausa final para concluir a Temporada 3 com segurança

        return false; // Retorna o controle para o Controlador avançar para a Temporada 4
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