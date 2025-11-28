package desafio.estagio;

public class ProdutoEstoque {
    private final int codigo;
    private final String descricao;
    private int quantidadeEstoque;

    public ProdutoEstoque(int codigo, String descricao, int estoque) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidadeEstoque = estoque;
    }

    public void adicionarEstoque(int quantidade) {
        this.quantidadeEstoque += quantidade;
    }
    
    public void subtrairEstoque(int quantidade) {
        this.quantidadeEstoque -= quantidade;
    }

    public int getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }

    @Override
    public String toString() {
        return "ID: " + codigo + " - " + descricao + " (Estoque: " + quantidadeEstoque + ")";
    }
}