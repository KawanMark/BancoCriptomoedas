/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ClienteDAO;
import DAO.OperacoesDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.JanelaSacar;

/**
 *
 * @author kawan
 */
public class ControllerSacar {
    private ClienteDAO clienteDAO;
    private JanelaSacar janelaSacar;
    private Connection conn;
    
    public ControllerSacar(Connection conn, JanelaSacar janelaSacar) {
        this.clienteDAO = new ClienteDAO(conn);
        this.janelaSacar = janelaSacar;
        this.conn = conn;
    }
      public void atualizarSaldoAtual(double saldoAtualizado) {
        janelaSacar.atualizarSaldoAtual(saldoAtualizado);
    }
      
      private boolean verificarCredenciais(String cpf, String senha) {
        try {
            return clienteDAO.consultarPorCPFESenha(cpf, senha).next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(janelaSacar, "Erro ao verificar credenciais. Por favor, tente novamente.");
            e.printStackTrace();
            return false;
        }
    }  
      
    public void abrirSaque() {
        String cpf = JOptionPane.showInputDialog(janelaSacar, "Digite seu CPF:");
        String senha = JOptionPane.showInputDialog(janelaSacar, "Digite sua senha:");

        boolean credenciaisValidas = verificarCredenciais(cpf, senha);

        if (credenciaisValidas) {
            janelaSacar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(janelaSacar, "CPF ou senha inválidos. Por favor, tente novamente.");
        }
    }
      
   
      

public void realizarSaque(double valorSaque) {
    try {
        String cpf = clienteDAO.obterCpfCliente();
        
        double saldoAtual = clienteDAO.consultarSaldo(cpf, "Reais");
        
        if (valorSaque > saldoAtual) {
            JOptionPane.showMessageDialog(janelaSacar, "Saldo insuficiente para realizar o saque.");
        } else {
            double novoSaldo = saldoAtual - valorSaque;
            clienteDAO.atualizarSaldo(cpf, "Reais", novoSaldo);
            janelaSacar.atualizarSaldoAtual(novoSaldo);
            JOptionPane.showMessageDialog(janelaSacar, "Saque realizado com sucesso!");
            
            OperacoesDAO operacoesDAO = new OperacoesDAO(conn);
            //operacoesDAO.registrarOperacao(cpf, "Saque", "Reais", valorSaque, 0.0, saldoAtual, 0.0);
             
             operacoesDAO.registrarOperacao(cpf, "Saque", "Reais", valorSaque, 0.0, novoSaldo,
                clienteDAO.consultarSaldo(cpf, "Bitcoin"), clienteDAO.consultarSaldo(cpf, "Ethereum"),
                clienteDAO.consultarSaldo(cpf, "Ripple"), 0.0);


        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(janelaSacar, "Erro ao realizar saque. Por favor, tente novamente.");
        e.printStackTrace();
    }
}}