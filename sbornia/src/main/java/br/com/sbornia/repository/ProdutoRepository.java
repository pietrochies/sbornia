package br.com.sbornia.repository;

import br.com.sbornia.model.Produto;
import java.util.List;

public interface ProdutoRepository {
    Produto findByCodigo(String codigo);
    List<Produto> findAll();
    void save(Produto produto);
    void update(Produto produto);
    void delete(String codigo);
}