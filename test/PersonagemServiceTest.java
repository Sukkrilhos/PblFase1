import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonagemServiceTest {

    private PersonagemRepository repository;
    private PersonagemService service;

    // Configura um ambiente limpo antes de cada teste
    @BeforeEach
    public void setUp() {
        repository = new PersonagemRepository();
        service = new PersonagemService(repository);
    }

    @Test
    public void deveCriarESalvarPersonagemComSucesso() {
        // Execução
        Personagem p = service.criarPersonagem("Arthur", 35);

        // Verificações
        assertNotNull(p);
        assertEquals("Arthur", p.getNome());
        assertEquals(35, p.getIdade());
        assertEquals(p, repository.buscar(), "O personagem deve estar salvo no repository.");
    }

    @Test
    public void deveAdicionarPontoCalculistaQuandoEscolhaForUm() {
        // Cenário
        service.criarPersonagem("Arthur", 35);

        // Execução
        service.registrarEscolha(1);

        // Verificações
        Personagem p = service.getPersonagem();
        assertEquals(1, p.getPontosCalculista());
        assertEquals(0, p.getPontosExausto());
        assertEquals(0, p.getPontosDefensivo());
    }

    @Test
    public void deveIgnorarPontuacaoQuandoEscolhaForInvalida() {
        // Cenário
        service.criarPersonagem("Arthur", 35);

        // Execução (escolha 4 ou 0 não devem pontuar)
        service.registrarEscolha(4);
        service.registrarEscolha(0);

        // Verificações
        Personagem p = service.getPersonagem();
        assertEquals(0, p.getPontosCalculista());
        assertEquals(0, p.getPontosExausto());
        assertEquals(0, p.getPontosDefensivo());
    }
}