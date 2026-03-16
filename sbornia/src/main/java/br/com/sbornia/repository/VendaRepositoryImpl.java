package br.com.sbornia.repository;

import br.com.sbornia.model.Venda;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class VendaRepositoryImpl implements VendaRepository {
    private final EntityManager em;

    public VendaRepositoryImpl() {
        this.em = Persistence.createEntityManagerFactory("sborniaPU").createEntityManager();
    }

    @Override
    public Venda findById(String id) {
        return em.find(Venda.class, id);
    }

    @Override
    public List<Venda> findAll() {
        return em.createQuery("FROM Venda", Venda.class).getResultList();
    }

    @Override
    public void save(Venda venda) {
        em.getTransaction().begin();
        em.persist(venda);
        em.getTransaction().commit();
    }

    @Override
    public void update(Venda venda) {
        em.getTransaction().begin();
        em.merge(venda);
        em.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        em.getTransaction().begin();
        Venda venda = findById(id);
        if (venda != null) {
            em.remove(venda);
        }
        em.getTransaction().commit();
    }
}