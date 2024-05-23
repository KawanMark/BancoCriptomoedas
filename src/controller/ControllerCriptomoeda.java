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
import view.JanelaComprarCripto;

public class ControllerCriptomoeda {
    private Connection conn;
    private JanelaComprarCripto janela;
    private ClienteDAO clienteDAO;
    private Carteira carteira;
    private Cotacao cotacao;
    private OperacoesDAO operacoesDAO;
    
    

    public ControllerCriptomoeda(Connection conn, JanelaComprarCripto janela, Carteira carteira, Cotacao cotacao) {
        this.conn = conn;
        this.janela = janela;
        this.clienteDAO = new ClienteDAO(conn);
        this.carteira = carteira;
        this.cotacao = cotacao;
        this.operacoesDAO = new OperacoesDAO(conn);
    }

    public void atualizarSaldoDaCarteira(String cpf) {
        try {
            // Obtenha o saldo do banco de dados usando o CPF
            double saldoReais = clienteDAO.consultarSaldo(cpf, "Reais");

            // Atualize o saldo na carteira
            carteira.setSaldoReais(saldoReais);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao atualizar saldo da carteira.");
        }
    }
    
public CompraInfo comprarMoeda(double valorCompra, String moedaSelecionada, String cpf) throws SQLException {
    // Antes de prosseguir com a compra, atualizamos o saldo da carteira
    atualizarSaldoDaCarteira(cpf);

    Moedas moeda;

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
    

    
    // Obter a cotação atual da moeda
    double cotacaoAtual = cotacao.getCotacao(moedaSelecionada);

    // Calcular a taxa de compra
    double taxaCompra = moeda.calcularTaxaCompra(valorCompra);
    double taxaCompraArredondada = Math.floor(taxaCompra * 100) / 100;
    //taxaCompra = Math.round(taxaCompra * 100.0) / 100.0;

   
    // Subtrair a taxa do valor total da compra
    double valorTotalCompra = valorCompra - taxaCompra;
    
    // Calcular a quantidade de moeda a comprar
    double quantidadeComprada = valorTotalCompra / cotacaoAtual;

    quantidadeComprada = quantidadeComprada - (taxaCompra / cotacaoAtual);
    System.out.println(quantidadeComprada);


 
    System.out.println(carteira.getSaldoReais());
    
    if (valorTotalCompra > carteira.getSaldoReais()) {
        JOptionPane.showMessageDialog(janela, "Saldo insuficiente para realizar a compra.");
        return null;
    } else {
        double novoSaldoReais = carteira.getSaldoReais() - valorTotalCompra;
        carteira.setSaldoReais(novoSaldoReais);
        double saldoMoedaAtualizado = moeda.getSaldo() + quantidadeComprada;
        moeda.setSaldo(saldoMoedaAtualizado);

        try {
            clienteDAO.atualizarSaldo(cpf, "Reais", novoSaldoReais);
            clienteDAO.adicionarSaldoCripto(cpf, quantidadeComprada, moedaSelecionada);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(janela, "Erro ao atualizar saldo no banco de dados.");
            return null;
        }
        

        //operacoesDAO.registrarOperacao(cpf, "Compra", moedaSelecionada, valorCompra, taxaCompra, quantidadeComprada, cotacaoAtual);
            
        
            double saldoBitcoinAtualizado = clienteDAO.consultarSaldo(cpf, "Bitcoin");
            double saldoEthereumAtualizado = clienteDAO.consultarSaldo(cpf, "Ethereum");
            double saldoRippleAtualizado = clienteDAO.consultarSaldo(cpf, "Ripple");
            clienteDAO.atualizarSaldo(cpf, "Reais", novoSaldoReais);
            clienteDAO.adicionarSaldoCripto(cpf, quantidadeComprada, moedaSelecionada);
            operacoesDAO.registrarOperacao(cpf, "Compra", moedaSelecionada, valorTotalCompra, taxaCompra, novoSaldoReais, saldoBitcoinAtualizado, saldoEthereumAtualizado, saldoRippleAtualizado, cotacaoAtual);

        

        // Adicionar detalhes da compra ao lblComprar
        String detalhesCompra = String.format("Compra realizada com sucesso!\nData e Hora: %s\nMoeda: %s\nQuantidade: %.8f\nCotação Atual: %.2f\nTaxa de Compra: %.2f\nSaldo Atual: %.2f\n",
            LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
            moedaSelecionada,
            quantidadeComprada,
            cotacaoAtual,
            taxaCompraArredondada, 
            novoSaldoReais
        );
        janela.getLblComprar().append(detalhesCompra);
    }

    // Retornar um objeto CompraInfo com as informações da compra
    return new CompraInfo("Compra realizada com sucesso!", valorCompra, moedaSelecionada);
}
}
