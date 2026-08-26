import java.util.Scanner;

public class Menu {
    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Jogar");
            System.out.println("2 - Instruções");
            System.out.println("3 - Creditos");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");


            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        Play.start();
                        break;

                    case 2:
                        instructions.HowToPlay();
                        break;

                    case 3:
                        Credits.references();
                        break;

                    case 4:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida! Escolha um número entre 1 e 4.");
                        break;
                }
            } else {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.next();
            }

        } while (opcao != 4);

        scanner.close();
    }
}




