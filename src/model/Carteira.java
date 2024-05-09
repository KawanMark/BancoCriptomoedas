/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class Carteira  {
   private Moedas reais;
    private Moedas ethereum;
    private Moedas ripple;
    private Moedas bitcoin;

    public Carteira(double saldoReais, double saldoEthereum, double saldoRipple, double saldoBitcoin) {
        reais = new Reais(saldoReais);
        ethereum = new Ethereum(saldoEthereum);
        ripple = new Ripple(saldoRipple);
        bitcoin = new Bitcoin(saldoBitcoin);
    }
    



    public double getSaldoReais() {
        return reais.getSaldo();
    }

    public void setSaldoReais(double saldoReais) {
        reais.setSaldo(saldoReais);
    }

    public double getSaldoEthereum() {
        return ethereum.getSaldo();
    }

    public void setSaldoEthereum(double saldoEthereum) {
        ethereum.setSaldo(saldoEthereum);
    }

    public double getSaldoRipple() {
        return ripple.getSaldo();
    }

    public void setSaldoRipple(double saldoRipple) {
        ripple.setSaldo(saldoRipple);
    }

    public double getSaldoBitcoin() {
        return bitcoin.getSaldo();
    }

    public void setSaldoBitcoin(double saldoBitcoin) {
        bitcoin.setSaldo(saldoBitcoin);
    }
    
        public double getSaldo(String moeda) {
            switch (moeda.toLowerCase()) {
                case "reais":
                    return reais.getSaldo();
                case "ethereum":
                    return ethereum.getSaldo();
                case "ripple":
                    return ripple.getSaldo();
                case "bitcoin":
                    return bitcoin.getSaldo();
                default:
                    throw new IllegalArgumentException("Moeda não reconhecida: " + moeda);
            }
    }

    public void setSaldo(String moeda, double saldo) {
        switch (moeda.toLowerCase()) {
            case "reais":
                reais.setSaldo(saldo);
                break;
            case "ethereum":
                ethereum.setSaldo(saldo);
                break;
            case "ripple":
                ripple.setSaldo(saldo);
                break;
            case "bitcoin":
                bitcoin.setSaldo(saldo);
                break;
            default:
                throw new IllegalArgumentException("Moeda não reconhecida: " + moeda);
        }
    }
    
    public Bitcoin getBitcoin() {
        return (Bitcoin) bitcoin;
    }

    public Ethereum getEthereum() {
        return (Ethereum) ethereum;
    }

    public Ripple getRipple() {
        return (Ripple) ripple;
}
}

    

