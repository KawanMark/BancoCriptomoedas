/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ClienteDAO;
import DAO.OperacoesDAO;
import view.JanelaExtrato;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import model.Extrato;
import model.Operacao;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import model.CompraInfo;
/**
 *
 * @author kawan
 */
public class ControllerExtrato {
     private Connection conn;
    private JanelaExtrato janelaExtrato;
    private OperacoesDAO operacoesDAO;
    private ClienteDAO clienteDAO;
    
    public ControllerExtrato(JanelaExtrato janelaExtrato) {
        this.janelaExtrato = janelaExtrato;
        this.operacoesDAO = new OperacoesDAO(conn);
        this.clienteDAO = new ClienteDAO(conn);
        
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

    for (Operacao operacao : extrato.getOperacoes()) {
        String sinal = operacao.getTipo().equals("Depósito") || operacao.getTipo().equals("Venda") ? " + R$ " : " - R$ ";
        sb.append(String.format("%s%s %.2f %s %s CT: %.4f TX: %.2f REAL: %.2f BTC: %.4f ETH: %.4f XRP: %.4f\n",
            operacao.getDataHora().format(formatter),
            sinal,
            operacao.getValor(),
            operacao.getTipo(),
            operacao.getMoedaOperacao(),
            operacao.getCotacao(),
            operacao.getTaxa(),
            operacao.getSaldoReal(),
            operacao.getSaldoBitcoin(),
            operacao.getSaldoEthereum(),
            operacao.getSaldoRipple()
        ));
    }
    
    janelaExtrato.getLblExtrato().setText(sb.toString());
}


}