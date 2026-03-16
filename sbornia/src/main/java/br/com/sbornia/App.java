package br.com.sbornia;

import br.com.sbornia.config.DataInitializer;
import br.com.sbornia.model.ItemVenda;
import br.com.sbornia.model.Produto;
import br.com.sbornia.model.Usuario;
import br.com.sbornia.model.Venda;
import br.com.sbornia.repository.ItemVendaRepositoryImpl;
import br.com.sbornia.repository.ProdutoRepositoryImpl;
import br.com.sbornia.repository.UsuarioRepositoryImpl;
import br.com.sbornia.repository.VendaRepositoryImpl;
import br.com.sbornia.service.ImpostoService;
import br.com.sbornia.service.VendaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Inicializar dados
        DataInitializer initializer = new DataInitializer();
        initializer.initializeData();

        // Repositórios e serviços
        ProdutoRepositoryImpl produtoRepo = new ProdutoRepositoryImpl();
        UsuarioRepositoryImpl usuarioRepo = new UsuarioRepositoryImpl();
        VendaRepositoryImpl vendaRepo = new VendaRepositoryImpl();
        ItemVendaRepositoryImpl itemVendaRepo = new ItemVendaRepositoryImpl();
        ImpostoService impostoService = new ImpostoService();
        VendaService vendaService = new VendaService(vendaRepo, usuarioRepo, produtoRepo, itemVendaRepo, impostoService);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema de Vendas Sbørnia ===");
        System.out.println("Dados iniciais carregados. Produtos e usuários disponíveis.");

        // Selecionar usuário
        System.out.print("Digite o ID do usuário: ");
        String usuarioId = scanner.nextLine();
        Usuario usuario = usuarioRepo.findById(usuarioId);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        System.out.println("Usuário selecionado: " + usuario.getNome());

        // Adicionar itens
        List<ItemVenda> itens = new ArrayList<>();
        while (true) {
            System.out.print("Digite o código do produto (ou 'fim' para finalizar): ");
            String codigo = scanner.nextLine();
            if ("fim".equalsIgnoreCase(codigo)) break;

            Produto produto = produtoRepo.findByCodigo(codigo);
            if (produto == null) {
                System.out.println("Produto não encontrado!");
                continue;
            }

            System.out.print("Digite a quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            if (quantidade > produto.getQuantidadeEstoque()) {
                System.out.println("Estoque insuficiente!");
                continue;
            }

            ItemVenda item = new ItemVenda(java.util.UUID.randomUUID().toString(), produto, quantidade);
            itens.add(item);
            System.out.println("Item adicionado: " + produto.getDescricao() + " x" + quantidade);
        }

        if (itens.isEmpty()) {
            System.out.println("Nenhum item adicionado. Venda cancelada.");
            return;
        }

        // Realizar venda
        Venda venda = vendaService.realizarVenda(usuarioId, itens);

        // Imprimir recibo (simulando impressão em papel)
        System.out.println("\n=== RECIBO DE VENDA ===");
        System.out.println("Usuário: " + venda.getUsuario().getNome());
        System.out.println("Itens:");
        for (ItemVenda item : venda.getItens()) {
            Produto p = item.getProduto();
            double valorBase = p.getPrecoUnitario() * item.getQuantidade();
            double imposto = impostoService.calcularImposto(p, usuario, item.getQuantidade());
            System.out.printf("- %s x%d: R$ %.2f (Imposto: R$ %.2f)\n", p.getDescricao(), item.getQuantidade(), valorBase, imposto);
        }
        System.out.printf("Valor Total: R$ %.2f\n", venda.getValorTotal());
        System.out.println("=== FIM DO RECIBO ===");

        scanner.close();
    }
}
