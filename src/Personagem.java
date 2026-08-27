import java.util.Scanner;

public class Personagem {

    public static void CriarPersonagem() {
        Scanner scanner = new Scanner(System.in);
        String nome;
        int idade = 0;

        System.out.println("\n=== CRIADOR DE PERSONAGEM ===");

        // Pega o nome do personagem
        System.out.print("Digite o nome do seu personagem: ");
        nome = scanner.nextLine();

        // Pega a idade com verificação de erro
        boolean idadeValida = false;
        do {
            System.out.print("Digite a idade do seu personagem: ");
            if (scanner.hasNextInt()) {
                idade = scanner.nextInt();
                idadeValida = true; // Sai do laço pois a idade é um número válido
            } else {
                System.out.println("Idade inválida! Por favor, digite apenas números.");
                scanner.next(); // Limpa a entrada errada do teclado
            }
        } while (!idadeValida);

        // Exibe o resultado final
        System.out.println("\nPersonagem criado com sucesso!");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("=============================\n");
    }
}