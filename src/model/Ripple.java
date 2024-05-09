/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class Ripple extends Moedas implements Tarifas {
    public Ripple(double saldoRipple) {
        super(saldoRipple);
    }

    @Override
    public double calcularTaxaCompra(double valor) {

        return valor * 0.01; // 1% de taxa de compra
    }

    @Override
    public double calcularTaxaVenda(double valor) {

        return valor * 0.01; // 1% de taxa de venda
    }
}
