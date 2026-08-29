public class Play {
import java.util.Scanner;

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n--- TELA DE JOGO ---");
            System.out.println("1- Criar Personagem");
            System.out.println("2- Retornar o menu");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        // O "boolean" guarda a resposta da criação do personagem
                        boolean sucesso = Personagem.CriarPersonagem();

                        // Se a criação foi um sucesso (true), o jogo avança
                        if (sucesso) {
                            iniciarFase1(); // Chama a próxima etapa
                            opcao = 2; // Muda a opção para 2 para encerrar este menu de criação e não ficar repetindo
                        }
                        break;

                    case 2:
                        System.out.println("Retornando ao menu principal...");
                        break;

                    default:
                        System.out.println("Opção inválida! Escolha 1 ou 2.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.next();
            }

        } while (opcao != 2);
    }

    // --- NOVA ETAPA DO JOGO ---
    // Você pode criar classes novas para as fases depois, mas aqui é um exemplo de como avançar:
    public static void iniciarFase1() {
        System.out.println("\n*** FASE 1: O INÍCIO ***");
        System.out.println("Você abre os olhos e se vê em uma floresta escura.");
        System.out.println("Sua jornada acaba de começar!");
        // A partir daqui você pode colocar novos menus de batalha, exploração, etc.
    }
}