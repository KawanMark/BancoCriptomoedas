package controller;

import DAO.ClienteDAO;
import DAO.OperacoesDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Carteira;
import model.CompraInfo;
import model.Cotacao;
import model.Moedas;
import view.JanelaVenderCripto;

public class ControllerVenderCripto {
    private Connection conn;
    private JanelaVenderCripto janela;
    private ClienteDAO clienteDAO;
    private Carteira carteira;
    private Cotacao cotacao;
    private OperacoesDAO operacoesDAO;

    public ControllerVenderCripto(Connection conn, JanelaVenderCripto janela, Carteira carteira, Cotacao cotacao) {
        this.conn = conn;
        this.janela = janela;
        this.clienteDAO = new ClienteDAO(conn);
        this.carteira = carteira;
        this.cotacao = cotacao;
        this.operacoesDAO = new OperacoesDAO(conn);
    }

    public void atualizarSaldoDaCarteira(String cpf) {
        try {
            double saldoReais = clienteDAO.consultarSaldo(cpf, "Reais");
            carteira.setSaldoReais(saldoReais);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar saldo da carteira.");
        }
    }

public CompraInfo venderMoeda(double quantidade, String moedaSelecionada, String cpf) {
    // Antes de prosseguir com a venda, atualizamos o saldo da carteira
    atualizarSaldoDaCarteira(cpf);

    Moedas moeda;
    double saldoMoeda = 0.0;
     try {
        saldoMoeda = clienteDAO.consultarSaldo(cpf, moedaSelecionada);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(janela, "Erro ao consultar saldo da moeda.");
        return null;
    }
    // Determinar qual moeda foi selecionada e instanciar o objeto correspondente
    switch (moedaSelecionada) {
        case "Bitcoin":
            moeda = carteira.getBitcoin();
            break;
        case "Ethereum":
            moeda = carteira.getEthereum();
            break;
        case "Ripple":
            moeda = carteira.getRipple();
            break;
        default:
            // Moeda não reconhecida
            JOptionPane.showMessageDialog(janela, "Moeda selecionada não reconhecida.");
            return null;
    }
    
    
    // Verificar se o saldo da moeda é suficiente para a venda
        if (quantidade > saldoMoeda) {
            JOptionPane.showMessageDialog(janela, "Saldo insuficiente para realizar a venda.");
            return null;
        } else {
        // Obter a cotação atual da moeda
        double cotacaoAtual = cotacao.getCotacao(moedaSelecionada);
            System.out.println("QUANTIDADEEE " + quantidade);
            
            System.out.println("moeda get saldo " + saldoMoeda);
            
        // Calcular o valor em reais da venda
        double valorVenda = quantidade * cotacaoAtual;
        System.out.println("VALOR VENDAAA " + valorVenda);

        // Calcular a taxa de venda
        double taxaVenda = moeda.calcularTaxaVenda(valorVenda);
        System.out.println("taxa vendaa " + taxaVenda);
        
        // Atualize o saldo em reais e o saldo da criptomoeda
        double novoSaldoReais = carteira.getSaldoReais() + valorVenda - taxaVenda;
        carteira.setSaldoReais(novoSaldoReais);
        double saldoMoedaAtualizado = moeda.getSaldo() - quantidade;
        moeda.setSaldo(saldoMoedaAtualizado);

        // Atualize o saldo no banco de dados
        try {
            clienteDAO.atualizarSaldo(cpf, "Reais", novoSaldoReais);
            clienteDAO.removerSaldoCripto(cpf, quantidade, moedaSelecionada);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(janela, "Erro ao atualizar saldo no banco de dados.");
            return null;
        }
        
       
        operacoesDAO.registrarOperacao(cpf, "Venda", moedaSelecionada, valorVenda, taxaVenda, quantidade);

        String detalhesVenda = String.format("Venda realizada com sucesso!\n" +
         "Data e Hora: %s\n" +
         "Moeda: %s\n" +
         "Tipo: %s\n" +
         "Quantidade: %.8f\n" +
         "Cotação Atual: %.2f\n" +
         "Taxa de Venda: %.2f\n" +
         "Saldo Atual (Reais): %.2f\n" +
         "Novo Saldo de %s: %.8f\n",
         LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
         moedaSelecionada,
         moedaSelecionada,
         quantidade,
         cotacaoAtual,
         taxaVenda,
         novoSaldoReais,
         moedaSelecionada,
         saldoMoedaAtualizado
     );
janela.getLblVender().append(detalhesVenda);
    return new CompraInfo("Venda realizada com sucesso!", quantidade, moedaSelecionada);
}}
}
