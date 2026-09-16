import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonagemServiceTest {

    private PersonagemService personagemService;
    private PersonagemRepository personagemRepository;

    @BeforeEach
    public void setUp() {
        // Inicializa as dependências antes de cada teste para garantir um estado limpo
        personagemRepository = new PersonagemRepository();
        personagemService = new PersonagemService(personagemRepository);
        personagemService.inicializarValter();
    }

    @Test
    public void testInicializacaoDoPersonagem() {
        Personagem p = personagemService.getPersonagem();

        // Verifica se o personagem foi criado corretamente
        assertNotNull(p, "O personagem não deveria ser nulo após a inicialização");
        assertEquals("Valter", p.getNome(), "O nome inicial padrão deve ser Valter");

        // Verifica se os atributos vitais começam cheios (supondo base 100 para Saúde e Relação)
        assertTrue(p.getSaude() > 0, "A saúde inicial deve ser maior que 0");
        assertTrue(p.getRelacaoFamiliar() > 0, "A relação familiar inicial deve ser maior que 0");
    }

    @Test
    public void testAplicarConsequenciaCenarioPositivo() {
        Personagem p = personagemService.getPersonagem();
        int dinheiroInicial = p.getDinheiro();
        int moralInicial = p.getMoralEtica();

        // Simula uma escolha que dá lucro, mas custa moral (Ex: Aceitar a proposta de Kadu)
        personagemService.aplicarConsequencia(5000, 0, -30, 0);

        assertEquals(dinheiroInicial + 5000, p.getDinheiro(), "O dinheiro deveria aumentar em 5000");
        assertEquals(moralInicial - 30, p.getMoralEtica(), "A moral deveria cair 30 pontos");
    }

    @Test
    public void testGameOverPorPerdaDeSaude() {
        Personagem p = personagemService.getPersonagem();
        int saudeInicial = p.getSaude();

        // Zera a saúde do personagem aplicando um dano igual à saúde atual
        personagemService.aplicarConsequencia(0, -saudeInicial, 0, 0);

        assertTrue(personagemService.isGameOver(), "O jogo deve dar Game Over quando a saúde chegar a 0");
    }

    @Test
    public void testGameOverPorPerdaDeRelacaoFamiliar() {
        Personagem p = personagemService.getPersonagem();
        int relacaoInicial = p.getRelacaoFamiliar();

        // Zera a relação familiar do personagem
        personagemService.aplicarConsequencia(0, 0, 0, -relacaoInicial);

        assertTrue(personagemService.isGameOver(), "O jogo deve dar Game Over quando a relação familiar chegar a 0");
    }

    @Test
    public void testJogoContinuaSeAtributosForemPositivos() {
        // Aplica consequências leves que não zeram os atributos
        personagemService.aplicarConsequencia(1000, -10, -10, -10);

        assertFalse(personagemService.isGameOver(), "O jogo NÃO deve dar Game Over se a Saúde e a Relação Familiar forem maiores que 0");
    }
}