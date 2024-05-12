package controller;

import DAO.ClienteDAO;
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
    

    public ControllerCriptomoeda(Connection conn, JanelaComprarCripto janela, Carteira carteira, Cotacao cotacao) {
        this.conn = conn;
        this.janela = janela;
        this.clienteDAO = new ClienteDAO(conn);
        this.carteira = carteira;
        this.cotacao = cotacao;
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
    
    
    
    

public CompraInfo comprarMoeda(double valor, String moedaSelecionada, String cpf) {
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

    // Calcular a quantidade de moeda a comprar
    double quantidadeComprada = valor / cotacaoAtual;

    // Verificar se o saldo é suficiente para a compra
    if (valor > carteira.getSaldoReais()) {
        JOptionPane.showMessageDialog(janela, "Saldo insuficiente para realizar a compra.");
        return null;
    } else {
        ZoneId zonaBrasilia = ZoneId.of("America/Sao_Paulo");
        LocalDateTime horarioBrasilia = LocalDateTime.now(zonaBrasilia);

        // Usar a data e hora formatada na mensagem de compra
        String mensagemCompra = String.format("Compra realizada com sucesso!\nData e Hora: %s\n", horarioBrasilia.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        // Atualizar o saldo de Reais na carteira
        double novoSaldoReais = carteira.getSaldoReais() - valor;
        carteira.setSaldoReais(novoSaldoReais);

        // Atualizar o saldo da moeda comprada na carteira
        double saldoMoedaAtualizado = moeda.getSaldo() + quantidadeComprada;
        moeda.setSaldo(saldoMoedaAtualizado);

        // Atualizar o saldo no banco de dados
        try {
            clienteDAO.atualizarSaldo(cpf, "Reais", novoSaldoReais);
            clienteDAO.adicionarSaldoCripto(cpf, quantidadeComprada, moedaSelecionada);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(janela, "Erro ao atualizar saldo no banco de dados.");
            return null;
        }
        
        // Registrar a operação no banco de dados
        clienteDAO.registrarOperacao(cpf, "Compra", moedaSelecionada, valor, novoSaldoReais);

        // Adicionar detalhes da compra ao lblComprar
        String detalhesCompra = String.format("Compra realizada com sucesso!\nData e Hora: %s\nMoeda: %s\nQuantidade: %.8f\nCotação Atual: %.2f\nSaldo Atual: %.2f\n",
            horarioBrasilia.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")),
            moedaSelecionada,
            quantidadeComprada,
            cotacaoAtual,
            novoSaldoReais
        );
        janela.getLblComprar().append(detalhesCompra);
    }

    // Retornar um objeto CompraInfo com as informações da compra
    return new CompraInfo("Compra realizada com sucesso!", valor, moedaSelecionada);
}



}
