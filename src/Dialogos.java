import java.util.List;
import java.util.Scanner;

public class Dialogos {
    private Scanner scanner;
    private GerenciadorDeAudio audio;

    // Construtor atualizado com a injeção do sistema de áudio
    public Dialogos(GerenciadorDeAudio audio) {
        this.scanner = new Scanner(System.in);
        this.audio = audio;
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n===== Ponto Zero =====");
        System.out.println("1. Novo Jogo");
        System.out.println("2. Carregar Jogo");
        System.out.println("3. Instruções");
        System.out.println("4. Créditos");
        System.out.println("5. Galeria de Conquistas");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }
    public void mostrarGaleriaConquistas(PersonagemService ps) {
        audio.tocarSom("sons/menu.wav");
        System.out.println("\n========================================================");
        System.out.println("                GALERIA DE CONQUISTAS");
        System.out.println("========================================================");

        imprimirConquista("ROTA_A", "O Homem Honesto (Desistiu do crime no início)", ps);
        imprimirConquista("ROTA_B_MORTE", "A Queda (Morreu durante a emboscada na fuga)", ps);
        imprimirConquista("ROTA_B_FUGA", "O Desaparecimento (Fugiu com identidades falsas)", ps);
        imprimirConquista("ROTA_B_PRISAO", "Atrás das Grades (Sobreviveu com colete, mas foi preso)", ps);
        imprimirConquista("ROTA_C_KAMIKAZE", "Reação em Cadeia (Explodiu o laboratório)", ps);
        imprimirConquista("ROTA_C_RICINA", "O Toque Silencioso (Envenenou todos com Ricina)", ps);
        imprimirConquista("ROTA_D_DELACAO", "O Acordo (Entregou o Cartel à polícia)", ps);

        System.out.println("========================================================\n");
        pausarParaLeitura();
    }

    private void imprimirConquista(String id, String descricao, PersonagemService ps) {
        if (ps.possuiConquista(id)) {
            System.out.println("[DESBLOQUEADO] \uD83C\uDFC6 " + descricao); // Mostra um troféu!
        } else {
            System.out.println("[BLOQUEADO]    ???");
        }
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
        System.out.println("Desenvolvido por:\n Matheus de Almeida Muniz Rodrigues\n Gustavo Silva Ribeiro");
        System.out.println("========================================================\n");
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void pausarParaLeitura() {
        System.out.println("(Pressione Enter para continuar...)");
        scanner.nextLine();
    }

    public int lerInteiroSeguro() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida! Digite o número correspondente.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();

        // Efeito sonoro de clique ao inserir um número válido
        audio.tocarSom("sons/menu.wav");

        return valor;
    }

    public int lerOpcao(int min, int max) {
        int valor;
        while (true) {
            valor = lerInteiroSeguro();
            if (valor >= min && valor <= max) {
                return valor;
            }
            System.out.print("Opção inválida! Escolha um número entre " + min + " e " + max + ": ");
        }
    }

    public String lerTexto() {
        String texto = scanner.nextLine().trim();
        while (texto.isEmpty()) {
            texto = scanner.nextLine().trim();
        }

        // Som de confirmação ao inserir texto
        audio.tocarSom("sons/menu.wav");

        return texto;
    }

    public String pedirNomeSave() {
        System.out.println("\n--- NOVO JOGO ---");
        System.out.print("Digite um nome para o seu save (ex: 'Playthrough 1', 'Rota Maligna'): ");
        return lerTexto();
    }

    public Progresso mostrarMenuDeSaves(List<Progresso> saves) {
        if (saves.isEmpty()) {
            System.out.println("\nNenhum jogo salvo encontrado!");
            return null;
        }

        System.out.println("\n=== JOGOS SALVOS ===");
        for (int i = 0; i < saves.size(); i++) {
            Progresso p = saves.get(i);
            System.out.println((i + 1) + ". " + p.getNomeSave() + " | Temp " + p.getTemporadaAtual() + " | Último save: " + p.getDataHora());
        }
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha o save que deseja carregar: ");

        int escolha;
        while (true) {
            escolha = lerInteiroSeguro();
            if (escolha >= 0 && escolha <= saves.size()) {
                break;
            }
            System.out.print("Opção inválida! Escolha um número da lista: ");
        }

        if (escolha == 0) return null;
        return saves.get(escolha - 1);
    }
    public int mostrarOpcoesDoSave(String nomeSave) {
        System.out.println("\n=== OPÇÕES: " + nomeSave + " ===");
        System.out.println("1 - Carregar Partida");
        System.out.println("2 - Apagar Save");
        System.out.println("0 - Voltar à Lista");
        System.out.print("Sua escolha: ");
        return lerOpcao(0, 2); // Aproveita o nosso validador para aceitar 0, 1 ou 2
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
        System.out.println("3 - Investir R$ 1000 numa Máscara de Gás profissional (Perde dinheiro, ganha item 'Máscara de Gás' e poupa saúde).");
        System.out.print("Sua escolha: ");
    }

    public void mostrarDesfechoRotaA() {
        System.out.println("\n========================================================");
        System.out.println("CAPÍTULO FINAL: O Homem Honesto (ROTA A)");
        System.out.println("========================================================");
        System.out.println("Você olhou nos olhos de Kadu e balançou a cabeça. O crime não era o seu caminho.");
        System.out.println("Valter Branco continuou dando aulas de química e fazendo bicos para pagar as contas.");
        System.out.println("Infelizmente, o câncer avançou implacavelmente e as dívidas engoliram sua família.");
        System.out.println("Você morreu pobre, mas com a consciência limpa. Marisa e Enzo nunca souberam do que você quase se tornou.");
        System.out.println("========================================================\n");
    }

    // =========================================================================
    // TEMPORADA 2: DIÁLOGOS E INTERAÇÕES
    // =========================================================================

    public void mostrarCenaFurgaoEncurraladoParte1(String nome, boolean temMascara) {
        System.out.println("\n=== CAPÍTULO 3: O Furgão Encurralado ===");
        System.out.println("Dois carros pretos fecham a saída do beco escuro.");
        System.out.println("O traficante 'Maluco' arranca Kadu de dentro do furgão e aponta uma arma para a cabeça dele.");
        System.out.println("MALUCO: — Quem é o químico que tá invadindo a minha praça? Fala ou o moleque morre aqui!");
        System.out.println("\nO que você faz?");
        System.out.println("1 - Descer com calma e negociar uma porcentagem dos lucros com ele.");
        System.out.println("2 - Jogar reagentes químicos na chapa para criar um gás de fuga e escapar correndo.");
        if (temMascara) {
            System.out.println("3 - [OPÇÃO SECRETA: MÁSCARA DE GÁS] Trancar o furgão, inundá-lo com gás mostarda, colocar a máscara e assistir os inimigos sufocarem.");
        }
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaFurgaoEncurraladoParte2(int escolhaAnterior) {
        if (escolhaAnterior == 1) {
            System.out.println("\nMaluco aceitou a proposta, mas deixou claro que agora você trabalha sob o comando dele.");
        } else if (escolhaAnterior == 2) {
            System.out.println("\nA fumaça química sufocou os capangas temporariamente. Vocês escaparam correndo no meio do caos!");
        } else if (escolhaAnterior == 3) {
            System.out.println("\nCompletamente imune graças à máscara, você sai do furgão caminhando por cima dos corpos asfixiados. Você até saqueou os bolsos deles.");
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
        System.out.println("Kadu encontrou o porão de uma lavanderia abandonada, mas custa caro.");
        System.out.println("\nO que você decide fazer?");
        System.out.println("1 - Usar as reservas de dinheiro da radioterapia.");
        System.out.println("2 - Voltar a desviar reagentes químicos da escola.");
        System.out.println("3 - [CRIAR ITEM] Aproveitar os reagentes que restam para sintetizar 'Ricina', um veneno letal e indetectável (Custa Saúde, mas ganha uma arma secreta).");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaNovoEsconderijoParte2(int escolhaAnterior) {
    }

    public void mostrarCenaOAtacadistaParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 6: O Atacadista ===");
        System.out.println("Um distribuidor conhecido como 'O Fidalgo' exige uma cota de 50 quilos por semana.");
        System.out.println("\nComo você responde?");
        System.out.println("1 - Aceitar a cota monstruosa (Sacrifica saúde e relação familiar).");
        System.out.println("2 - Bater o pé e impor um limite seguro de 20 quilos.");
        System.out.println("3 - [COMPRAR ITEM] Subornar o braço direito dele por R$ 8000 para encomendar 'Passaportes Falsos' para a sua família.");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaOAtacadistaParte2(int escolhaAnterior) {
    }

    // =========================================================================
    // TEMPORADA 4: DIÁLOGOS E INTERAÇÕES
    // =========================================================================

    public void mostrarCenaFundoFalsoParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 7: O Fundo Falso ===");
        System.out.println("Enquanto arrumava o armário do quarto, Marisa encontrou uma tábua solta no piso.");
        System.out.println("Lá dentro, há uma mochila abarrotada de notas de dinheiro vivo e uma arma de fogo.");
        System.out.println("\nMARISA: — " + nome + "! O que é isso?! Dinheiro sujo? Uma arma?! Com quem você está se metendo?!");
        System.out.println("\nComo você lida com o flagrante?");
        System.out.println("1 - Tentar manipular a situação, dizendo que é dinheiro legal.");
        System.out.println("2 - Desabar parcialmente, confessando e exigindo silêncio absoluto.");
        System.out.println("3 - [COMPRAR ITEM] Ignorar as lágrimas, gastar R$ 5000 do fundo com um contato obscuro e encomendar um 'Colete Balístico' para a guerra inevitável.");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaFundoFalsoParte2(int escolhaAnterior) {
        if (escolhaAnterior == 1) {
            System.out.println("\nMarisa percebe que você está mentindo na cara dura. A confiança morre aqui.");
        } else if (escolhaAnterior == 2) {
            System.out.println("\nEla chora copiosamente, horrorizada com o monstro em que você se transformou.");
        } else if (escolhaAnterior == 3) {
            System.out.println("\nVocê veste o colete e tranca o dinheiro. Marisa arruma as malas e sai de casa aterrorizada.");
        }
    }

    public void mostrarCenaEncruzilhadaParte1(String nome) {
        System.out.println("\n=== CAPÍTULO 8: O Cerco ===");
        System.out.println("O cerco final se fechou. Heitor está na cola das evidências na Polícia Federal,");
        System.out.println("e os capangas de 'O Fidalgo' descobriram a localização do porão e querem eliminar você.");
        System.out.println("\nVALTER: — Acabou o tempo. Não dá mais para esconder de ninguém.");
        System.out.println("\nQual será o seu último passo antes do colapso?");
        System.out.println("1 - Tentar empacotar o que sobrou do dinheiro, comprar identidades falsas e fugir do país.");
        System.out.println("2 - Aceitar o fim, ficar para trás e armar um plano explosivo para destruir o cartel de vez.");
        System.out.println("3 - [CRIAR ITEM] Reunir provas bancárias e rotas num 'Dossiê do Cartel' para tentar um acordo com a Polícia (NOVA ROTA D).");
        System.out.print("Sua escolha: ");
    }

    public void mostrarCenaEncruzilhadaParte2(int escolhaAnterior) {
        if (escolhaAnterior == 1) {
            System.out.println("\nVocê corre contra o tempo para arrumar as malas. A rota da fuga está traçada.");
        } else if (escolhaAnterior == 2) {
            System.out.println("\nVocê acende um cigarro com as mãos firmes. Não há mais fuga. É hora da guerra.");
        } else if (escolhaAnterior == 3) {
            System.out.println("\nVocê passa a noite a imprimir documentos e liga para o telemóvel do seu cunhado Heitor.");
        }
    }

    // =========================================================================
    // TEMPORADA 5: DESFECHOS FINAIS
    // =========================================================================

    public void mostrarCenaFuga() {
        audio.tocarSom("sons/gameover.wav"); // Efeito sonoro de derrota
        System.out.println("\n========================================================");
        System.out.println("CAPÍTULO 10: Sangue no Tapete (ROTA B - A QUEDA)");
        System.out.println("========================================================");
        System.out.println("Você tentou fugir, mas foi interceptado. Os assassinos do Cartel invadiram a casa.");
        System.out.println("Tentando proteger o dinheiro, você levou um tiro fatal no abdômen.");
        System.out.println("Heitor arromba a porta com a polícia apenas para encontrar você sangrando no chão da sala.");
        System.out.println("Sua família sobreviveu, mas perdeu tudo: o dinheiro, a honra e você.");
        System.out.println("O crime não compensou.");
        System.out.println("========================================================\n");
    }

    public void mostrarCenaFugaSucesso() {
        audio.tocarSom("sons/sucesso.wav"); // Efeito sonoro de vitória
        System.out.println("\n========================================================");
        System.out.println("CAPÍTULO 10: O Desaparecimento (ROTA B - FINAL SECRETO)");
        System.out.println("========================================================");
        System.out.println("Quando os assassinos do Cartel invadiram a sua casa, você já não estava lá.");
        System.out.println("Graças aos Passaportes Falsos que comprou, Valter, Marisa e Enzo cruzaram a fronteira.");
        System.out.println("Heitor encontrou apenas uma casa vazia. O seu império ruiu, mas a sua família sobreviveu,");
        System.out.println("escondida numa praia isolada da América Central, a viver do dinheiro que restou.");
        System.out.println("Você perdeu o poder, mas salvou a sua vida.");
        System.out.println("========================================================\n");
    }

    public void mostrarCenaAtaque() {
        audio.tocarSom("sons/explosao.wav"); // Efeito sonoro de explosão
        System.out.println("\n========================================================");
        System.out.println("CAPÍTULO 10: Reação em Cadeia (ROTA C - A VINGANÇA)");
        System.out.println("========================================================");
        System.out.println("Você usou seu gênio químico uma última vez. Com um explosivo no porta-malas,");
        System.out.println("você detonou o galpão inteiro, aniquilando todos os seus inimigos de uma só vez.");
        System.out.println("Gravemente ferido por estilhaços, você caminha pelo laboratório destruído,");
        System.out.println("toca no maquinário perfeito, sorri e dá seu último suspiro antes da polícia chegar.");
        System.out.println("O dinheiro está seguro para Marisa e Enzo. Você venceu. Mas perdeu sua alma.");
        System.out.println("========================================================\n");
    }
    public void mostrarCenaPrisao() {
        audio.tocarSom("sons/gameover.wav");
        System.out.println("\n========================================================");
        System.out.println("CAPÍTULO 10: Atrás das Grades (ROTA B - FINAL ALTERNATIVO)");
        System.out.println("========================================================");
        System.out.println("Os assassinos do Cartel invadiram a sua casa. O tiro que deveria ser fatal no seu");
        System.out.println("abdômen foi parado pelo Colete Balístico que você comprou. Você sobreviveu à emboscada.");
        System.out.println("Minutos depois, Heitor arromba a porta. Você é algemado no chão da sua própria sala.");
        System.out.println("Sua família está segura, mas você passará o resto dos seus dias numa cela de segurança máxima.");
        System.out.println("Você está vivo. Mas perdeu a sua liberdade para sempre.");
        System.out.println("========================================================\n");
    }

    public void mostrarCenaDelacao() {
        audio.tocarSom("sons/sucesso.wav");
        System.out.println("\n========================================================");
        System.out.println("CAPÍTULO 10: O Acordo (ROTA D - A DELAÇÃO)");
        System.out.println("========================================================");
        System.out.println("Você encontrou-se com Heitor e entregou o Dossiê do Cartel. Na mesma noite, a");
        System.out.println("Polícia Federal desmantelou todo o império de 'O Fidalgo' através das suas provas.");
        System.out.println("Você não é mais o poderoso Valter. Você e a sua família entraram no Programa de");
        System.out.println("Proteção a Testemunhas. A viver no anonimato, sem dinheiro e a trabalhar como");
        System.out.println("caixa num supermercado. A honra e o poder desapareceram. Mas todos sobreviveram.");
        System.out.println("========================================================\n");
    }
    public boolean oferecerSaveManual() {
        System.out.println("\n--- PONTO DE DESCANSO ---");
        System.out.println("Deseja salvar o seu progresso atualizado agora?");
        System.out.println("1 - Sim, salvar jogo");
        System.out.println("2 - Não, continuar jornada");
        System.out.print("Sua escolha: ");

        int escolha = lerOpcao(1, 2);
        if (escolha == 1) {
            audio.tocarSom("sons/menu.wav"); // Feedback sonoro
            return true;
        }
        return false;
    }
}
