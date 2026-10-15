public class TemporadaQuatro {
    private Dialogos dialogos;
    private PersonagemService personagemService;

    // Construtor atualizado, sem o Scanner
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
        int escolhaFundo = dialogos.lerInteiroSeguro();

        dialogos.mostrarCenaFundoFalsoParte2(escolhaFundo);

        if (escolhaFundo == 1) {
            personagemService.aplicarConsequencia(0, 0, -20, -35);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Relação Familiar despencou -35]");
        } else {
            personagemService.aplicarConsequencia(0, 0, -10, -50);
            dialogos.mostrarMensagem("\n[Efeito: Relação Familiar -50]");
        }

        if (personagemService.isGameOver()) return 0;
        dialogos.pausarParaLeitura(); // <--- Pausa essencial

        // --- CAPÍTULO 8: A Encruzilhada / O Cerco ---
        dialogos.mostrarCenaEncruzilhadaParte1(valter.getNome());
        int escolhaEncruzilhada = dialogos.lerInteiroSeguro();

        dialogos.mostrarCenaEncruzilhadaParte2(escolhaEncruzilhada);

        if (escolhaEncruzilhada == 1) {
            dialogos.mostrarMensagem("\n[ROTA B ESCOLHIDA: Você tentará a fuga desesperada com a família.]");
        } else {
            dialogos.mostrarMensagem("\n[ROTA C ESCOLHIDA: Você escolheu o caminho da vingança e do confronto final.]");
        }

        dialogos.pausarParaLeitura(); // <--- Pausa final da Temporada 4
        return escolhaEncruzilhada; // Retorna 1 (Fuga) ou 2 (Ataque) para a Temporada 5
    }
}