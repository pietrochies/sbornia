package br.com.sbornia.repository;

import br.com.sbornia.model.Venda;
import java.util.List;

public interface VendaRepository {
    Venda findById(String id);
    List<Venda> findAll();
    void save(Venda venda);
    void update(Venda venda);
    void delete(String id);
}