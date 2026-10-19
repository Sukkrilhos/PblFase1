import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonagemServiceTest {

    private PersonagemService personagemService;

    @BeforeEach
    public void setUp() {
        // Inicializa as dependências antes de cada teste para garantir um estado limpo
        GerenciadorDeSave gerenciadorDeSave = new GerenciadorDeSave();
        personagemService = new PersonagemService(gerenciadorDeSave);

        // Inicializa o personagem com o perfil padrão (Nome: Valter, Antecedente 1: Professor Tradicional)
        personagemService.inicializarPersonagemCustomizado("Valter", 1);
    }

    @Test
    public void testAplicarConsequenciaCorretamente() {
        // Simula uma escolha que dá dinheiro mas penaliza saúde e moral
        personagemService.aplicarConsequencia(5000, -20, -15, -5);

        Personagem valter = personagemService.getPersonagem();

        assertEquals(5000, valter.getDinheiro(), "O dinheiro deve ser 5000");
        assertEquals(80, valter.getSaude(), "A saúde deve cair para 80");
        assertEquals(85, valter.getMoralEtica(), "A moral deve cair para 85");
        assertEquals(95, valter.getRelacaoFamiliar(), "A relação familiar deve cair para 95");
    }

    @Test
    public void testGameOverPorSaudeZero() {
        // Remove 100 de saúde
        personagemService.aplicarConsequencia(0, -100, 0, 0);

        assertTrue(personagemService.isGameOver(), "O jogo deve identificar Game Over se a saúde zerar");
    }

    @Test
    public void testGameOverPorRelacaoFamiliarZero() {
        // Remove 100 de relação familiar
        personagemService.aplicarConsequencia(0, 0, 0, -100);

        assertTrue(personagemService.isGameOver(), "O jogo deve identificar Game Over se a relação familiar zerar");
    }

    @Test
    public void testContinuidadeComAtributosPositivos() {
        // Penalizações leves que não zeram os atributos vitais
        personagemService.aplicarConsequencia(1000, -50, -90, -50);

        assertFalse(personagemService.isGameOver(), "O jogo não deve acabar se saúde e relação forem maiores que zero");
    }

    @Test
    public void testAdicionarEVerificarItemInventario() {
        String item = "Colete Balístico";

        assertFalse(personagemService.verificarItem(item), "O personagem não deve ter o item inicialmente");

        personagemService.adicionarItemInventario(item);

        assertTrue(personagemService.verificarItem(item), "O inventário deve confirmar a posse do item após a adição");
    }

    @Test
    public void testDesbloquearEVerificarConquista() {
        String conquista = "ROTA_B_ADVOGADO";

        personagemService.desbloquearConquista(conquista);

        assertTrue(personagemService.possuiConquista(conquista), "O sistema deve validar a conquista recém-desbloqueada");
    }
    @Test
    public void testInicializacaoCustomizadaComAntecedenteIndustrial() {
        personagemService.inicializarPersonagemCustomizado("Heisenberg", 2);
        Personagem p = personagemService.getPersonagem();

        assertEquals("Heisenberg", p.getNome());
        assertEquals("Ex-Químico Industrial", p.getAntecedente());
        assertEquals(3000, p.getDinheiro());
        assertEquals(90, p.getMoralEtica());
    }

    @Test
    public void testInicializacaoCustomizadaComItemPrecavido() {
        personagemService.inicializarPersonagemCustomizado("Valter", 3);
        Personagem p = personagemService.getPersonagem();

        assertEquals("Químico Precavido", p.getAntecedente());
        assertTrue(p.possuiItem("Máscara de Gás"));
    }
}