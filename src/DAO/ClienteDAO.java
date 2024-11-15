/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import utils.Util;

/**
 *
 * @author kawan
 */
public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    

    public ResultSet consultarPorCPFESenha(String cpf, String senha) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE cpf = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.setString(2, senha);
        return statement.executeQuery();
    }
    
  public double consultarSaldo(String cpf, String tipoMoeda) throws SQLException {
        String sql = "SELECT saldo_" + tipoMoeda + " FROM cliente WHERE cpf = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, cpf);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("saldo_" + tipoMoeda);
                } else {
                    return 0.0; 
                }
            }
        }
    }
  
  /**
     * Atualiza o saldo do cliente.
     *
     * @param cpfCliente CPF do cliente.
     * @param tipoMoeda Tipo de moeda.
     * @param novoSaldo Novo saldo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    
 public void atualizarSaldo(String cpfCliente, String tipoMoeda, double novoSaldo) throws SQLException {
    String sql = "UPDATE cliente SET saldo_" + tipoMoeda + " = ? WHERE cpf = ?";

    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        // Define o novo saldo
        statement.setDouble(1, novoSaldo);
        // Define o CPF do cliente
        statement.setString(2, cpfCliente);
        // Executa a atualização
        statement.executeUpdate();
    }
}

    
    // Método para verificar as credenciais do cliente no banco de dados
    public boolean verificarCredenciais(String cpf, String senha) {
        try {
            // Consulta no banco de dados se existe um cliente com o CPF e a senha fornecidos
            // Se encontrar, retorna true; caso contrário, retorna false
            ResultSet resultSet = consultarPorCPFESenha(cpf, senha);
            return resultSet.next(); // Se houver uma linha no ResultSet, significa que as credenciais são válidas
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar credenciais. Por favor, tente novamente.");
            e.printStackTrace();
            return false;
        }
}
 public void depositar(String cpf, double valor) throws SQLException {
        double saldoAtual = consultarSaldo(cpf, "Reais");
        atualizarSaldo(cpf, "Reais", saldoAtual + valor);
    }
 
   public boolean registrarOperacao(String cpfCliente, String tipoOperacao, String moedaOperacao, double valorOperacao, double saldoAtual) {
        String sql = "INSERT INTO cliente (cpf, data_operacao, tipo_operacao, moeda_operacao, valor_operacao, saldo_" + moedaOperacao.toLowerCase() + ") VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfCliente);
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(3, tipoOperacao);
            statement.setString(4, moedaOperacao);
            statement.setDouble(5, valorOperacao);
            statement.setDouble(6, saldoAtual);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
   /**
     * Adiciona saldo em criptomoedas ao cliente.
     *
     * @param cpf CPF do cliente.
     * @param quantidade Quantidade a ser adicionada.
     * @param moeda Tipo de moeda a ser adicionada.
     */
   
public void adicionarSaldoCripto(String cpf, double quantidade, String moeda) {
    String nomeColuna = "saldo_" + moeda.toLowerCase(); // Obtém o nome da coluna com base na moeda

    // Limita a quantidade a 6 casas decimais
    String quantidadeStr = Util.formatDouble(quantidade);
    quantidade = Util.parseDouble(quantidadeStr);

    // Constrói a query SQL para adicionar a quantidade ao saldo existente
    String sql = "UPDATE cliente SET " + nomeColuna + " = " + nomeColuna + " + ? WHERE cpf = ?";
    try {
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDouble(1, quantidade);
        statement.setString(2, cpf);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao adicionar saldo ao banco de dados.");
    }
}


    
     public void salvarCotacoes(double cotacaoBitcoin, double cotacaoEthereum, double cotacaoRipple) {
        String sql = "UPDATE cotacoes SET cotacoes_bitcoin = ?, cotacoes_ethereum = ?, cotacoes_ripple = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, cotacaoBitcoin);
            stmt.setDouble(2, cotacaoEthereum);
            stmt.setDouble(3, cotacaoRipple);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    public void removerSaldoCripto(String cpf, double quantidade, String moedaSelecionada) throws SQLException {
        String nomeColuna = "saldo_" + moedaSelecionada.toLowerCase(); // 

        String sql = "UPDATE cliente SET " + nomeColuna + " = " + nomeColuna + " - ? WHERE cpf = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setDouble(1, quantidade);
        stmt.setString(2, cpf);
        stmt.executeUpdate();
    }
    
    
    public String obterCpfCliente() throws SQLException {
    String sql = "SELECT cpf FROM cliente LIMIT 1"; // 
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("cpf");
            } else {
                throw new SQLException("Nenhum cliente encontrado no banco de dados.");
            }
        }
    }
}
    
    
    
      public boolean isCotacoesVazia() throws SQLException {
        String sql = "SELECT COUNT(*) FROM cotacoes";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0;
            } else {
                throw new SQLException("Erro ao verificar se a tabela cotacoes está vazia.");
            }
        }
    }

    public void inserirCotacoesIniciais() throws SQLException {
        String sql = "INSERT INTO cotacoes (cotacoes_bitcoin, cotacoes_ethereum, cotacoes_ripple) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, 150); 
            stmt.setDouble(2, 50); 
            stmt.setDouble(3, 1.4); 
            stmt.executeUpdate();
        }
    }


}

    
