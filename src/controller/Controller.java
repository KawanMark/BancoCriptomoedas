/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.ClienteDAO;
import DAO.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import view.JanelaLogin;
import view.JanelaMenu;

/**
 *
 * @author kawan
 */
public class Controller {
    private JanelaLogin login;
    private JanelaMenu menu;
    private ClienteDAO clienteDAO;

    public Controller(JanelaLogin login) throws SQLException {
        this.login = login;
        this.clienteDAO = new ClienteDAO(Conexao.getConnection());
    }
    
     public Controller(JanelaMenu menu) throws SQLException {
        this.menu = menu;
        this.clienteDAO = new ClienteDAO(Conexao.getConnection());
    }

    public void loginCliente() {
        String cpf = login.getTxtCPF().getText();
        String senha = login.getTxtSenha().getText();
        
        try {
            
            ResultSet res = clienteDAO.consultarPorCPFESenha(cpf, senha);
            if (res.next()) {
                JOptionPane.showMessageDialog(login, "Login realizado com sucesso!");
                abrirJanelaMenu();
            } else {
                JOptionPane.showMessageDialog(login, "CPF ou senha incorretos!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(login, "Erro ao fazer login: " + e.getMessage());
        }
    }

    public void entrar() throws SQLException {
        try {
            String cpf = login.getTxtCPF().getText();
            String senha = login.getTxtSenha().getText();
            long cpfLong = Long.parseLong(cpf);
            long senhaLong = Long.parseLong(senha);
            if (entrar(cpfLong, senhaLong)) {
                abrirJanelaMenu();
            } else {
                JOptionPane.showMessageDialog(login, "CPF ou senha incorretos!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(login, "Use apenas números para o CPF e senha!");
        }
    }

    private boolean entrar(long cpf, long senha) {
        
        return false;
    }

    public void abrirJanelaMenu() throws SQLException {
        if (menu == null) {
            menu = new JanelaMenu();
        }
        menu.setVisible(true);
        login.setVisible(false);
    }

    public void fecharJanela() {
        if (menu != null) {
            menu.dispose();
        }
        if (login != null) {
            login.dispose();
        }
    }
    
    
}
    

