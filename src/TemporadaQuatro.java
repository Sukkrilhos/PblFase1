public class TemporadaQuatro {
    private Dialogos dialogos;
    private PersonagemService personagemService;

    public TemporadaQuatro(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
    }

    public int executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("     TEMPORADA 4: O FALSO EQUILÍBRIO E O CERCO");
        dialogos.mostrarMensagem("========================================================");
        dialogos.pausarParaLeitura();

        // --- CAPÍTULO 7: O Fundo Falso ---
        dialogos.mostrarCenaFundoFalsoParte1(valter.getNome());
        int escolhaFundo = dialogos.lerOpcao(1, 3); // Atualizado para 3 opções

        dialogos.mostrarCenaFundoFalsoParte2(escolhaFundo);

        if (escolhaFundo == 1) {
            personagemService.aplicarConsequencia(0, 0, -20, -35);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Relação Familiar despencou -35]");
        } else if (escolhaFundo == 2) {
            personagemService.aplicarConsequencia(0, 0, -10, -50);
            dialogos.mostrarMensagem("\n[Efeito: Relação Familiar -50]");
        } else if (escolhaFundo == 3) {
            personagemService.aplicarConsequencia(-5000, 0, -30, -70);
            personagemService.adicionarItemInventario("Colete Balístico");
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -5000 | Relação Familiar -70 | ITEM ADQUIRIDO: Colete Balístico]");
        }

        if (personagemService.isGameOver()) return 0;
        dialogos.pausarParaLeitura();

        if (dialogos.oferecerSaveManual()) {
            personagemService.salvarProgressoManual(4); // Passa o número da Temporada atual (2)
            dialogos.mostrarMensagem("\n[Progresso salvo manualmente com sucesso no seu slot!]");
            dialogos.pausarParaLeitura();
        }

        // --- CAPÍTULO 8: A Encruzilhada / O Cerco ---
        dialogos.mostrarCenaEncruzilhadaParte1(valter.getNome());
        int escolhaEncruzilhada = dialogos.lerOpcao(1, 3); // Atualizado para 3 opções

        dialogos.mostrarCenaEncruzilhadaParte2(escolhaEncruzilhada);

        if (escolhaEncruzilhada == 1) {
            dialogos.mostrarMensagem("\n[ROTA B ESCOLHIDA: Você tentará a fuga desesperada com a família.]");
        } else if (escolhaEncruzilhada == 2) {
            dialogos.mostrarMensagem("\n[ROTA C ESCOLHIDA: Você escolheu o caminho da vingança e do confronto final.]");
        } else if (escolhaEncruzilhada == 3) {
            personagemService.aplicarConsequencia(0, 0, 10, 0); // Ganha moral por fazer a coisa certa
            personagemService.adicionarItemInventario("Dossiê do Cartel");
            dialogos.mostrarMensagem("\n[ROTA D ESCOLHIDA: Você escolheu entregar o seu império à polícia. ITEM: Dossiê do Cartel]");
        }

        dialogos.pausarParaLeitura();
        return escolhaEncruzilhada; // Retorna 1, 2 ou 3 para a Temporada 5
    }

}