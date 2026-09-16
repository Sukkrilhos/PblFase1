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
        // --- NOVO: Captura do nome do protagonista ---
        dialogos.mostrarMensagem("\nAntes de começarmos, qual será o nome do protagonista?");
        System.out.print("Digite o nome (ou pressione Enter para usar 'Valter'): ");
        String nomeEscolhido = scanner.nextLine().trim();

        if (nomeEscolhido.isEmpty()) {
            nomeEscolhido = "Valter"; // Define o padrão caso o usuário apenas dê Enter
        }

        Personagem protagonista = personagemService.inicializarValter();
        protagonista.setNome(nomeEscolhido);

        dialogos.mostrarMensagem("\nIniciando 'Reação em Cadeia' com " + protagonista.getNome() + "...");
        pausarParaLeitura();

        // --- TEMPORADA 1 ---
        TemporadaUm temporadaUm = new TemporadaUm(dialogos, personagemService, scanner);
        boolean fimDeJogoT1 = temporadaUm.executarTemporada(protagonista);

        if (fimDeJogoT1 || checarFimDeJogo()) {
            dialogos.mostrarMensagem("Retornando ao Menu Principal...\n");
            return;
        }
        mostrarStatusAtual();

        // --- TEMPORADA 2 ---
        dialogos.mostrarMensagem("\nAvançando para a Temporada 2...");
        pausarParaLeitura();

        TemporadaDois temporadaDois = new TemporadaDois(dialogos, personagemService, scanner);
        boolean fimDeJogoT2 = temporadaDois.executarTemporada(protagonista);
        if (fimDeJogoT2 || checarFimDeJogo()) return;
        mostrarStatusAtual();

        // --- TEMPORADA 3 ---
        dialogos.mostrarMensagem("\nAvançando para a Temporada 3...");
        pausarParaLeitura();

        TemporadaTres temporadaTres = new TemporadaTres(dialogos, personagemService, scanner);
        boolean fimDeJogoT3 = temporadaTres.executarTemporada(protagonista);
        if (fimDeJogoT3 || checarFimDeJogo()) return;
        mostrarStatusAtual();

        // --- TEMPORADA 4 ---
        dialogos.mostrarMensagem("\nAvançando para a Temporada 4...");
        pausarParaLeitura();

        TemporadaQuatro temporadaQuatro = new TemporadaQuatro(dialogos, personagemService, scanner);
        int escolhaFinalRota = temporadaQuatro.executarTemporada(protagonista);
        if (checarFimDeJogo()) return;
        mostrarStatusAtual();

        // --- TEMPORADA 5 (DESFECHO FINAL) ---
        dialogos.mostrarMensagem("\nAvançando para o Desfecho Final...");
        pausarParaLeitura();

        TemporadaCinco temporadaCinco = new TemporadaCinco(dialogos, personagemService, scanner);
        temporadaCinco.executarTemporada(protagonista, escolhaFinalRota);

        dialogos.mostrarMensagem("\n[Fim da Campanha. Obrigado por jogar 'Reação em Cadeia'!]\n");
        pausarParaLeitura();
    }

    private boolean checarFimDeJogo() {
        if (personagemService.isGameOver()) {
            dialogos.mostrarMensagem("\nGAME OVER: Seu corpo cedeu à doença ou sua família te abandonou.");
            dialogos.mostrarMensagem("Retornando ao Menu Principal...\n");
            pausarParaLeitura();
            return true;
        }
        return false;
    }

    private void mostrarStatusAtual() {
        Personagem p = personagemService.getPersonagem();
        dialogos.mostrarMensagem("\n========================");
        // Exibe o nome escolhido em letras maiúsculas no painel de status
        dialogos.mostrarMensagem("   STATUS DE " + p.getNome().toUpperCase());
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