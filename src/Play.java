import java.util.Scanner;

public class Play {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n--- TELA DE JOGO ---");
            System.out.println("1- Criar Personagem");
            System.out.println("2- Retornar o menu");
            System.out.print("Escolha uma opção: ");

            // Verifica se a entrada é um número
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        // Aqui está a chamada para a sua classe Personagem!
                        Personagem.CriarPersonagem();
                        break;

                    case 2:
                        System.out.println("Retornando ao menu principal...");
                        // Ao escolher 2, o laço while vai encerrar e voltar para o Menu.
                        break;

                    default:
                        System.out.println("Opção inválida! Escolha 1 ou 2.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.next(); // Limpa o valor inválido do teclado
            }

        } while (opcao != 2);
    }
}