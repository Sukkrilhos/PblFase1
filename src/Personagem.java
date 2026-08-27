import java.util.Scanner;

public class Personagem {

    // --- CLASSE DOS COADJUVANTES (NPCs) ---
    // Criamos essa estrutura para definir o molde de como um coadjuvante deve ser
    public static class Coadjuvante {
        public String nome;
        public String personalidade;

        // O construtor para criar o coadjuvante rapidamente
        public Coadjuvante(String nome, String personalidade) {
            this.nome = nome;
            this.personalidade = personalidade;
        }

        // Um método simples de apresentação
        public void seApresentar() {
            System.out.println("- " + nome + " (" + personalidade + ") está aqui.");
        }
    }

    // --- MÉTODOS DO PERSONAGEM PRINCIPAL ---

    public static boolean CriarPersonagem() {
        Scanner scanner = new Scanner(System.in);
        String nome;
        int idade = 0;

        System.out.println("\n=== CRIADOR DE PERSONAGEM ===");

        System.out.print("Digite o nome do seu personagem: ");
        nome = scanner.nextLine();

        boolean idadeValida = false;
        do {
            System.out.print("Digite a idade do seu personagem: ");
            if (scanner.hasNextInt()) {
                idade = scanner.nextInt();
                idadeValida = true;
            } else {
                System.out.println("Idade inválida! Por favor, digite apenas números.");
                scanner.next();
            }
        } while (!idadeValida);

        System.out.println("\nPersonagem criado com sucesso!");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("=============================\n");

        // Retorna verdadeiro para avisar que a criação foi um sucesso!
        return true;
    }

    // --- GERENCIAMENTO DE COADJUVANTES ---

    // Você pode chamar este método lá na sua Fase 1 para carregar os aliados da história
    public static void inicializarCoadjuvantes() {
        // Aqui estão os espaços para você preencher os nomes depois!
        Coadjuvante aliado1 = new Coadjuvante("[NOME 1]", "Corajoso, leal e sempre age antes de pensar.");
        Coadjuvante aliado2 = new Coadjuvante("[NOME 2]", "Misterioso, calculista e fala muito pouco.");
        Coadjuvante guia = new Coadjuvante("[NOME 3]", "Sábio, paciente, mas com um senso de humor sarcástico.");
        Coadjuvante rival = new Coadjuvante("[NOME 4]", "Arrogante e competitivo, sempre tentando provar que é melhor.");

        System.out.println("\n(Sistema: Carregando coadjuvantes da história...)");
        aliado1.seApresentar();
        aliado2.seApresentar();
        guia.seApresentar();
        rival.seApresentar();
    }
}