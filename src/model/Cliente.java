/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import view.JanelaMenu;

/**
 *
 * @author kawan
 */
public class Cliente {
     private String nome;
    private String cpf;
    private String senha;
    private Carteira carteira;
    
      /**
     * Construtor da classe Cliente.
     *
     * @param nome Nome do cliente.
     * @param cpf CPF do cliente.
     * @param senha Senha do cliente.
     * @param saldoReais Saldo em Reais do cliente.
     * @param saldoEthereum Saldo em Ethereum do cliente.
     * @param saldoRipple Saldo em Ripple do cliente.
     * @param saldoBitcoin Saldo em Bitcoin do cliente.
     */

    public Cliente(String nome, String cpf, String senha, double saldoReais, double saldoEthereum, double saldoRipple, double saldoBitcoin) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.carteira = new Carteira(saldoReais, saldoEthereum, saldoRipple, saldoBitcoin);
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
    
    public void showWindow(JanelaMenu j1){
        j1.setVisible(true);
    }
    public void unshowWindow(JanelaMenu j1){
        j1.setVisible(false);
    }
    
    //public void consultarSaldoCarteira() {
//        double saldoReais = carteira.consultarSaldoReais();
//        double saldoEthereum = carteira.consultarSaldoEthereum();
//        double saldoRipple = carteira.consultarSaldoRipple();
//        double saldoBitcoin = carteira.consultarSaldoBitcoin();
        
//        // Exemplo de exibição do saldo na console
//        System.out.println("Saldo em Reais: " + saldoReais);
//        System.out.println("Saldo em Ethereum: " + saldoEthereum);
//        System.out.println("Saldo em Ripple: " + saldoRipple);
//        System.out.println("Saldo em Bitcoin: " + saldoBitcoin);
//    }
    
    
    
}
