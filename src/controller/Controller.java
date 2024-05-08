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
import view.JanelaDepositar;
import view.JanelaLogin;
import view.JanelaMenu;
import view.JanelaSacar;
import view.JanelaSaldo;

/**
 *
 * @author kawan
 */
public class Controller {
    private JanelaLogin login;
    private ClienteDAO clienteDAO;
    private JanelaMenu menu;
    private JanelaSaldo saldo;
    private JanelaSacar sacar;

    public Controller(JanelaLogin login) throws SQLException {
        this.login = login;
        this.clienteDAO = new ClienteDAO(Conexao.getConnection());
        saldo = null;
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
    
      public boolean verificarCredenciais(String cpf, String senha) {
        return clienteDAO.verificarCredenciais(cpf, senha);
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
    
    
    
public void abrirJanelaSaldo() {
        ControllerSaldo controllerSaldo = new ControllerSaldo(clienteDAO, new JanelaSaldo());
        controllerSaldo.consultarSaldo();
}

public void abrirJanelaDeposito() {
    try {
        // Obtenha a conexão usando a classe Conexao
        Connection conn = Conexao.getConnection();

        // Crie a janela de depósito e o controlador de depósito
        JanelaDepositar janelaDepositar = new JanelaDepositar(conn);
        ControllerDepositar controllerDepositar = new ControllerDepositar(janelaDepositar.clienteDAO, janelaDepositar);

        // Exiba a janela de depósito
        janelaDepositar.setVisible(true);
    } catch (SQLException e) {
        e.printStackTrace();
        // Trate o erro de conexão com o banco de dados adequadamente
    }
}

public class ControllerSacar {
    private ClienteDAO clienteDAO;
    private JanelaSacar janelaSacar;
    
     public ControllerSacar(Connection conn, JanelaSacar janelaSacar) {
        this.clienteDAO = new ClienteDAO(conn);
        this.janelaSacar = janelaSacar;
    }
    
    // Outros métodos...
    
public void abrirJanelaSaque() {
    try {
        // Obtenha a conexão usando a classe Conexao
        Connection conn = Conexao.getConnection();

        // Crie a janela de saque e o controlador de saque
        JanelaSacar janelaSacar = new JanelaSacar();
        ControllerSacar controllerSacar = new ControllerSacar(conn, janelaSacar);

        // Exiba a janela de saque
        janelaSacar.setVisible(true);
    } catch (SQLException e) {
        e.printStackTrace();
        // Trate o erro de conexão com o banco de dados adequadamente
    }
}

}
}
    
    

