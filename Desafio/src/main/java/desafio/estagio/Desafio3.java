package desafio.estagio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Desafio3 {
    
    private static final double MULTA_PERCENTUAL_DIA = 0.025; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("R$ #,##0.00");
        
        System.out.println("----------------------------------------");
        System.out.println("Calculadora de Juros e Multa por Atraso");
        System.out.println("Data de Cálculo (Hoje): " + LocalDate.now());
        System.out.println("----------------------------------------");

        double valorOriginal = obterValor(scanner);
        if (valorOriginal <= 0) {
            scanner.close();
            return;
        }
        
        LocalDate dataVencimento = obterDataVencimento(scanner);
        if (dataVencimento == null) {
            scanner.close();
            return;
        }
        
        calcularEExibir(valorOriginal, dataVencimento, df);
        
        scanner.close();
    }
    
    private static double obterValor(Scanner scanner) {
        System.out.print("Informe o Valor Original do Débito (ex: 100,00): R$ ");
        if (scanner.hasNextDouble()) {
            double valor = scanner.nextDouble();
            scanner.nextLine();
            return valor;
        } else {
            System.out.println("Entrada inválida para o valor. Encerrando.");
            scanner.nextLine();
            return 0.0;
        }
    }
    
    private static LocalDate obterDataVencimento(Scanner scanner) {
        LocalDate dataVencimento = null;
        while (dataVencimento == null) {
            System.out.print("Informe a Data de Vencimento (formato AAAA-MM-DD, ex: 2025-11-20): ");
            try {
                dataVencimento = LocalDate.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("ERRO: Formato de data inválido. Use AAAA-MM-DD.");
            }
        }
        return dataVencimento;
    }
    
    private static void calcularEExibir(double valorOriginal, LocalDate dataVencimento, DecimalFormat df) {
        
        LocalDate dataHoje = LocalDate.now();
        
        long diasAtraso = Math.max(0, ChronoUnit.DAYS.between(dataVencimento, dataHoje));

        System.out.println("--- Resumo ---");
        System.out.println("Valor Original: " + df.format(valorOriginal));
        System.out.println("Data de Vencimento: " + dataVencimento);
        System.out.println("Dias de Atraso: " + diasAtraso + " dias");

        if (diasAtraso > 0) {
            double multaTotal = valorOriginal * MULTA_PERCENTUAL_DIA * diasAtraso;
            double valorAtualizado = valorOriginal + multaTotal;

            System.out.println("Taxa de Multa Diária: " + (MULTA_PERCENTUAL_DIA * 100) + "%");
            System.out.println("Valor da Multa Total: " + df.format(multaTotal));
            System.out.println("----------------------------------------");
            System.out.println("Valor Total Atualizado: " + df.format(valorAtualizado));
        } else {
            System.out.println("O débito não está em atraso. Valor a pagar: " + df.format(valorOriginal));
        }
    }
}