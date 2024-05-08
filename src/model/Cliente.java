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
    private Carteira carteira;
    private String nome;
    private long cpf, senha;
    
    public Cliente(Long cpf) {
        this.cpf = cpf;
        carteira = new Carteira();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getSenha() {
        return senha;
    }

    public void setSenha(long senha) {
        this.senha = senha;
    }

    public Cliente(String nome, long cpf, long senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }
    
    public Cliente(){
 
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + '}';
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
