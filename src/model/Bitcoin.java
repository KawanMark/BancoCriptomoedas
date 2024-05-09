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

        return valor * 0.02; // 2% de taxa de compra
    }

    @Override
    public double calcularTaxaVenda(double valor) {

        return valor * 0.03; // 3% de taxa de venda
    }
}