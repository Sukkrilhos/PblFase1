public class TemporadaCinco {
    private Dialogos dialogos;
    private PersonagemService personagemService;

    public TemporadaCinco(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
    }

    public void executarTemporada(Personagem valter, int escolhaRotaFinal) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("          TEMPORADA 5: O FIM DA LINHA");
        dialogos.mostrarMensagem("========================================================");
        dialogos.pausarParaLeitura();

        if (escolhaRotaFinal == 1) {
            if (personagemService.verificarItem("Passaportes Falsos")) {
                dialogos.mostrarCenaFugaSucesso();
                dialogos.mostrarMensagem("\n[FIM DA ROTA B ALTERNATIVA: Fuga perfeita. Você usou os seus itens com maestria!]");
            } else {
                dialogos.mostrarCenaFuga();
                personagemService.aplicarConsequencia(-valter.getDinheiro(), -valter.getSaude(), 0, -valter.getRelacaoFamiliar());
                dialogos.mostrarMensagem("\n[FIM DA ROTA B: Desastre total. Devia ter preparado uma rota de fuga segura...]");
            }
        } else {
            if (personagemService.verificarItem("Ricina")) {
                dialogos.mostrarMensagem("\n========================================================");
                dialogos.mostrarMensagem("CAPÍTULO 10: O Toque Silencioso (ROTA C - FINAL SECRETO)");
                dialogos.mostrarMensagem("========================================================");
                dialogos.mostrarMensagem("Em vez de explodir tudo e morrer no processo, você envenenou o ar do galpão com Ricina.");
                dialogos.mostrarMensagem("Você caminhou calmamente por entre os corpos intactos dos capangas e do Fidalgo.");
                dialogos.mostrarMensagem("Recuperou o seu dinheiro e fugiu na calada da noite. Frio e calculista.");
                dialogos.mostrarMensagem("========================================================\n");
                dialogos.mostrarMensagem("\n[FIM DA ROTA C ALTERNATIVA: Vitória limpa. Heisenberg puro.]");
            } else {
                dialogos.mostrarCenaAtaque();
                dialogos.mostrarMensagem("\n[FIM DA ROTA C: O último ato de química e redenção. O Cartel foi aniquilado com explosivos.]");
            }
        }

        dialogos.pausarParaLeitura();
    }
}