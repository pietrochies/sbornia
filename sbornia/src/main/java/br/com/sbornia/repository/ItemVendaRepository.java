package br.com.sbornia.repository;

import br.com.sbornia.model.ItemVenda;
import java.util.List;

public interface ItemVendaRepository {
    ItemVenda findById(String id);
    List<ItemVenda> findAll();
    void save(ItemVenda itemVenda);
    void update(ItemVenda itemVenda);
    void delete(String id);
}