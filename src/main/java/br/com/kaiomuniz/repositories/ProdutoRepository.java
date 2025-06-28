package br.com.kaiomuniz.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.kaiomuniz.entities.Produto;
import br.com.kaiomuniz.entities.Tipo;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {


    @Query("SELECT p.tipo as tipo, COUNT(p) as quantidade, AVG(p.precoUnitario) as precoMedio " +
           "FROM Produto p GROUP BY p.tipo")
    List<TipoQuantidadePrecoMedia> findQuantidadeAndPrecoMedioGroupByTipo();


    interface TipoQuantidadePrecoMedia {
        Tipo getTipo();
        Long getQuantidade();
        Double getPrecoMedio();
    }
}
