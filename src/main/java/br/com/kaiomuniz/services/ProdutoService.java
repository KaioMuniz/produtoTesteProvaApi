package br.com.kaiomuniz.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kaiomuniz.entities.Produto;
import br.com.kaiomuniz.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(UUID id) {
        return produtoRepository.findById(id);
    }

    public Produto create(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> update(UUID id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            return Optional.empty();
        }
        produto.setId(id);
        return Optional.of(produtoRepository.save(produto));
    }

    public boolean delete(UUID id) {
        if (!produtoRepository.existsById(id)) {
            return false;
        }
        produtoRepository.deleteById(id);
        return true;
    }
    public List<ProdutoRepository.TipoQuantidadePrecoMedia> getQuantidadePrecoMedioPorTipo() {
        return produtoRepository.findQuantidadeAndPrecoMedioGroupByTipo();
    }

}
