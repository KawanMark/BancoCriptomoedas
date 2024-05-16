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

    public OperacoesDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean registrarOperacao(String cpfCliente, String tipoOperacao, String moedaOperacao, double valorOperacao, double taxa, double saldoAtual) {
        String sql = "INSERT INTO operacoes (cpf_cliente, data_operacao, tipo_operacao, moeda_operacao, valor_operacao, taxa, saldo_atual_" + moedaOperacao.toLowerCase() + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfCliente);
            statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(3, tipoOperacao);
            statement.setString(4, moedaOperacao);
            statement.setDouble(5, valorOperacao);
            statement.setDouble(6, taxa);
            
            // Defina o saldo atual como 0 se não houver operações
            double saldo = (saldoAtual != 0) ? saldoAtual : 0;
            statement.setDouble(7, saldo);
            
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
                    String tipo = rs.getString("tipo_operacao");
                    double taxa = rs.getDouble("taxa");
                    double saldoReal = rs.getDouble("saldo_atual_reais");
                    double saldoBitcoin = rs.getDouble("saldo_atual_bitcoin");
                    double saldoEthereum = rs.getDouble("saldo_atual_ethereum");
                    double saldoRipple = rs.getDouble("saldo_atual_ripple");

                    Operacao operacao = new Operacao(dataHora, valor, tipo, taxa, saldoReal, saldoBitcoin, saldoEthereum, saldoRipple);
                    operacoes.add(operacao);
                }
            }
        }

 
        String nomeCliente = "Kawan Mark Geronimo da Silva";
        extrato = new Extrato(nomeCliente, cpf, operacoes); 

        return extrato;
    }
}