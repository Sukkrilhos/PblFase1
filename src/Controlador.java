import java.util.Scanner;

public class Controlador {
    private Dialogos dialogos;
    private PersonagemService personagemService;
    private Scanner scanner;
    private GerenciadorDeSave gerenciadorDeSave;

    public Controlador(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
        this.scanner = new Scanner(System.in);
        this.gerenciadorDeSave = new GerenciadorDeSave();
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
                    carregarJogoSalvo(); // Opção 2 para carregar o progresso
                    break;
                case 3:
                    dialogos.mostrarInstrucoes();
                    break;
                case 4:
                    dialogos.mostrarCreditos();
                    break;
                case 5:
                    dialogos.mostrarMensagem("Encerrando o sistema. Até a próxima!");
                    limparTela(); // Limpa o terminal antes de encerrar
                    break;
                default:
                    dialogos.mostrarMensagem("Opção inválida!");
            }
        } while (opcao != 5); // Condição ajustada para encerrar na opção 5
    }

    private void iniciarJogo() {
        Personagem valter = personagemService.inicializarValter();
        dialogos.mostrarMensagem("\nIniciando 'Reação em Cadeia'...");

        // --- TEMPORADA 1 ---
        TemporadaUm temporadaUm = new TemporadaUm(dialogos, personagemService, scanner);
        boolean fimDeJogoT1 = temporadaUm.executarTemporada(valter);

        if (fimDeJogoT1 || checarFimDeJogo()) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(1); // Cria o ponto de save automático da Temporada 1

        // --- TEMPORADA 2 ---
        TemporadaDois temporadaDois = new TemporadaDois(dialogos, personagemService, scanner);
        boolean fimDeJogoT2 = temporadaDois.executarTemporada(valter);

        if (fimDeJogoT2 || checarFimDeJogo()) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(2); // Cria o ponto de save automático da Temporada 2

        // --- TEMPORADA 3 ---
        TemporadaTres temporadaTres = new TemporadaTres(dialogos, personagemService, scanner);
        boolean fimDeJogoT3 = temporadaTres.executarTemporada(valter);

        if (fimDeJogoT3 || checarFimDeJogo()) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(3); // Cria o ponto de save automático da Temporada 3

        dialogos.mostrarMensagem("\n[Fim da Temporada 3. As próximas temporadas seguem este mesmo padrão modular!]");
        pausarParaLeitura();
    }

    private void carregarJogoSalvo() {
        Progresso save = gerenciadorDeSave.carregarJogo();

        if (save != null) {
            personagemService.setPersonagem(save.getPersonagem());
            dialogos.mostrarMensagem("\nJogo carregado com sucesso! Retomando a partir da Temporada " + save.getTemporadaAtual());
            pausarParaLeitura();

            continuarJogoDaTemporada(save.getTemporadaAtual());
        } else {
            dialogos.mostrarMensagem("\nNenhum arquivo de salvamento encontrado. Jogue e conclua pelo menos a Temporada 1 para gerar um save!");
            pausarParaLeitura();
        }
    }

    private void salvarProgressoAutomatico(int temporadaAtual) {
        Progresso progresso = new Progresso(personagemService.getPersonagem(), temporadaAtual);
        gerenciadorDeSave.salvarJogo(progresso);
        dialogos.mostrarMensagem("\n[Progresso guardado automaticamente após a Temporada " + temporadaAtual + "]");
        pausarParaLeitura();
    }

    private void continuarJogoDaTemporada(int temporada) {
        Personagem valter = personagemService.getPersonagem();

        // Retoma a partir da temporada guardada no JSON
        if (temporada <= 1) {
            TemporadaUm temporadaUm = new TemporadaUm(dialogos, personagemService, scanner);
            if (temporadaUm.executarTemporada(valter) || checarFimDeJogo()) return;
            mostrarStatusAtual();
            salvarProgressoAutomatico(1);
        }
        if (temporada <= 2) {
            TemporadaDois temporadaDois = new TemporadaDois(dialogos, personagemService, scanner);
            if (temporadaDois.executarTemporada(valter) || checarFimDeJogo()) return;
            mostrarStatusAtual();
            salvarProgressoAutomatico(2);
        }
        if (temporada <= 3) {
            TemporadaTres temporadaTres = new TemporadaTres(dialogos, personagemService, scanner);
            if (temporadaTres.executarTemporada(valter) || checarFimDeJogo()) return;
            mostrarStatusAtual();
            salvarProgressoAutomatico(3);
        }
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

    private void limparTela() {
        // Simula a limpeza do terminal imprimindo múltiplas quebras de linha
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
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