/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class Bitcoin extends Moedas implements Tarifas {
   
    public Bitcoin(double saldoBitcoin) {
        super(saldoBitcoin);
    }

    @Override
    public double calcularTaxaCompra(double valor) {
        // Lógica para calcular a taxa de compra de Bitcoin
        return 0;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        // Lógica para calcular a taxa de venda de Bitcoin
        return 0;
    }

}
