package br.com.sbornia.repository;

import br.com.sbornia.model.Usuario;
import java.util.List;

public interface UsuarioRepository {
    Usuario findById(String id);
    List<Usuario> findAll();
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(String id);
}