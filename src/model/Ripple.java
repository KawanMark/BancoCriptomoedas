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
     private static final double TAXA_COMPRA = 0.01; // 1%
    private static final double TAXA_VENDA = 0.01; // 1%

    @Override
    public double calcularTaxaCompra(double valor) {
        return valor * TAXA_COMPRA;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return valor * TAXA_VENDA;
    }
}
