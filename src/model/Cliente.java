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
    private long cpf, senha;

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
        this.nome = "Kawan";
        this.cpf = 541;
        this.senha = 819;
    }
    
    public void showWindow(JanelaMenu j1){
        j1.setVisible(true);
    }
    public void unshowWindow(JanelaMenu j1){
        j1.setVisible(false);
    }
    
}
