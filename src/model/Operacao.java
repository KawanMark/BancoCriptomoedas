package model;

import java.time.LocalDateTime;

public class Operacao {
    private LocalDateTime dataHora;
    private double valor;
     private double cotacao;
    private String tipo;
    private double taxa;
    private double saldoReal;
    private double saldoBitcoin;
    private double saldoEthereum;
    private double saldoRipple;
    private double valorCripto;
    private double valorReal;

  public Operacao(LocalDateTime dataHora, double valor, String tipo, double taxa, double saldoReal, double saldoBitcoin, double saldoEthereum, double saldoRipple) {
    this.dataHora = dataHora;
    this.valor = valor;
    this.cotacao = cotacao;
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

    public double getValorCripto() {
        return valorCripto;
    }

    public double getValorReal() {
        return valorReal;
    }

    public double getCotacao() {
        return cotacao;
    }

    public void setCotacao(double cotacao) {
        this.cotacao = cotacao;
    }
    
    
}
