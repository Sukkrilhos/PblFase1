public class Controlador {
    private Dialogos dialogos;
    private PersonagemService personagemService;
    private GerenciadorDeSave gerenciadorDeSave;
    private GerenciadorDeSessao gerenciadorDeSessao;

    public Controlador(Dialogos dialogos, PersonagemService personagemService, GerenciadorDeSave gerenciadorDeSave) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
        this.gerenciadorDeSave = gerenciadorDeSave;

        // Instancia a nova classe de gestão de sessão
        this.gerenciadorDeSessao = new GerenciadorDeSessao(dialogos, gerenciadorDeSave);
    }

    public void iniciarSistema() {
        int opcao;
        do {
            dialogos.mostrarMenuPrincipal();
            opcao = dialogos.lerInteiroSeguro();

            switch (opcao) {
                case 1:
                    tentarIniciarJogo();
                    break;
                case 2:
                    tentarCarregarJogo();
                    break;
                case 3:
                    dialogos.mostrarInstrucoes();
                    break;
                case 4:
                    dialogos.mostrarCreditos();
                    break;
                case 5:
                    dialogos.mostrarMensagem("Encerrando o sistema. Até a próxima!");
                    limparTela();
                    break;
                default:
                    dialogos.mostrarMensagem("Opção inválida!");
            }
        } while (opcao != 5);
    }

    // Tenta iniciar um novo jogo. Se a exceção de reinício for lançada, o ciclo repete.
    private void tentarIniciarJogo() {
        boolean jogando = true;
        while (jogando) {
            try {
                iniciarJogo();
                jogando = false; // Se chegar ao fim sem exceções, sai do ciclo
            } catch (ReiniciarPartidaException e) {
                limparTela();
                dialogos.mostrarMensagem("\n--- REINICIANDO SISTEMA A PARTIR DO ZERO ---");
            }
        }
    }

    // Tenta carregar um jogo. Se morrer e escolher reiniciar, transita para um novo jogo limpo.
    private void tentarCarregarJogo() {
        boolean jogando = true;
        while (jogando) {
            try {
                carregarJogoSalvo();
                jogando = false;
            } catch (ReiniciarPartidaException e) {
                limparTela();
                dialogos.mostrarMensagem("\n--- REINICIANDO SISTEMA A PARTIR DO ZERO ---");
                tentarIniciarJogo(); // Inicia uma jornada do zero após apagar o save
                jogando = false;
            }
        }
    }

    private void iniciarJogo() {
        Personagem valter = personagemService.inicializarValter();
        dialogos.mostrarMensagem("\nIniciando 'Reação em Cadeia'...");

        // --- TEMPORADA 1 ---
        TemporadaUm temporadaUm = new TemporadaUm(dialogos, personagemService);
        if (temporadaUm.executarTemporada(valter) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(1);

        // --- TEMPORADA 2 ---
        TemporadaDois temporadaDois = new TemporadaDois(dialogos, personagemService);
        if (temporadaDois.executarTemporada(valter) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(2);

        // --- TEMPORADA 3 ---
        TemporadaTres temporadaTres = new TemporadaTres(dialogos, personagemService);
        if (temporadaTres.executarTemporada(valter) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(3);

        // --- TEMPORADA 4 ---
        TemporadaQuatro temporadaQuatro = new TemporadaQuatro(dialogos, personagemService);
        int escolhaRotaFinal = temporadaQuatro.executarTemporada(valter);

        if (gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(4);

        // --- TEMPORADA 5 (DESFECHO FINAL) ---
        TemporadaCinco temporadaCinco = new TemporadaCinco(dialogos, personagemService);
        temporadaCinco.executarTemporada(valter, escolhaRotaFinal);

        // Chama a gestão de fim de campanha (que também pode lançar o ReiniciarPartidaException)
        gerenciadorDeSessao.lidarComFimDeCampanha();
    }

    private void carregarJogoSalvo() {
        Progresso save = gerenciadorDeSave.carregarJogo();

        if (save != null) {
            personagemService.setPersonagem(save.getPersonagem());
            dialogos.mostrarMensagem("\nJogo carregado com sucesso! Retomando a partir da Temporada " + save.getTemporadaAtual());
            dialogos.pausarParaLeitura();

            continuarJogoDaTemporada(save.getTemporadaAtual());
        } else {
            dialogos.mostrarMensagem("\nNenhum arquivo de salvamento encontrado. Jogue e conclua pelo menos a Temporada 1 para gerar um save!");
            dialogos.pausarParaLeitura();
        }
    }

    private void continuarJogoDaTemporada(int temporada) {
        Personagem valter = personagemService.getPersonagem();

        if (temporada <= 1) {
            TemporadaUm temporadaUm = new TemporadaUm(dialogos, personagemService);
            if (temporadaUm.executarTemporada(valter) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
            mostrarStatusAtual();
            salvarProgressoAutomatico(1);
        }
        if (temporada <= 2) {
            TemporadaDois temporadaDois = new TemporadaDois(dialogos, personagemService);
            if (temporadaDois.executarTemporada(valter) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
            mostrarStatusAtual();
            salvarProgressoAutomatico(2);
        }
        if (temporada <= 3) {
            TemporadaTres temporadaTres = new TemporadaTres(dialogos, personagemService);
            if (temporadaTres.executarTemporada(valter) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
            mostrarStatusAtual();
            salvarProgressoAutomatico(3);
        }
        if (temporada <= 4) {
            TemporadaQuatro temporadaQuatro = new TemporadaQuatro(dialogos, personagemService);
            int escolhaRotaFinal = temporadaQuatro.executarTemporada(valter);
            if (gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
            mostrarStatusAtual();
            salvarProgressoAutomatico(4);

            TemporadaCinco temporadaCinco = new TemporadaCinco(dialogos, personagemService);
            temporadaCinco.executarTemporada(valter, escolhaRotaFinal);

            gerenciadorDeSessao.lidarComFimDeCampanha();
        }
    }

    private void salvarProgressoAutomatico(int temporadaAtual) {
        Progresso progresso = new Progresso(personagemService.getPersonagem(), temporadaAtual);
        gerenciadorDeSave.salvarJogo(progresso);
        dialogos.mostrarMensagem("\n[Progresso guardado automaticamente após a Temporada " + temporadaAtual + "]");
        dialogos.pausarParaLeitura();
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
        dialogos.pausarParaLeitura();
    }

    private void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}