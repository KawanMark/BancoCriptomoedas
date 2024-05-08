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
import javax.swing.JOptionPane;

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
    String sql = "SELECT saldo_" + tipoMoeda + " FROM cliente WHERE cpf = ?" ;
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setString(1, cpf);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getDouble("saldo_" + tipoMoeda);
            } else {
                return 0.0; // Ou outro valor padrão, se necessário
            }
        }
    } catch (SQLException e) {
        
        throw e;
    }
}
    
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

    
}