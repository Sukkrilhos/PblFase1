import java.util.Scanner;

public class Controlador {
    private Dialogos dialogos;
    private PersonagemService personagemService;
    private Scanner scanner;

    public Controlador(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarSistema() {
        int opcao;
        do {
            dialogos.mostrarMenuPrincipal();
            opcao = lerInteiroSeguro();

            switch (opcao) {
                case 1:
                    iniciarJogo();
                    break;
                case 2:
                    dialogos.mostrarInstrucoes();
                    break;
                case 3:
                    dialogos.mostrarCreditos();
                    break;
                case 4:
                    dialogos.mostrarMensagem("Encerrando o sistema. Até a próxima!");
                    break;
                default:
                    dialogos.mostrarMensagem("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private void iniciarJogo() {
        Personagem valter = personagemService.inicializarValter();
        dialogos.mostrarMensagem("\nIniciando 'Reação em Cadeia'...");

        // --- TEMPORADA 1 ---
        TemporadaUm temporadaUm = new TemporadaUm(dialogos, personagemService, scanner);
        boolean fimDeJogoT1 = temporadaUm.executarTemporada(valter);

        if (fimDeJogoT1 || checarFimDeJogo()) return;
        mostrarStatusAtual();

        // --- TEMPORADA 2 ---
        TemporadaDois temporadaDois = new TemporadaDois(dialogos, personagemService, scanner);
        boolean fimDeJogoT2 = temporadaDois.executarTemporada(valter);

        if (fimDeJogoT2 || checarFimDeJogo()) return;
        mostrarStatusAtual();

        // --- TEMPORADA 3 ---
        TemporadaTres temporadaTres = new TemporadaTres(dialogos, personagemService, scanner);
        boolean fimDeJogoT3 = temporadaTres.executarTemporada(valter);

        if (fimDeJogoT3 || checarFimDeJogo()) return;
        mostrarStatusAtual();

        dialogos.mostrarMensagem("\n[Fim da Temporada 3. As próximas temporadas seguem este mesmo padrão modular!]");
        pausarParaLeitura();
    }

    private boolean checarFimDeJogo() {
        if (personagemService.isGameOver()) {
            dialogos.mostrarMensagem("\nGAME OVER: Seu corpo cedeu ao câncer ou sua família te abandonou.");
            dialogos.mostrarMensagem("Retornando ao Menu Principal...\n");
            pausarParaLeitura();
            return true;
        }
        return false;
    }

    private void mostrarStatusAtual() {
        Personagem p = personagemService.getPersonagem();
        dialogos.mostrarMensagem("\n========================");
        dialogos.mostrarMensagem("   STATUS DE VALTER     ");
        dialogos.mostrarMensagem("========================");
        dialogos.mostrarMensagem("Dinheiro: R$ " + p.getDinheiro());
        dialogos.mostrarMensagem("Saúde: " + p.getSaude() + "%");
        dialogos.mostrarMensagem("Moral/Ética: " + p.getMoralEtica() + "%");
        dialogos.mostrarMensagem("Relação Familiar: " + p.getRelacaoFamiliar() + "%");
        dialogos.mostrarMensagem("========================\n");
        pausarParaLeitura();
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