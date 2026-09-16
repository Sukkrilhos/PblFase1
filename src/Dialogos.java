public class Dialogos {

    public void mostrarMenuPrincipal() {
        System.out.println("\n===== REAÇÃO EM CADEIA =====");
        System.out.println("1 - Novo Jogo");
        System.out.println("2 - Instruções");
        System.out.println("3 - Créditos");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void mostrarInstrucoes() {
        System.out.println("\n========================================================");
        System.out.println("                      INSTRUÇÕES");
        System.out.println("========================================================");
        System.out.println("Suas escolhas moldam quatro atributos essenciais:");
        System.out.println("- Dinheiro | Saúde | Moral/Ética | Relação Familiar");
        System.out.println("CUIDADO: Se a Saúde ou Relação Familiar chegarem a zero,");
        System.out.println("seu jogo terminará imediatamente (Game Over).");
        System.out.println("========================================================\n");
    }

    public void mostrarCreditos() {
        System.out.println("\n========================================================");
        System.out.println("                       CRÉDITOS");
        System.out.println("========================================================");
        System.out.println("Projeto PBL - EXA863");
        System.out.println("Arquitetura MVC baseada em Java");
        System.out.println("========================================================\n");
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    // =========================================================================
    // TEMPORADA 1: DIÁLOGOS E INTERAÇÕES
    // =========================================================================

    public void mostrarCenaJantarParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 1: O Jantar em Família ===");
        System.out.println("Marisa coloca os pratos na mesa, mas evita olhar nos seus olhos. O silêncio é pesado.");
        System.out.println("MARISA: — " + nome + "... a secretária do Dr. Nogueira ligou. Ela disse que você saiu do consultório apressado. O que aconteceu?");
        System.out.println("\nO que você responde?");
        System.out.println("1 - 'Não foi nada, Marisa. Só uma confusão com os horários.'");
        System.out.println("2 - 'Ele... ele pediu mais exames. Apenas isso.'");
        System.out.print("Sua resposta: ");
    }

    public void mostrarCenaJantarParte2(String nome, int respostaAnterior) {
        if (respostaAnterior == 1) {
            System.out.println("\nMARISA: (Suspirando irritada) — Você sempre diz que 'não é nada'. Acha que eu sou cega para não ver você tossindo a noite toda?");
        } else {
            System.out.println("\nMARISA: (Parando de servir a comida, assustada) — Mais exames? " + nome + ", você está me deixando em pânico. É algo grave?");
        }

        System.out.println("\nEla senta à mesa e segura sua mão. O diagnóstico de câncer de pulmão queima no seu bolso.");
        System.out.println("\nCOMO VOCÊ ENCERRA O ASSUNTO? (Escolha Crítica)");
        System.out.println("1 - Puxar a mão, inventar que está com problemas na escola e mudar de assunto bruscamente.");
        System.out.println("2 - Apertar a mão dela, segurar o choro e dizer que 'não foi um bom dia, mas vamos resolver juntos'.");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaAEscolha(String nome) {
        System.out.println("\n=== CAPÍTULO 1: A Escolha ===");
        System.out.println("Após acompanhar uma operação policial com seu cunhado Heitor, " + nome + " encurrala o ex-aluno Kadu.");
        System.out.println("Você tem a genialidade química. Ele tem as ruas. Você pode fabricar metanfetamina para salvar sua família.");
        System.out.println("1 - Recusar. 'Não. Isso não sou eu.'");
        System.out.println("2 - Aceitar. 'Tenho uma proposta melhor do que você imagina.'");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaFornada(String nome) {
        System.out.println("\n=== CAPÍTULO 2: A Primeira Fornada ===");
        System.out.println("A fumaça do laboratório improvisado no furgão está destruindo seus pulmões já debilitados.");
        System.out.println("1 - Pausar a produção para respirar (Ganha menos dinheiro, mas poupa saúde).");
        System.out.println("2 - Ignorar a dor e forçar a produção máxima (Ganha muito dinheiro, mas derruba a saúde e corrompe a moral).");
        System.out.print("Sua escolha: ");
    }

    // =========================================================================
    // TEMPORADA 2: DIÁLOGOS E INTERAÇÕES
    // =========================================================================

    public void mostrarCenaFurgaoEncurraladoParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 3: O Furgão Encurralado ===");
        System.out.println("Dois carros pretos fecham a saída do beco escuro.");
        System.out.println("O traficante 'Maluco' arranca Kadu de dentro do furgão e aponta uma arma para a cabeça dele.");
        System.out.println("MALUCO: — Quem é o químico que tá invadindo a minha praça? Fala ou o moleque morre aqui!");
        System.out.println("\nO que você faz?");
        System.out.println("1 - Descer com calma e negociar uma porcentagem dos lucros com ele.");
        System.out.println("2 - Jogar reagentes químicos na chapa para criar um gás de fuga e escapar atirando.");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaFurgaoEncurraladoParte2(int escolhaAnterior) {
        if (escolhaAnterior == 1) {
            System.out.println("\nMaluco aceitou a proposta, mas deixou claro que agora você trabalha sob o comando dele.");
        } else {
            System.out.println("\nA fumaça química sufocou os capangas. Vocês escaparam correndo no meio do caos!");
        }
    }

    public void mostrarCenaSanguePiaParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 4: O Sangue na Pia ===");
        System.out.println("Você chega em casa de madrugada e tem uma forte crise de tosse, cuspindo sangue na pia do banheiro.");
        System.out.println("A porta se abre num estrondo. Marisa vê o sangue e entra em pânico completo.");
        System.out.println("\nMARISA: — " + nome + "! Por que você está sangrando assim?!");
        System.out.println("\nO que você diz a ela?");
        System.out.println("1 - Revelar parcialmente o câncer, mentindo que conseguiu um empréstimo para o tratamento.");
        System.out.println("2 - Brigar com ela, dizer que é só uma pneumonia da escola e trancar a porta.");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaSanguePiaParte2(int escolhaAnterior) {
        if (escolhaAnterior == 1) {
            System.out.println("\nMarisa chora nos seus braços, mas alivia-se sabendo que a clínica está paga.");
        } else {
            System.out.println("\nVocê a expulsou do banheiro de forma ríspida. O clima em casa virou um campo minado.");
        }
    }

    // =========================================================================
    // TEMPORADA 3: DIÁLOGOS E INTERAÇÕES
    // =========================================================================

    public void mostrarCenaNovoEsconderijoParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 5: O Novo Esconderijo ===");
        System.out.println("O furgão não é mais seguro para cozinhar em grande escala.");
        System.out.println("Kadu encontrou o porão de uma lavanderia industrial abandonada, mas o proprietário corrupto quer uma fortuna adiantada.");
        System.out.println("\nVALTER: — Kadu, o preço desse aluguel é absurdo. De onde vamos tirar tanto dinheiro vivo agora?");
        System.out.println("KADU: — Professor, o senhor decide. Ou a gente paga, ou fecha o laboratório.");
        System.out.println("\nO que você decide fazer para conseguir o dinheiro do aluguel?");
        System.out.println("1 - Usar as reservas de dinheiro reservadas para as suas sessões de radioterapia particular.");
        System.out.println("2 - Recusar gastar o dinheiro da saúde e voltar a desviar reagentes químicos da escola.");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaNovoEsconderijoParte2(int escolhaAnterior) {
        if (escolhaAnterior == 1) {
            System.out.println("\nVocê tirou o dinheiro do tratamento médico. O porão é garantido, mas seu corpo começa a pagar o preço da negligência.");
        } else {
            System.out.println("\nVocê invadiu o almoxarifado do colégio de madrugada. Conseguiu os insumos, mas a sensação de culpa e o risco de ser pego aumentam.");
        }
    }

    public void mostrarCenaOAtacadistaParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 6: O Atacadista ===");
        System.out.println("A produção no porão está a todo vapor, chamando a atenção de peixes maiores.");
        System.out.println("Um distribuidor de alto escalão conhecido como 'O Fidalgo' convoca vocês para uma reunião e exige uma cota de 50 quilos por semana.");
        System.out.println("\nFIDGALDO: — Ou vocês entregam essa quantidade exata toda semana, ou eu encerro a parceria de um jeito definitivo.");
        System.out.println("\nComo você responde ao Fidalgo?");
        System.out.println("1 - Aceitar a cota monstruosa, sacrificando suas noites de sono e sua saúde na linha de produção.");
        System.out.println("2 - Bater o pé e impor um limite seguro de apenas 20 quilos, desafiando a autoridade dele.");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaOAtacadistaParte2(int escolhaAnterior) {
        if (escolhaAnterior == 1) {
            System.out.println("\nVocê se tornou um escravo da própria bancada. O lucro explodiu, mas sua saúde entrou em colapso total.");
        } else {
            System.out.println("\nVocê manteve o controle do ritmo de trabalho, mas plantou um inimigo perigoso que agora monitora cada passo seu.");
        }
    }
}