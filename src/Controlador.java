import java.util.List;

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
                case 1: tentarIniciarJogo();
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
                    dialogos.mostrarGaleriaConquistas(personagemService);
                    break;

                case 6:
                    dialogos.mostrarMensagem("Encerrando o sistema. Até a próxima!");
                    limparTela();
                    break;
                default:
                    dialogos.mostrarMensagem("Opção inválida!");
            }
        } while (opcao != 6);
    }

    // Tenta iniciar um novo jogo pedindo o nome do save
    private void tentarIniciarJogo() {
        boolean jogando = true;
        while (jogando) {
            try {
                // ANTES DE INICIAR O JOGO, PEDE O NOME DO SAVE
                String nomeSave = dialogos.pedirNomeSave();
                gerenciadorDeSave.setNomeSaveAtual(nomeSave);

                iniciarJogo();
                jogando = false; // Se chegar ao fim sem exceções, sai do ciclo
            } catch (ReiniciarPartidaException e) {
                limparTela();
                dialogos.mostrarMensagem("\n--- REINICIANDO SISTEMA A PARTIR DO ZERO ---");
                // Repete o ciclo, pedindo o nome do save novamente para a nova tentativa
            }
        }
    }

    // Tenta carregar um jogo a partir da lista
    private void tentarCarregarJogo() {
        boolean jogando = true;
        while (jogando) {
            try {
                carregarJogoSalvo();
                jogando = false;
            } catch (ReiniciarPartidaException e) {
                limparTela();
                dialogos.mostrarMensagem("\n--- REINICIANDO SISTEMA A PARTIR DO ZERO ---");
                tentarIniciarJogo(); // Volta para o Novo Jogo limpo
                jogando = false;
            }
        }
    }

    private void iniciarJogo() {
        // Fluxo de Personalização
        String nomePersonagem = dialogos.pedirNomePersonagem();
        int opcaoAntecedente = dialogos.escolherAntecedente();

        Personagem jogador = personagemService.inicializarPersonagemCustomizado(nomePersonagem, opcaoAntecedente);

        dialogos.mostrarMensagem("\nIniciando 'Reação em Cadeia' com " + jogador.getNome() + " [" + jogador.getAntecedente() + "]...");
        dialogos.pausarParaLeitura();

        // --- TEMPORADA 1 ---
        TemporadaUm temporadaUm = new TemporadaUm(dialogos, personagemService);
        if (temporadaUm.executarTemporada(jogador) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(1);

        // --- TEMPORADA 2 ---
        TemporadaDois temporadaDois = new TemporadaDois(dialogos, personagemService);
        if (temporadaDois.executarTemporada(jogador) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(2);

        // --- TEMPORADA 3 ---
        TemporadaTres temporadaTres = new TemporadaTres(dialogos, personagemService);
        if (temporadaTres.executarTemporada(jogador) || gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(3);

        // --- TEMPORADA 4 ---
        TemporadaQuatro temporadaQuatro = new TemporadaQuatro(dialogos, personagemService);
        int escolhaRotaFinal = temporadaQuatro.executarTemporada(jogador);

        if (gerenciadorDeSessao.lidarComGameOver(personagemService)) return;
        mostrarStatusAtual();
        salvarProgressoAutomatico(4);

        // --- TEMPORADA 5 (DESFECHO FINAL) ---
        TemporadaCinco temporadaCinco = new TemporadaCinco(dialogos, personagemService);
        temporadaCinco.executarTemporada(jogador, escolhaRotaFinal);

        gerenciadorDeSessao.lidarComFimDeCampanha();
    }

    private void carregarJogoSalvo() {
        boolean noMenuSaves = true;

        while (noMenuSaves) {
            // Busca a lista atualizada a cada ciclo
            List<Progresso> savesDisponiveis = gerenciadorDeSave.listarSaves();
            Progresso saveEscolhido = dialogos.mostrarMenuDeSaves(savesDisponiveis);

            if (saveEscolhido != null) {
                // Pergunta o que fazer com o save escolhido
                int acao = dialogos.mostrarOpcoesDoSave(saveEscolhido.getNomeSave());

                if (acao == 1) { // 1 - CARREGAR
                    gerenciadorDeSave.setNomeSaveAtual(saveEscolhido.getNomeSave());
                    personagemService.setPersonagem(saveEscolhido.getPersonagem());

                    dialogos.mostrarMensagem("\nJogo '" + saveEscolhido.getNomeSave() + "' carregado com sucesso!");
                    dialogos.mostrarMensagem("Retomando a partir da Temporada " + saveEscolhido.getTemporadaAtual());
                    dialogos.pausarParaLeitura();

                    continuarJogoDaTemporada(saveEscolhido.getTemporadaAtual());
                    noMenuSaves = false; // Sai do menu e começa a jogar

                } else if (acao == 2) { // 2 - APAGAR
                    gerenciadorDeSave.setNomeSaveAtual(saveEscolhido.getNomeSave());
                    gerenciadorDeSave.apagarSave(); // Apaga o ficheiro JSON correspondente

                    dialogos.mostrarMensagem("\nSave '" + saveEscolhido.getNomeSave() + "' apagado permanentemente!");
                    dialogos.pausarParaLeitura();
                    // O ciclo continua e mostra a lista de saves atualizada sem o que acabou de apagar
                }
                // Se a ação for 0 (Voltar), não faz nada e o ciclo repete, mostrando a lista novamente

            } else {
                // Se o jogador digitou 0 na lista de saves, sai do menu de carregar
                noMenuSaves = false;
            }
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