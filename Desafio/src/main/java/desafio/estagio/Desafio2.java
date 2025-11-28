package desafio.estagio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Desafio2 {
    
    private static final List<ProdutoEstoque> estoqueAtual = new ArrayList<>();
    private static int proximoIdMovimentacao = 1; 
    private static Scanner scanner = new Scanner(System.in);

    static {
        estoqueAtual.add(new ProdutoEstoque(101, "Caneta Azul", 150));
        estoqueAtual.add(new ProdutoEstoque(102, "Caderno Universitário", 75));
        estoqueAtual.add(new ProdutoEstoque(103, "Borracha Branca", 200));
        estoqueAtual.add(new ProdutoEstoque(104, "Lápis Preto HB", 320));
        estoqueAtual.add(new ProdutoEstoque(105, "Marcador de Texto Amarelo", 90));
    }

    public static void main(String[] args) {
        System.out.println("Sistema de Movimentação de Estoque");
        exibirMenu();
        scanner.close();
    }

    private static void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Listar Produtos");
            System.out.println("2. Lançar ENTRADA");
            System.out.println("3. Lançar SAÍDA");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); 
            } else {
                System.out.println("Entrada inválida.");
                scanner.nextLine(); 
                opcao = 0;
                continue;
            }

            switch (opcao) {
                case 1: listarProdutos(); break;
                case 2: lancarMovimentacao("ENTRADA"); break;
                case 3: lancarMovimentacao("SAÍDA"); break;
                case 4: System.out.println("Sistema encerrado."); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }
    
    private static void listarProdutos() {
        System.out.println("--- Produtos em Estoque ---");
        for (ProdutoEstoque p : estoqueAtual) {
            System.out.println(p);
        }
    }

    private static ProdutoEstoque buscarProduto(int codigo) {
        for (ProdutoEstoque p : estoqueAtual) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    private static void lancarMovimentacao(String tipo) {
        System.out.print("Informe o Código do Produto: ");
        int codigo = scanner.nextInt();
        
        ProdutoEstoque produto = buscarProduto(codigo);
        if (produto == null) {
            System.out.println("ERRO: Produto com código " + codigo + " não encontrado.");
            return;
        }

        System.out.print("Informe a Quantidade para " + tipo + ": ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();
        
        if (quantidade <= 0) {
            System.out.println("ERRO: A quantidade deve ser um número positivo.");
            return;
        }

        if (tipo.equals("ENTRADA")) {
            produto.adicionarEstoque(quantidade);
        } else if (tipo.equals("SAÍDA")) {
            if (produto.getQuantidadeEstoque() < quantidade) {
                System.out.println("ERRO: Estoque insuficiente. Saldo atual: " + produto.getQuantidadeEstoque());
                return;
            }
            produto.subtrairEstoque(quantidade);
        }
        
        System.out.println("Movimentação Realizada com Sucesso!");
        System.out.println("ID da Movimentação: " + proximoIdMovimentacao++);
        System.out.println("Descrição da Movimentação: " + tipo + " de " + quantidade + " unidades de " + produto.getDescricao());
        System.out.println("Estoque Final do Produto: " + produto.getQuantidadeEstoque());
    }
}