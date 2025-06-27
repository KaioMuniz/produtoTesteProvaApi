package br.com.kaiomuniz.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.kaiomuniz.entities.Produto;
import br.com.kaiomuniz.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // GET /api/produtos
    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    // GET /api/produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable UUID id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST /api/produtos
    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto) {
        Produto novo = produtoRepository.save(produto);
        return ResponseEntity.ok(novo);
    }

    // PUT /api/produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable UUID id, @RequestBody Produto produto) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id); // garantir que o ID seja mantido
        Produto atualizado = produtoRepository.save(produto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE /api/produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
