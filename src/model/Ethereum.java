/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class Ethereum extends Moedas implements Tarifas{
  public Ethereum(double saldoEthereum) {
        super(saldoEthereum);
    }

    @Override
    public double calcularTaxaCompra(double valor) {
        // Lógica para calcular a taxa de compra de Ethereum
        return 0;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        // Lógica para calcular a taxa de venda de Ethereum
        return 0;
    }
}
