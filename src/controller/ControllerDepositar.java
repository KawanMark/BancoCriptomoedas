/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Timestamp;
import java.util.Date;
import DAO.ClienteDAO;
import DAO.OperacoesDAO;
import java.sql.Connection;
import view.JanelaDepositar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author kawan
 */
public class ControllerDepositar {

      private final ClienteDAO clienteDAO;
    private final JanelaDepositar janelaDepositar;

    public ControllerDepositar(ClienteDAO clienteDAO, JanelaDepositar janelaDepositar) {
        this.clienteDAO = clienteDAO;
        this.janelaDepositar = janelaDepositar;
    }

    public void realizarDeposito() {
        String cpf = JOptionPane.showInputDialog(janelaDepositar, "Digite seu CPF:");
        String senha = JOptionPane.showInputDialog(janelaDepositar, "Digite sua senha:");

        boolean credenciaisValidas = verificarCredenciais(cpf, senha);

        if (credenciaisValidas) {
            janelaDepositar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(janelaDepositar, "CPF ou senha inválidos. Por favor, tente novamente.");
        }
    }

    private boolean verificarCredenciais(String cpf, String senha) {
        try {
            return clienteDAO.consultarPorCPFESenha(cpf, senha).next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(janelaDepositar, "Erro ao verificar credenciais. Por favor, tente novamente.");
            e.printStackTrace();
            return false;
        }
    }

    public void realizarOperacaoDeposito(String cpf) {
        try {
            double valorDeposito = obterValorDeposito();
            if (valorDeposito < 0) {
                JOptionPane.showMessageDialog(janelaDepositar, "Valor de depósito inválido. Por favor, tente novamente.");
                return;
            }

            double saldoAntes = clienteDAO.consultarSaldo(cpf, "Reais");
            clienteDAO.depositar(cpf, valorDeposito);
            double saldoAtual = clienteDAO.consultarSaldo(cpf, "Reais");

            exibirInformacoesDeposito(saldoAntes, valorDeposito, saldoAtual);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(janelaDepositar, "Erro ao realizar depósito. Por favor, tente novamente.");
            e.printStackTrace();
        }
    }

    private double obterValorDeposito() {
        String valorDepositoStr = janelaDepositar.getTxtDeposito().getText();
        if (valorDepositoStr.isEmpty()) {
            return -1;
        }
        try {
            return Double.parseDouble(valorDepositoStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void exibirInformacoesDeposito(double saldoAntes, double valorDeposito, double saldoAtual) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataHoraDeposito = dateFormat.format(new Date());

        String mensagem = String.format("Saldo antes do depósito: R$ %.2f\nValor do depósito: R$ %.2f\nData e hora do depósito: %s\nSaldo atualizado: R$ %.2f",
                saldoAntes, valorDeposito, dataHoraDeposito, saldoAtual);

        janelaDepositar.getLblDepositar().setText(mensagem);
    }
}

