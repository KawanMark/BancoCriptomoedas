/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class Reais extends Moedas implements Tarifas {
    
    public Reais(double saldoReais) {
        super(saldoReais);
    }

    @Override
    public double calcularTaxaCompra(double valor) {
        return 0; // Sem taxa
    }

    @Override
    public double calcularTaxaVenda(double valor) {
        return 0; // Sem taxa
    }

}
