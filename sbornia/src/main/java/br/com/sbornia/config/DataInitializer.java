package br.com.sbornia.config;

import br.com.sbornia.model.Categoria;
import br.com.sbornia.model.Produto;
import br.com.sbornia.model.Usuario;
import br.com.sbornia.repository.ProdutoRepository;
import br.com.sbornia.repository.ProdutoRepositoryImpl;
import br.com.sbornia.repository.UsuarioRepository;
import br.com.sbornia.repository.UsuarioRepositoryImpl;
import java.time.LocalDate;

public class DataInitializer {

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public DataInitializer() {
        this.produtoRepository = new ProdutoRepositoryImpl();
        this.usuarioRepository = new UsuarioRepositoryImpl();
    }

    public void initializeData() {
        // Produtos de exemplo
        Produto produto1 = new Produto("P001", "Arroz", 100, 5.0, Categoria.ALIMENTICIO);
        Produto produto2 = new Produto("P002", "Óleo de Motor", 50, 20.0, Categoria.AUTOMOTIVO);
        Produto produto3 = new Produto("P003", "Cerveja", 200, 3.0, Categoria.BEBIDA_ALCOOLICA);
        Produto produto4 = new Produto("P004", "Camiseta", 150, 15.0, Categoria.OUTRO);

        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);
        produtoRepository.save(produto4);

        // Usuários de exemplo
        Usuario usuario1 = new Usuario("U001", "João Silva", LocalDate.of(1990, 1, 1), 2); // Jovem, 2 dependentes
        Usuario usuario2 = new Usuario("U002", "Maria Santos", LocalDate.of(1960, 5, 10), 0); // Idosa, sem dependentes
        Usuario usuario3 = new Usuario("U003", "Carlos Oliveira", LocalDate.of(1985, 3, 15), 4); // Adulto, 4 dependentes

        usuarioRepository.save(usuario1);
        usuarioRepository.save(usuario2);
        usuarioRepository.save(usuario3);

        System.out.println("Dados iniciais inseridos com sucesso!");
    }
}