public class GerenciadorDeSessao {
    private Dialogos dialogos;
    private GerenciadorDeSave gerenciadorDeSave;

    public GerenciadorDeSessao(Dialogos dialogos, GerenciadorDeSave gerenciadorDeSave) {
        this.dialogos = dialogos;
        this.gerenciadorDeSave = gerenciadorDeSave;
    }

    public boolean lidarComGameOver(PersonagemService personagemService) {
        if (personagemService.isGameOver()) {
            dialogos.mostrarMensagem("\nGAME OVER: O seu corpo cedeu ao cansaço ou a sua família abandonou-o.");
            oferecerOpcoesDeFimDeJogo();
            return true;
        }
        return false;
    }

    public void lidarComFimDeCampanha() {
        dialogos.mostrarMensagem("\n[Fim da Campanha. Obrigado por jogar 'Ponto de Ebulição'!]");
        oferecerOpcoesDeFimDeJogo();
    }

    private void oferecerOpcoesDeFimDeJogo() {
        dialogos.mostrarMensagem("\nO que deseja fazer agora?");
        dialogos.mostrarMensagem("1 - Reiniciar Partida (Apagar progresso atual)");
        dialogos.mostrarMensagem("2 - Voltar ao Menu Principal");
        System.out.print("Sua escolha: ");

        int escolha = dialogos.lerInteiroSeguro();

        if (escolha == 1) {
            reiniciarPartida();
        } else {
            dialogos.mostrarMensagem("Retornando ao Menu Principal...\n");
        }
    }

    private void reiniciarPartida() {
        dialogos.mostrarMensagem("\nLimpando o progresso anterior e reiniciando a jornada...");
        gerenciadorDeSave.apagarSave();
        throw new ReiniciarPartidaException();
    }
}