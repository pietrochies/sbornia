package br.com.sbornia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import java.util.List;

@Entity
public class Venda {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itens;

    @Column(name = "valor_total")
    private double valorTotal;

    // Construtores
    public Venda() {}

    public Venda(String id, Usuario usuario, List<ItemVenda> itens) {
        this.id = id;
        this.usuario = usuario;
        this.itens = itens;
        this.valorTotal = calcularValorTotal();
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    // Método para calcular o valor total (será implementado na service)
    private double calcularValorTotal() {
        // Placeholder; lógica real na service
        return 0.0;
    }
}