package DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.Extrato;
import model.Operacao;
import java.util.List;
import java.sql.ResultSet;


public class OperacoesDAO {
    private Connection conn;
    private ClienteDAO clienteDAO;

    public OperacoesDAO(Connection conn) {
        this.conn = conn;
        this.clienteDAO = new ClienteDAO(conn);
    }

public boolean registrarOperacao(String cpfCliente, String tipoOperacao, String moedaOperacao, double valorOperacao, double taxa, double saldoAtualReal, double saldoAtualBitcoin, double saldoAtualEthereum, double saldoAtualRipple) {
    String sql = "INSERT INTO operacoes (cpf_cliente, data_operacao, tipo_operacao, moeda_operacao, valor_operacao, taxa, saldo_atual_reais, saldo_atual_bitcoin, saldo_atual_ethereum, saldo_atual_ripple) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpfCliente);
        statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        statement.setString(3, tipoOperacao);
        statement.setString(4, moedaOperacao);
        statement.setDouble(5, valorOperacao);
        statement.setDouble(6, taxa);
        statement.setDouble(7, saldoAtualReal);
        statement.setDouble(8, saldoAtualBitcoin);
        statement.setDouble(9, saldoAtualEthereum);
        statement.setDouble(10, saldoAtualRipple);

        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}




public Extrato consultarExtrato(String cpf) throws SQLException {
    Extrato extrato = null;
    List<Operacao> operacoes = new ArrayList<>();

    String sql = "SELECT * FROM operacoes WHERE cpf_cliente = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, cpf);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LocalDateTime dataHora = rs.getTimestamp("data_operacao").toLocalDateTime();
                double valor = rs.getDouble("valor_operacao");
                double cotacao = rs.getDouble("cotacao");
                String tipo = rs.getString("tipo_operacao");
                double taxa = rs.getDouble("taxa");
                String moedaOperacao = rs.getString("moeda_operacao");
                double saldoReal = rs.getDouble("saldo_atual_reais");
                double saldoBitcoin = rs.getDouble("saldo_atual_bitcoin");
                double saldoEthereum = rs.getDouble("saldo_atual_ethereum");
                double saldoRipple = rs.getDouble("saldo_atual_ripple");

                // Criar a operação com as informações obtidas
                Operacao operacao = new Operacao(dataHora, valor, tipo, taxa, saldoReal, saldoBitcoin, saldoEthereum, saldoRipple, moedaOperacao);

                // Definir a cotação atual
                operacao.setCotacao(cotacao);

                operacoes.add(operacao);
            }
        }
    }

    String nomeCliente = "Kawan Mark Geronimo da Silva"; // Você pode buscar o nome do cliente no banco de dados se necessário
    extrato = new Extrato(nomeCliente, cpf, operacoes);

    return extrato;
}


 
 
 
//        public List<Operacao> getOperacoes(String cpf) {
//        List<Operacao> operacoes = new ArrayList<>();
//        String sql = "SELECT * FROM operacoes WHERE cpf_cliente = ?";
//        try {
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1, cpf);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                LocalDateTime dataHora = resultSet.getTimestamp("data_operacao").toLocalDateTime();
//                double valor = resultSet.getDouble("valor_operacao");
//                double cotacao = resultSet.getDouble("cotacao");
//                String tipo = resultSet.getString("tipo_operacao");
//                double taxa = resultSet.getDouble("taxa");
//                double saldoReal = resultSet.getDouble("saldo_atual_reais");
//                double saldoBitcoin = resultSet.getDouble("saldo_atual_bitcoin");
//                double saldoEthereum = resultSet.getDouble("saldo_atual_ethereum");
//                double saldoRipple = resultSet.getDouble("saldo_atual_ripple");
//                String moedaOperacao = resultSet.getString("moeda_operacao"); 
//
 //               Operacao operacao = new Operacao(dataHora, valor, tipo, taxa, saldoReal, saldoBitcoin, saldoEthereum, saldoRipple, moedaOperacao); // Modifique esta linha
//                operacoes.add(operacao);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
 //       }
 //       return operacoes;
 //   }

    // ... outros métodos ...
}
