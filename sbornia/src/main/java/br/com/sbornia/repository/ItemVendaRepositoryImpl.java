package br.com.sbornia.repository;

import br.com.sbornia.model.ItemVenda;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ItemVendaRepositoryImpl implements ItemVendaRepository {
    private final EntityManager em;

    public ItemVendaRepositoryImpl() {
        this.em = Persistence.createEntityManagerFactory("sborniaPU").createEntityManager();
    }

    @Override
    public ItemVenda findById(String id) {
        return em.find(ItemVenda.class, id);
    }

    @Override
    public List<ItemVenda> findAll() {
        return em.createQuery("FROM ItemVenda", ItemVenda.class).getResultList();
    }

    @Override
    public void save(ItemVenda itemVenda) {
        em.getTransaction().begin();
        em.persist(itemVenda);
        em.getTransaction().commit();
    }

    @Override
    public void update(ItemVenda itemVenda) {
        em.getTransaction().begin();
        em.merge(itemVenda);
        em.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        em.getTransaction().begin();
        ItemVenda itemVenda = findById(id);
        if (itemVenda != null) {
            em.remove(itemVenda);
        }
        em.getTransaction().commit();
    }
}