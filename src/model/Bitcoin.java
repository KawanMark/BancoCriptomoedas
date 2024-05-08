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
   
    private static final double taxa_compra = 0.02; 
    private static final double taxa_venda = 0.03; 

    @Override
    public double calcularTaxaCompra(double valor) {
        return valor * taxa_compra;
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return valor * taxa_venda;
    }

}
