/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kawan
 */
public class Investidor {
    private String nome;
    private String cpf;
    private Carteira carteira;

    public Investidor(String nome, String cpf, double saldoReais, double saldoEthereum, double saldoRipple, double saldoBitcoin) {
        this.nome = nome;
        this.cpf = cpf;
        carteira = new Carteira(saldoReais, saldoEthereum, saldoRipple, saldoBitcoin);
    }
    
}
