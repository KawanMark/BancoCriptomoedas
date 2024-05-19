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
        this.operacoesDAO = new OperacoesDAO(conn);
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    StringBuilder sb = new StringBuilder();
    sb.append("Nome: ").append(extrato.getNome()).append("\n");
    sb.append("CPF: ").append(extrato.getCpf()).append("\n\n");
    sb.append(String.format("%-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
        "Data e Hora", "Operação", "Valor (R$)", "CT", "TX (R$)", "Saldo REAL", "Saldo BTC", "Saldo ETH", "Saldo XRP"));

    for (Operacao operacao : extrato.getOperacoes()) {
        String sinal = operacao.getTipo().equals("Deposito") || operacao.getTipo().equals("Compra") ? "+" : "-";
        sb.append(String.format("%-20s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
            operacao.getDataHora().format(formatter),
            operacao.getTipo(),
            sinal + String.format("%.3f", operacao.getValor()),
            String.format("%.3f", operacao.getCotacao()),
            String.format("%.3f", operacao.getTaxa()),
            String.format("%.2f", operacao.getSaldoReal()),
            String.format("%.3f", operacao.getSaldoBitcoin()),
            String.format("%.3f", operacao.getSaldoEthereum()),
            String.format("%.3f", operacao.getSaldoRipple())
        ));
    }

    janelaExtrato.getLblExtrato().setText(sb.toString());
}

}