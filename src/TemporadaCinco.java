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
            // --- ROTA B: A QUEDA OU O DESAPARECIMENTO ---
            if (personagemService.verificarItem("Passaportes Falsos")) {
                personagemService.desbloquearConquista("ROTA_B_FUGA"); // CONQUISTA
                dialogos.mostrarCenaFugaSucesso(valter.getNome());
                dialogos.mostrarMensagem("\n[FIM DA ROTA B (SUCESSO): Fuga perfeita. Você usou os seus itens com maestria!]");
            } else if (personagemService.verificarItem("Colete Balístico")) {
                personagemService.desbloquearConquista("ROTA_B_PRISAO"); // CONQUISTA
                dialogos.mostrarCenaPrisao();
                personagemService.aplicarConsequencia(-valter.getDinheiro(), -50, 0, -50);
                dialogos.mostrarMensagem("\n[FIM DA ROTA B (SOBREVIVÊNCIA): O colete salvou a sua vida, mas a liberdade acabou.]");
            } else {
                personagemService.desbloquearConquista("ROTA_B_MORTE"); // CONQUISTA
                dialogos.mostrarCenaFuga();
                personagemService.aplicarConsequencia(-valter.getDinheiro(), -valter.getSaude(), 0, -valter.getRelacaoFamiliar());
                dialogos.mostrarMensagem("\n[FIM DA ROTA B (MORTE): Desastre total. Devia ter preparado uma rota de fuga segura...]");
            }

        } else if (escolhaRotaFinal == 2) {
            // --- ROTA C: A VINGANÇA EXPLOSIVA OU SILENCIOSA ---
            if (personagemService.verificarItem("Ricina")) {
                personagemService.desbloquearConquista("ROTA_C_RICINA"); // CONQUISTA
                dialogos.mostrarMensagem("\n========================================================");
                dialogos.mostrarMensagem("CAPÍTULO 10: O Toque Silencioso (ROTA C - FINAL SECRETO)");
                dialogos.mostrarMensagem("========================================================");
                dialogos.mostrarMensagem("Em vez de explodir tudo e morrer no processo, você envenenou o ar do galpão com Ricina.");
                dialogos.mostrarMensagem("Você caminhou calmamente por entre os corpos intactos dos capangas e do Fidalgo.");
                dialogos.mostrarMensagem("Recuperou o seu dinheiro e fugiu na calada da noite. Frio e calculista.");
                dialogos.mostrarMensagem("========================================================\n");
                dialogos.mostrarMensagem("\n[FIM DA ROTA C (SUCESSO): Vitória limpa. Heisenberg puro.]");
            } else {
                personagemService.desbloquearConquista("ROTA_C_KAMIKAZE"); // CONQUISTA
                dialogos.mostrarCenaAtaque();
                dialogos.mostrarMensagem("\n[FIM DA ROTA C (KAMIKAZE): O último ato de química e redenção. O Cartel foi aniquilado com explosivos.]");
            }

        } else if (escolhaRotaFinal == 3) {
            // --- NOVA ROTA D: A DELAÇÃO PREMIADA ---
            if (personagemService.verificarItem("Dossiê do Cartel")) {
                personagemService.desbloquearConquista("ROTA_D_DELACAO"); // CONQUISTA
                dialogos.mostrarCenaDelacao();
                personagemService.aplicarConsequencia(-valter.getDinheiro(), 0, +50, +20); // Perde o dinheiro, ganha moral e relação
                dialogos.mostrarMensagem("\n[FIM DA ROTA D (RENDIÇÃO): Você sacrificou o império para proteger a família.]");
            }
        }

        dialogos.pausarParaLeitura();
    }
}