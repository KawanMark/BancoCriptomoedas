package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OperacoesDAO {
    private Connection conn;

    public OperacoesDAO(Connection conn) {
        this.conn = conn;
    }

   public boolean registrarOperacao(String cpfCliente, String tipoOperacao, String moedaOperacao, double valorOperacao, double saldoAtual) {
        String sql = "INSERT INTO cliente (cpf, data_operacao, tipo_operacao, moeda_operacao, valor_operacao, saldo_reais" + moedaOperacao.toLowerCase() + ") VALUES (?, ?, ?, ?, ?, ?)";
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
   
   
}