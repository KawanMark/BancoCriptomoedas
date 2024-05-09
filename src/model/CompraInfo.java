/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class CompraInfo {
     private String mensagemCompra;
    private double valor;
    private String moedaSelecionada;

    public CompraInfo(String mensagemCompra, double valor, String moedaSelecionada) {
        this.mensagemCompra = mensagemCompra;
        this.valor = valor;
        this.moedaSelecionada = moedaSelecionada;
    }

    public String getMensagemCompra() {
        return mensagemCompra;
    }

    public double getValor() {
        return valor;
    }

    public String getMoedaSelecionada() {
        return moedaSelecionada;
    }
}

