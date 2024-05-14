/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author kawan
 */
public class Operacao {
    private LocalDateTime dataHora;
    private double valor;
    private String tipo;
    private double taxa;
    private double saldoReal;
    private double saldoBitcoin;
    private double saldoEthereum;
    private double saldoRipple;

    public Operacao(LocalDateTime dataHora, double valor, String tipo, double taxa, double saldoReal, double saldoBitcoin, double saldoEthereum, double saldoRipple) {
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
        this.taxa = taxa;
        this.saldoReal = saldoReal;
        this.saldoBitcoin = saldoBitcoin;
        this.saldoEthereum = saldoEthereum;
        this.saldoRipple = saldoRipple;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public double getTaxa() {
        return taxa;
    }

    public double getSaldoReal() {
        return saldoReal;
    }

    public double getSaldoBitcoin() {
        return saldoBitcoin;
    }

    public double getSaldoEthereum() {
        return saldoEthereum;
    }

    public double getSaldoRipple() {
        return saldoRipple;
    }
}