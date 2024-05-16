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
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author kawan
 */
public class ControllerExtrato {
     private Connection conn;
    private JanelaExtrato janelaExtrato;
    private OperacoesDAO operacoesDAO;
    
    public ControllerExtrato(JanelaExtrato janelaExtrato) {
        this.janelaExtrato = janelaExtrato;
    }
  public void consultarExtrato(String cpf) {
        try {
            Extrato extrato = operacoesDAO.consultarExtrato(cpf);
            exibirExtrato(extrato);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar extrato.");
        }
    }
public void exibirExtrato(Extrato extrato) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    StringBuilder sb = new StringBuilder();
    sb.append("Nome: ").append(extrato.getNome()).append("\n");
    sb.append("CPF: ").append(extrato.getCpf()).append("\n");
    sb.append("\n");

    for (Operacao operacao : extrato.getOperacoes()) {
        String dataFormatada = operacao.getDataHora().format(formatter);
        double valorFormatado = Double.parseDouble(String.format("%.2f", operacao.getValor()));
        double valorCriptoFormatado = Double.parseDouble(String.format("%.2f", operacao.getValorCripto()));
        double taxaFormatada = Double.parseDouble(String.format("%.2f", operacao.getTaxa()));
        double valorRealFormatado = Double.parseDouble(String.format("%.2f", operacao.getValorReal()));
        double saldoBitcoinFormatado = Double.parseDouble(String.format("%.2f", operacao.getSaldoBitcoin()));
        double saldoEthereumFormatado = Double.parseDouble(String.format("%.2f", operacao.getSaldoEthereum()));
        double saldoRippleFormatado = Double.parseDouble(String.format("%.2f", operacao.getSaldoRipple()));

        sb.append(dataFormatada).append(" ");
        sb.append(operacao.getTipo()).append(" ");
        sb.append(valorFormatado).append(" ");
        sb.append("CT: ").append(valorCriptoFormatado).append(" ");
        sb.append("TX: ").append(taxaFormatada).append(" ");
        sb.append("REAL: ").append(valorRealFormatado).append(" ");
        sb.append("BTC: ").append(saldoBitcoinFormatado).append(" ");
        sb.append("ETH: ").append(saldoEthereumFormatado).append(" ");
        sb.append("XRP: ").append(saldoRippleFormatado).append("\n");
    }

    janelaExtrato.getLblExtrato().setText(sb.toString());
}
}