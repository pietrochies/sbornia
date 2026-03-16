package br.com.sbornia.service;

import br.com.sbornia.model.Produto;
import br.com.sbornia.model.Usuario;
import br.com.sbornia.model.Categoria;

public class ImpostoService {
    public double calcularImposto(Produto produto, Usuario usuario, int quantidade) {
        double valorBase = produto.getPrecoUnitario() * quantidade;
        double imposto = 0.0;
        switch (produto.getCategoria()) {
            case ALIMENTICIO:
                imposto = valorBase * 0.05;
                break;
            case AUTOMOTIVO:
                imposto = valorBase * 0.30;
                break;
            case BEBIDA_ALCOOLICA:
                imposto = valorBase * 1.00;
                break;
            case OUTRO:
                imposto = valorBase * 0.17;
                break;
        }
        // Regras para usuário
        boolean isMaiorDe60 = usuario.isMaiorDe60Anos();
        boolean maisDe3Dependentes = usuario.getNumeroDependentes() > 3;
        if (produto.getCategoria() != Categoria.BEBIDA_ALCOOLICA) {
            if (isMaiorDe60) {
                imposto = 0.0;
            } else if (maisDe3Dependentes) {
                imposto = imposto * 0.5;
            }
        }
        return imposto;
    }
}