/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.Cliente;
import view.JanelaLogin;
import view.JanelaMenu;

/**
 *
 * @author kawan
 */
public class Controller {
    JanelaLogin login;
    JanelaMenu menu;
    
    Cliente c1 = new Cliente();
    
    public boolean entrar(long cpf, long senha){
        boolean saida = false;
        if(cpf == c1.getCpf() && senha == c1.getSenha()){
            saida = true;
        }
        return saida;
    }
    
    
    public Controller(JanelaLogin login) {
        this.login = login;
    }

    public Controller(JanelaMenu menu) {
        this.menu = menu;
    }
    
    public void entrar(){
        try{
            
            String cpf = login.getTxtCPF().getText();
            String senha = login.getTxtSenha().getText();
            long cpfLong = Long.parseLong(cpf);
            long senhaLong = Long.parseLong(senha);
            if(entrar(cpfLong, senhaLong)){
                abrirJanelaMenu();
                
                
            }
            else{
                login.getTxtCPF().setText("Campo incorreto.");
                login.getTxtSenha().setText("Campo incorreto.");
            }

        }
        catch(NumberFormatException e){
            login.getTxtCPF().setText("Use numeros");
            login.getTxtSenha().setText("Use numeros");
            
        }
       
    }
    
    public void abrirJanelaMenu(){
        Cliente User = new Cliente();
        if(menu == null){
            menu = new JanelaMenu();
        }
        User.showWindow(menu);
    }

    
    
}
    

