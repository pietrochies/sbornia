package br.com.sbornia.repository;

import br.com.sbornia.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final EntityManager em;

    public UsuarioRepositoryImpl() {
        this.em = Persistence.createEntityManagerFactory("sborniaPU").createEntityManager();
    }

    @Override
    public Usuario findById(String id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("FROM Usuario", Usuario.class).getResultList();
    }

    @Override
    public void save(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void update(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }

    @Override
    public void delete(String id) {
        em.getTransaction().begin();
        Usuario usuario = findById(id);
        if (usuario != null) {
            em.remove(usuario);
        }
        em.getTransaction().commit();
    }
}