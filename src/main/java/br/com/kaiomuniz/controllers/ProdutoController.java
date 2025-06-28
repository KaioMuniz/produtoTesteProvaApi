package br.com.kaiomuniz.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kaiomuniz.entities.Produto;
import br.com.kaiomuniz.repositories.ProdutoRepository;
import br.com.kaiomuniz.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable UUID id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        Produto novo = produtoService.create(produto);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable UUID id, @RequestBody Produto produto) {
        return produtoService.update(id, produto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (produtoService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/resumo-por-tipo")
    public ResponseEntity<List<ProdutoRepository.TipoQuantidadePrecoMedia>> resumoPorTipo() {
        var resultado = produtoService.getQuantidadePrecoMedioPorTipo();
        return ResponseEntity.ok(resultado);
    }

    
}
