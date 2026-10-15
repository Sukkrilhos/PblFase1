public class TemporadaTres {
    private Dialogos dialogos;
    private PersonagemService personagemService;

    public TemporadaTres(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
    }

    public boolean executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("      TEMPORADA 3: A EXPANSÃO DO IMPÉRIO");
        dialogos.mostrarMensagem("========================================================");

        // --- CAPÍTULO 5: O Novo Esconderijo ---
        dialogos.mostrarCenaNovoEsconderijoParte1(valter.getNome());
        int escolhaEsconderijo = dialogos.lerOpcao(1, 3);

        if (escolhaEsconderijo == 1) {
            personagemService.aplicarConsequencia(0, -25, -5, 0);
            dialogos.mostrarMensagem("\n[Efeito: Saúde -25 | Você usou o dinheiro da radioterapia para pagar o aluguel do porão.]");
        } else if (escolhaEsconderijo == 2) {
            personagemService.aplicarConsequencia(0, 0, -20, 0);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Você voltou a roubar reagentes químicos da escola na madruga.]");
        } else if (escolhaEsconderijo == 3) {
            personagemService.aplicarConsequencia(0, -15, -15, 0);
            personagemService.adicionarItemInventario("Ricina");
            dialogos.mostrarMensagem("\n[Efeito: Saúde -15 | Moral -15 | ITEM ADQUIRIDO: Ricina]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura();

        // --- CAPÍTULO 6: O Atacadista ---
        dialogos.mostrarCenaOAtacadistaParte1(valter.getNome());
        int escolhaAtacadista = dialogos.lerOpcao(1, 3);

        if (escolhaAtacadista == 1) {
            personagemService.aplicarConsequencia(10000, -30, -15, -15);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +10000 | Saúde -30 | Moral -15 | Relação Familiar -15]");
        } else if (escolhaAtacadista == 2) {
            personagemService.aplicarConsequencia(3000, 0, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +3000 | Você irritou um distribuidor perigoso de alto escalão.]");
        } else if (escolhaAtacadista == 3) {
            personagemService.aplicarConsequencia(-8000, 0, 0, 0);
            personagemService.adicionarItemInventario("Passaportes Falsos");
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -8000 | ITEM ADQUIRIDO: Passaportes Falsos]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura();

        return false;
    }
}