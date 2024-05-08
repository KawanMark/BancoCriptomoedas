/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import DAO.ClienteDAO;
import java.sql.SQLException;
import view.JanelaSaldo;
import java.sql.Timestamp;
import java.util.Date;
import DAO.ClienteDAO;
import java.sql.Connection;
import view.JanelaDepositar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author kawan
 */
public class ControllerSaldo {
     private ClienteDAO clienteDAO;
    private JanelaSaldo janelaSaldo;

    // Construtor da classe
    public ControllerSaldo(ClienteDAO clienteDAO, JanelaSaldo janelaSaldo) {
        this.clienteDAO = clienteDAO;
        this.janelaSaldo = janelaSaldo;
    }

    // Método para lidar com a ação do botão "Consultar Saldo"
    public void consultarSaldo() {
        // Exibe uma caixa de diálogo para o usuário inserir CPF e senha
        String cpf = JOptionPane.showInputDialog(janelaSaldo, "Digite seu CPF:");
        String senha = JOptionPane.showInputDialog(janelaSaldo, "Digite sua senha:");

        // Verifica se o CPF e a senha correspondem a um registro no banco de dados
        boolean credenciaisValidas = verificarCredenciais(cpf, senha);

        if (credenciaisValidas) {
            exibirSaldo(cpf);
        } else {
            JOptionPane.showMessageDialog(janelaSaldo, "CPF ou senha inválidos. Por favor, tente novamente.");
        }
    }

    // Método para verificar as credenciais do cliente no banco de dados
   private boolean verificarCredenciais(String cpf, String senha) {
    try {
        // Consulta no banco de dados se existe um cliente com o CPF e a senha fornecidos
        ResultSet resultSet = clienteDAO.consultarPorCPFESenha(cpf, senha);
        
        // Se houver algum resultado, significa que as credenciais são válidas
        return resultSet.next();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(janelaSaldo, "Erro ao verificar credenciais. Por favor, tente novamente.");
        e.printStackTrace();
        return false;
    }
}


    // Método para exibir o saldo do cliente
    private void exibirSaldo(String cpf) {
        try {
            // Recupera o saldo do cliente do banco de dados usando o CPF
            double saldoReais = clienteDAO.consultarSaldo(cpf, "Reais");
            double saldoBitcoin = clienteDAO.consultarSaldo(cpf, "Bitcoin");
            double saldoEthereum = clienteDAO.consultarSaldo(cpf, "Ethereum");
            double saldoRipple = clienteDAO.consultarSaldo(cpf, "Ripple");

            // Formata o saldo para exibição na janela
            String saldoFormatado = String.format("Nome: %s\nCPF: %s\nReais: %.2f\nBitcoin: %.4f\nEthereum: %.2f\nRipple: %.2f",
                "Kawan Mark Geronimo da S", cpf, saldoReais, saldoBitcoin, saldoEthereum, saldoRipple);

            // Exibe o saldo na janela
            janelaSaldo.getLblSaldo().setText(saldoFormatado);

            // Exibe a janela de saldo
            janelaSaldo.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(janelaSaldo, "Erro ao consultar saldo. Por favor, tente novamente.");
            e.printStackTrace();
        }
    }
}