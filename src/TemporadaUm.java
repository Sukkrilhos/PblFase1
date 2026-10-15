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
        int fala1 = dialogos.lerInteiroSeguro();

        dialogos.mostrarCenaJantarParte2(valter.getNome(), fala1);
        int escolhaCriticaJantar = dialogos.lerInteiroSeguro();

        if (escolhaCriticaJantar == 1) {
            personagemService.aplicarConsequencia(0, 0, -10, -10);
            dialogos.mostrarMensagem("\nMARISA: — Você está mentindo pra mim de novo. (Ela levanta e sai da mesa).");
            dialogos.mostrarMensagem("[Efeito: Moral -10 | Relação Familiar -10]");
        } else {
            personagemService.aplicarConsequencia(0, 0, 0, +15);
            dialogos.mostrarMensagem("\nMARISA: — Nós vamos dar um jeito. Nós sempre damos. (Ela te abraça).");
            dialogos.mostrarMensagem("[Efeito: Relação Familiar +15]");
        }
        dialogos.pausarParaLeitura();

        // --- Cena 2: A Escolha ---
        dialogos.mostrarCenaAEscolha(valter.getNome());
        int escolhaPrincipal = dialogos.lerInteiroSeguro();

        if (personagemService.isGameOver()) return true;

        if (escolhaPrincipal == 1) {
            // Chama a mensagem de encerramento da Rota A
            dialogos.mostrarDesfechoRotaA();
            dialogos.pausarParaLeitura();
            return true;
        } else {
            dialogos.mostrarMensagem("\nROTA B/C INICIADA: Você aceitou a proposta de Kadu.");
            personagemService.aplicarConsequencia(5000, 0, -30, 0);
            dialogos.mostrarMensagem("[Efeito: Dinheiro +5000 | Moral -30]");
            dialogos.pausarParaLeitura();
        }

        // --- Cena 3: A Primeira Fornada ---
        dialogos.mostrarCenaFornada(valter.getNome());
        int escolhaFornada = dialogos.lerInteiroSeguro();

        if (escolhaFornada == 1) {
            personagemService.aplicarConsequencia(-500, 0, 0, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -500 | Saúde Preservada]");
        } else if (escolhaFornada == 2) {
            personagemService.aplicarConsequencia(3000, -20, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +3000 | Saúde -20 | Moral -10]");
        } else if (escolhaFornada == 3) {
            personagemService.aplicarConsequencia(-1000, 0, 0, 0);
            personagemService.adicionarItemInventario("Máscara de Gás"); // GANHA O ITEM
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -1000 | ITEM ADQUIRIDO: Máscara de Gás | Saúde Preservada]");
        }

        dialogos.pausarParaLeitura();
        return false;
    }
}