package desafio.estagio;

import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

public class Desafio1 {

    private static final String DADOS_VENDAS = "{ \"vendas\": [ { \"vendedor\": \"João Silva\", \"valor\": 1200.50 }, { \"vendedor\": \"João Silva\", \"valor\": 950.75 }, { \"vendedor\": \"João Silva\", \"valor\": 1800.00 }, { \"vendedor\": \"João Silva\", \"valor\": 1400.30 }, { \"vendedor\": \"João Silva\", \"valor\": 1100.90 }, { \"vendedor\": \"João Silva\", \"valor\": 1550.00 }, { \"vendedor\": \"João Silva\", \"valor\": 1700.80 }, { \"vendedor\": \"João Silva\", \"valor\": 250.30 }, { \"vendedor\": \"João Silva\", \"valor\": 480.75 }, { \"vendedor\": \"João Silva\", \"valor\": 320.40 }, { \"vendedor\": \"Maria Souza\", \"valor\": 2100.40 }, { \"vendedor\": \"Maria Souza\", \"valor\": 1350.60 }, { \"vendedor\": \"Maria Souza\", \"valor\": 950.20 }, { \"vendedor\": \"Maria Souza\", \"valor\": 1600.75 }, { \"vendedor\": \"Maria Souza\", \"valor\": 1750.00 }, { \"vendedor\": \"Maria Souza\", \"valor\": 1450.90 }, { \"vendedor\": \"Maria Souza\", \"valor\": 400.50 }, { \"vendedor\": \"Maria Souza\", \"valor\": 180.20 }, { \"vendedor\": \"Maria Souza\", \"valor\": 90.75 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 800.50 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 1200.00 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 1950.30 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 1750.80 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 1300.60 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 300.40 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 500.00 }, { \"vendedor\": \"Carlos Oliveira\", \"valor\": 125.75 }, { \"vendedor\": \"Ana Lima\", \"valor\": 1000.00 }, { \"vendedor\": \"Ana Lima\", \"valor\": 1100.50 }, { \"vendedor\": \"Ana Lima\", \"valor\": 1250.75 }, { \"vendedor\": \"Ana Lima\", \"valor\": 1400.20 }, { \"vendedor\": \"Ana Lima\", \"valor\": 1550.90 }, { \"vendedor\": \"Ana Lima\", \"valor\": 1650.00 }, { \"vendedor\": \"Ana Lima\", \"valor\": 75.30 }, { \"vendedor\": \"Ana Lima\", \"valor\": 420.90 }, { \"vendedor\": \"Ana Lima\", \"valor\": 315.40 } ] }";

    public static void main(String[] args) {
        System.out.println("Iniciando Desafio 1: Cálculo de Comissão");

        Map<String, Double> comissoesPorVendedor = processarVendas(DADOS_VENDAS);
        
        exibirResultados(comissoesPorVendedor);
    }
    
    private static Map<String, Double> processarVendas(String dados) {
        Map<String, Double> comissoesPorVendedor = new HashMap<>();
        
        String vendasTexto = dados.replaceAll("\\s", "") 
                                  .replaceFirst("\\{\\\"vendas\\\":\\[", "")
                                  .replaceFirst("\\]\\}", "");
                                         
        String[] vendas = vendasTexto.split("\\},\\{");

        for (String vendaString : vendas) {
            try {
                String vendedor = vendaString.split("\\\"vendedor\\\":\\\"")[1].split("\\\"")[0];
                String valorTexto = vendaString.split("\\\"valor\\\":")[1].split("}")[0].replace(',', '.'); 
                double valor = Double.parseDouble(valorTexto);
                
                double comissao = calcularComissao(valor);
                
                comissoesPorVendedor.put(vendedor, 
                    comissoesPorVendedor.getOrDefault(vendedor, 0.0) + comissao);
                    
            } catch (Exception e) {
            }
        }
        return comissoesPorVendedor;
    }

    private static double calcularComissao(double valorVenda) {
        if (valorVenda < 100.00) {
            return 0.0; 
        } else if (valorVenda < 500.00) {
            return valorVenda * 0.01; 
        } else {
            return valorVenda * 0.05; 
        }
    }
    
    private static void exibirResultados(Map<String, Double> resultados) {
        System.out.println("----------------------------------------");
        System.out.println("Relatório de Comissão Total por Vendedor");
        System.out.println("----------------------------------------");
        
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        
        for (Map.Entry<String, Double> entry : resultados.entrySet()) {
            System.out.println("Vendedor: " + entry.getKey() + " | Comissão Total: " + df.format(entry.getValue()));
        }
        System.out.println("----------------------------------------");
    }
}