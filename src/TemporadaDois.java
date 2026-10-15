public class TemporadaDois {
    private Dialogos dialogos;
    private PersonagemService personagemService;

    public TemporadaDois(Dialogos dialogos, PersonagemService personagemService) {
        this.dialogos = dialogos;
        this.personagemService = personagemService;
    }

    public boolean executarTemporada(Personagem valter) {
        dialogos.mostrarMensagem("\n========================================================");
        dialogos.mostrarMensagem("       TEMPORADA 2: A AMEAÇA NO RETROVISOR");
        dialogos.mostrarMensagem("========================================================");

        // --- Cena 1: O Furgão Encurralado ---
        boolean temMascara = personagemService.verificarItem("Máscara de Gás");
        dialogos.mostrarCenaFurgaoEncurraladoParte1(valter.getNome(), temMascara);

        int maxOpcao = temMascara ? 3 : 2;
        int escolhaFurgao = dialogos.lerOpcao(1, maxOpcao);

        dialogos.mostrarCenaFurgaoEncurraladoParte2(escolhaFurgao);

        if (escolhaFurgao == 1) {
            personagemService.aplicarConsequencia(-2000, 0, -10, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro -2000 | Moral -10]");
        } else if (escolhaFurgao == 2) {
            personagemService.aplicarConsequencia(0, 0, -20, 0);
            dialogos.mostrarMensagem("\n[Efeito: Moral -20 | Fuga arriscada bem-sucedida]");
        } else if (escolhaFurgao == 3 && temMascara) {
            personagemService.aplicarConsequencia(1000, 0, -40, 0);
            dialogos.mostrarMensagem("\n[Efeito: Dinheiro +1000 (Saque) | Moral -40 | Fuga Impecável]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura();

        // --- Cena 2: O Sangue na Pia ---
        dialogos.mostrarCenaSanguePiaParte1(valter.getNome());
        int escolhaPia = dialogos.lerOpcao(1, 2);

        dialogos.mostrarCenaSanguePiaParte2(escolhaPia);

        if (escolhaPia == 1) {
            personagemService.aplicarConsequencia(0, +10, -5, +10);
            dialogos.mostrarMensagem("\n[Efeito: Saúde +10 | Relação Familiar +10]");
        } else {
            personagemService.aplicarConsequencia(0, -15, -10, -25);
            dialogos.mostrarMensagem("\n[Efeito: Saúde -15 | Relação Familiar -25]");
        }

        if (personagemService.isGameOver()) return true;
        dialogos.pausarParaLeitura();

        return false;
    }
}