package br.com.sbornia.service;

import br.com.sbornia.model.Venda;
import br.com.sbornia.model.Usuario;
import br.com.sbornia.model.ItemVenda;
import br.com.sbornia.model.Produto;
import br.com.sbornia.repository.VendaRepository;
import br.com.sbornia.repository.UsuarioRepository;
import br.com.sbornia.repository.ProdutoRepository;
import br.com.sbornia.repository.ItemVendaRepository;
import java.util.List;

public class VendaService {
    private final VendaRepository vendaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ImpostoService impostoService;

    public VendaService(VendaRepository vendaRepository, UsuarioRepository usuarioRepository,
                       ProdutoRepository produtoRepository, ItemVendaRepository itemVendaRepository,
                       ImpostoService impostoService) {
        this.vendaRepository = vendaRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.impostoService = impostoService;
    }

    public Venda realizarVenda(String usuarioId, List<ItemVenda> itens) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        double valorTotal = 0.0;
        for (ItemVenda item : itens) {
            Produto produto = produtoRepository.findByCodigo(item.getProduto().getCodigo());
            double valorBase = produto.getPrecoUnitario() * item.getQuantidade();
            double imposto = impostoService.calcularImposto(produto, usuario, item.getQuantidade());
            valorTotal += valorBase + imposto;
            // Atualiza estoque
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoRepository.update(produto);
        }
        Venda venda = new Venda(java.util.UUID.randomUUID().toString(), usuario, itens);
        venda.setValorTotal(valorTotal);
        vendaRepository.save(venda);
        return venda;
    }
}