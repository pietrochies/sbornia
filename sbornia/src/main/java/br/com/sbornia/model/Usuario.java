package br.com.sbornia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.LocalDate;

@Entity
public class Usuario {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "numero_dependentes")
    private int numeroDependentes;

    // Construtores
    public Usuario() {}

    public Usuario(String id, String nome, LocalDate dataNascimento, int numeroDependentes) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.numeroDependentes = numeroDependentes;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public int getNumeroDependentes() { return numeroDependentes; }
    public void setNumeroDependentes(int numeroDependentes) { this.numeroDependentes = numeroDependentes; }

    // Método auxiliar para verificar se tem mais de 60 anos
    public boolean isMaiorDe60Anos() {
        return LocalDate.now().getYear() - dataNascimento.getYear() > 60;
    }
}