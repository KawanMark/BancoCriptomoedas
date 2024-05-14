/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.OperacoesDAO;
import view.JanelaExtrato;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import model.Extrato;
import model.Operacao;

/**
 *
 * @author kawan
 */
public class ControllerExtrato {
     private Connection conn;
    private JanelaExtrato janelaExtrato;
    private OperacoesDAO operacoesDAO;
    
    
  public void consultarExtrato(String cpf) {
        try {
            Extrato extrato = operacoesDAO.consultarExtrato(cpf);
            exibirExtrato(extrato);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar extrato.");
        }
    }

    private void exibirExtrato(Extrato extrato) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(extrato.getNome()).append("\n");
        sb.append("CPF: ").append(extrato.getCpf()).append("\n");
        sb.append("\n");

        for (Operacao operacao : extrato.getOperacoes()) {
            sb.append(operacao.getDataHora()).append(" ");
            sb.append(operacao.getTipo()).append(" ");
            sb.append(operacao.getValor()).append(" ");
            sb.append("CT: ").append(operacao.getValorCripto()).append(" ");
            sb.append("TX: ").append(operacao.getTaxa()).append(" ");
            sb.append("REAL: ").append(operacao.getValorReal()).append(" ");
            sb.append("BTC: ").append(operacao.getSaldoBitcoin()).append(" ");
            sb.append("ETH: ").append(operacao.getSaldoEthereum()).append(" ");
            sb.append("XRP: ").append(operacao.getSaldoRipple()).append("\n");
        }

        janelaExtrato.getLblExtrato().setText(sb.toString());
    }
}