public class TemporadaUm {
    private Dialogos dialogos;
    private PersonagemService personagemService;

    public TemporadaUm(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
    }

    public boolean executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("          TEMPORADA 1: A QUÍMICA DA NECESSIDADE");
        dialogos.mostrarMensagem("========================================================");

        // --- Cena 1: O Jantar ---
        dialogos.mostrarCenaJantarParte1(valter.getNome());
        int fala1 = dialogos.lerOpcao(1, 2);

        dialogos.mostrarCenaJantarParte2(valter.getNome(), fala1);
        int escolhaCriticaJantar = dialogos.lerOpcao(1, 2);

        if (escolhaCriticaJantar == 1) {
            personagemService.aplicarConsequencia(0, 0, -10, -10);
            dialogos.mostrarMensagem("\nMARISA: — Você está mentindo pra mim de novo. (Ela levanta e sai da mesa).");
            dialogos.mostrarMensagem("[Efeito: Moral -10 | Relação Familiar -10]");
        } else {
            personagemService.aplicarConsequencia(0, 0, 0, +15);
            dialogos.mostrarMensagem("\nMARISA: — Nós vamos dar um jeito. Nós sempre damos. (Ela te abraça).");
            dialogos.mostrarMensagem("[Efeito: Relação Familiar +15]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura();

        // =======================================================
        // PONTO DE SAVE MANUAL ENTRE OS CAPÍTULOS 1 E 2
        // =======================================================
        if (dialogos.oferecerSaveManual()) {
            personagemService.salvarProgressoManual(1); // Temporada 1
            dialogos.mostrarMensagem("\n[Progresso salvo manualmente com sucesso no seu slot!]");
            dialogos.pausarParaLeitura();
        }
        // =======================================================

        // --- Cena 2: A Escolha ---
        dialogos.mostrarCenaAEscolha(valter.getNome());
        int escolhaPrincipal = dialogos.lerOpcao(1, 2);

        if (escolhaPrincipal == 1) {
            // REGISTO DA CONQUISTA DA ROTA A
            personagemService.desbloquearConquista("ROTA_A");
            dialogos.mostrarDesfechoRotaA();
            dialogos.pausarParaLeitura();
            return true;
        } else {
            dialogos.mostrarMensagem("\nROTA B/C INICIADA: Você aceitou a proposta de Kadu.");
            personagemService.aplicarConsequencia(5000, 0, -30, 0);
            dialogos.mostrarMensagem("[Efeito: Dinheiro +5000 | Moral -30]");

            if (personagemService.isGameOver()) return true;
            dialogos.pausarParaLeitura();
        }

        // --- Cena 3: A Primeira Fornada ---
        dialogos.mostrarCenaFornada(valter.getNome());
        int escolhaFornada = dialogos.lerOpcao(1, 3);

        if (escolhaFornada == 1) {
            personagemService.aplicarConsequencia(-500, 0, 0, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -500 | Saúde Preservada]");
        } else if (escolhaFornada == 2) {
            personagemService.aplicarConsequencia(3000, -20, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +3000 | Saúde -20 | Moral -10]");
        } else if (escolhaFornada == 3) {
            personagemService.aplicarConsequencia(-1000, 0, 0, 0);
            personagemService.adicionarItemInventario("Máscara de Gás");
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -1000 | ITEM ADQUIRIDO: Máscara de Gás | Saúde Preservada]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura();

        return false;
    }
}