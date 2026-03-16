package br.com.sbornia.repository;

import br.com.sbornia.model.Produto;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProdutoRepositoryImpl implements ProdutoRepository {
    private final EntityManager em;

    public ProdutoRepositoryImpl() {
        this.em = Persistence.createEntityManagerFactory("sborniaPU").createEntityManager();
    }

    @Override
    public Produto findByCodigo(String codigo) {
        return em.find(Produto.class, codigo);
    }

    @Override
    public List<Produto> findAll() {
        return em.createQuery("FROM Produto", Produto.class).getResultList();
    }

    @Override
    public void save(Produto produto) {
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
    }

    @Override
    public void update(Produto produto) {
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
    }

    @Override
    public void delete(String codigo) {
        em.getTransaction().begin();
        Produto produto = findByCodigo(codigo);
        if (produto != null) {
            em.remove(produto);
        }
        em.getTransaction().commit();
    }
}