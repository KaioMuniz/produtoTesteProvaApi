package br.com.kaiomuniz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.javafaker.Faker;

import br.com.kaiomuniz.entities.Produto;
import br.com.kaiomuniz.entities.Tipo;
import br.com.kaiomuniz.repositories.ProdutoRepository;
import br.com.kaiomuniz.services.ProdutoService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ProdutoTesteProvaApiApplicationTests {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    private Produto produto;
    private Faker faker;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker(Locale.forLanguageTag("pt-BR")); // sem deprecated

        produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setNome(faker.commerce().productName());
        produto.setPrecoUnitario(faker.number().randomDouble(2, 10, 1000));
        produto.setTipo(faker.bool().bool() ? Tipo.MATERIAL : Tipo.SERVICO);
    }


    @Test
    @Order(1)
    void deveSalvarProduto() {
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto salvo = produtoService.create(produto);

        assertNotNull(salvo);
        assertEquals(produto.getNome(), salvo.getNome());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    @Order(2)
    void deveRetornarProdutoPorId() {
        UUID id = produto.getId();

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        Optional<Produto> encontrado = produtoService.findById(id);

        assertTrue(encontrado.isPresent());
        assertEquals(produto.getNome(), encontrado.get().getNome());
        verify(produtoRepository, times(1)).findById(id);
    }

    @Test
    @Order(3)
    void deveRetornarTodosProdutos() {
        List<Produto> lista = List.of(produto);
        when(produtoRepository.findAll()).thenReturn(lista);

        List<Produto> todos = produtoService.findAll();

        assertFalse(todos.isEmpty());
        assertEquals(1, todos.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    @Order(4)
    void deveAtualizarProdutoExistente() {
        UUID id = produto.getId();

        when(produtoRepository.existsById(id)).thenReturn(true);
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Optional<Produto> atualizado = produtoService.update(id, produto);

        assertTrue(atualizado.isPresent());
        assertEquals(produto.getNome(), atualizado.get().getNome());
        verify(produtoRepository, times(1)).existsById(id);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    @Order(5)
    void naoDeveAtualizarProdutoInexistente() {
        UUID id = UUID.randomUUID();

        when(produtoRepository.existsById(id)).thenReturn(false);

        Optional<Produto> atualizado = produtoService.update(id, produto);

        assertTrue(atualizado.isEmpty());
        verify(produtoRepository, times(1)).existsById(id);
        verify(produtoRepository, never()).save(any());
    }

    @Test
    @Order(6)
    void deveDeletarProdutoExistente() {
        UUID id = produto.getId();

        when(produtoRepository.existsById(id)).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(id);

        boolean deletado = produtoService.delete(id);

        assertTrue(deletado);
        verify(produtoRepository, times(1)).existsById(id);
        verify(produtoRepository, times(1)).deleteById(id);
    }

    @Test
    @Order(7)
    void naoDeveDeletarProdutoInexistente() {
        UUID id = UUID.randomUUID();

        when(produtoRepository.existsById(id)).thenReturn(false);

        boolean deletado = produtoService.delete(id);

        assertFalse(deletado);
        verify(produtoRepository, times(1)).existsById(id);
        verify(produtoRepository, never()).deleteById(any());
    }
    @Test
    @Order(8)
    void deveRetornarQuantidadeEPrecoMedioPorTipo() {
        // Criar mocks de retorno
        ProdutoRepository.TipoQuantidadePrecoMedia material = new ProdutoRepository.TipoQuantidadePrecoMedia() {
            @Override public Tipo getTipo() { return Tipo.MATERIAL; }
            @Override public Long getQuantidade() { return 5L; }
            @Override public Double getPrecoMedio() { return 20.0; }
        };
        ProdutoRepository.TipoQuantidadePrecoMedia servico = new ProdutoRepository.TipoQuantidadePrecoMedia() {
            @Override public Tipo getTipo() { return Tipo.SERVICO; }
            @Override public Long getQuantidade() { return 3L; }
            @Override public Double getPrecoMedio() { return 100.0; }
        };

        when(produtoRepository.findQuantidadeAndPrecoMedioGroupByTipo())
            .thenReturn(List.of(material, servico));

        var lista = produtoService.getQuantidadePrecoMedioPorTipo();

        assertEquals(2, lista.size());
        assertEquals(Tipo.MATERIAL, lista.get(0).getTipo());
        assertEquals(5L, lista.get(0).getQuantidade());
        assertEquals(20.0, lista.get(0).getPrecoMedio());

        assertEquals(Tipo.SERVICO, lista.get(1).getTipo());
        assertEquals(3L, lista.get(1).getQuantidade());
        assertEquals(100.0, lista.get(1).getPrecoMedio());

        verify(produtoRepository, times(1)).findQuantidadeAndPrecoMedioGroupByTipo();
    }

}
