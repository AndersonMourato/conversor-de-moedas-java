package br.com.exchange.menu;

import br.com.exchange.service.ApiService;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final Scanner scan;
    private final ApiService apiService;
    private final String msgError;

    public Menu() {
        this.scan = new Scanner(System.in);
        this.apiService = new ApiService();
        this.msgError = "Erro:\nOpção invalida, tente novamente digitando um numero de 1 a 7.";
    }

    public void start() {
        int option;
        boolean loop = true;

        while (loop) {
            System.out.println("\n\n####################### CONVERSOR DE MOEDAS #######################");

            System.out.println("[1] - DOLAR --> PESO ARGENTINO");
            System.out.println("[2] - PESO ARGENTINO --> DOLAR");
            System.out.println("[3] - DOLAR --> REAL BRASILEIRO");
            System.out.println("[4] - REAL BRASILEIRO --> DOLAR");
            System.out.println("[5] - DOLAR --> PESO COLOMBIANO");
            System.out.println("[6] - PESO COLOMBIANO --> DOLAR");
            System.out.println("[7] - SAIR");

            System.out.println("//______________________________________________________________//");
            System.out.println("Selecione uma das opções acima: ");
            try {
                option = scan.nextInt();
                switch (option) {
                    case 1:
                        converter("USD", "ARS");
                        break;
                    case 2:
                        converter("ARS", "USD");
                        break;
                    case 3:
                        converter("USD", "BRL");
                        break;
                    case 4:
                        converter("BRL", "USD");
                        break;
                    case 5:
                        converter("USD", "COP");
                        break;
                    case 6:
                        converter("COP", "USD");
                        break;
                    case 7:
                        loop = false;
                        break;
                    default:
                        System.err.println(this.msgError);
                }
            } catch (InputMismatchException err) {
                scan.nextLine();
                System.err.println(this.msgError);
            }
        }
    }

    public void converter(String currentCoin, String toCoin) {
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("\n******************** " + currentCoin + " <-- PARA --> " + toCoin + " ********************");
        System.out.println("Informe o valor que deseja converter");

        try {
            Double valor = scan.nextDouble();
            Double taxa = this.apiService.getCoinTaxa(currentCoin, toCoin);
            Double result = valor * taxa;
            System.out.println("------> Resultado: $" + df.format(result) + " " + toCoin);
        } catch (InputMismatchException err) {
            scan.nextLine();
            System.err.println("*O valor informado não é válido.");
        }
    }

}
