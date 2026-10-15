public class TemporadaTres {
    private Dialogos dialogos;
    private PersonagemService personagemService;

    // Construtor atualizado, sem o Scanner
    public TemporadaTres(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
    }

    public boolean executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("      TEMPORADA 3: A EXPANSÃO DO IMPÉRIO");
        dialogos.mostrarMensagem("========================================================");

        // --- CAPÍTULO 5: O Novo Esconderijo (Interação 1) ---
        dialogos.mostrarCenaNovoEsconderijoParte1(valter.getNome());
        int escolhaEsconderijo = dialogos.lerInteiroSeguro(); // CORREÇÃO: Lê apenas uma vez

        dialogos.mostrarCenaNovoEsconderijoParte2(escolhaEsconderijo);

        if (escolhaEsconderijo == 1) {
            personagemService.aplicarConsequencia(0, -25, -5, 0);
            dialogos.mostrarMensagem("\n[Efeito: Saúde -25 | Você usou o dinheiro da radioterapia para pagar o aluguel do porão.]");
        } else {
            personagemService.aplicarConsequencia(0, 0, -20, 0);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Você voltou a roubar reagentes químicos da escola na madruga.]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura(); // Usa o pausar da View

        // --- CAPÍTULO 6: O Atacadista (Interação 2) ---
        dialogos.mostrarCenaOAtacadistaParte1(valter.getNome());
        int escolhaAtacadista = dialogos.lerInteiroSeguro(); // CORREÇÃO: Lê apenas uma vez

        dialogos.mostrarCenaOAtacadistaParte2(escolhaAtacadista);

        if (escolhaAtacadista == 1) {
            personagemService.aplicarConsequencia(10000, -30, -15, -15);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +10000 | Saúde -30 | Moral -15 | Relação Familiar -15]");
        } else {
            personagemService.aplicarConsequencia(3000, 0, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +3000 | Você irritou um distribuidor perigoso de alto escalão.]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura();

        return false; // Fim da Temporada 3 com sucesso
    }
}