package br.com.sbornia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;

@Entity
public class ItemVenda {
    @Id
    @Column(name = "id")
    private String id; // Pode ser gerado automaticamente ou composto

    @ManyToOne
    @JoinColumn(name = "produto_codigo")
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidade;

    // Construtores
    public ItemVenda() {}

    public ItemVenda(String id, Produto produto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}