package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Cotacao {
    private Map<String, Double> cotacoes;

    public static Cotacao obterCotacaoAtual(Connection conn) throws SQLException {
        Cotacao cotacaoAtualizada = new Cotacao();
        cotacaoAtualizada.carregarCotacoesDoBanco(conn); // Carrega as cotações do banco de dados
        return cotacaoAtualizada;
    }

    public Cotacao() {
        this.cotacoes = new HashMap<>();
    }

    // Método para carregar as cotações do banco de dados
    private void carregarCotacoesDoBanco(Connection conn) throws SQLException {
        String sql = "SELECT cotacoes_bitcoin, cotacoes_ethereum, cotacoes_ripple FROM cotacoes";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cotacoes.put("bitcoin", rs.getDouble("cotacoes_bitcoin"));
                cotacoes.put("ethereum", rs.getDouble("cotacoes_ethereum"));
                cotacoes.put("ripple", rs.getDouble("cotacoes_ripple"));
            }
        }
    }

    // Método para obter a cotação atual de uma determinada criptomoeda
 public double getCotacao(String moeda) {
    String moedaLowerCase = moeda.toLowerCase();
    if (cotacoes.containsKey(moedaLowerCase)) {
        return cotacoes.get(moedaLowerCase);
    } else {
        // Se a moeda não existir, lança uma exceção ou retorna um valor padrão
        throw new IllegalArgumentException("Moeda não reconhecida: " + moeda);
    }
}
 
public Map<String, Double> atualizarCotacoes() {
    Random rand = new Random();
    for (String moeda : cotacoes.keySet()) {
        double cotacaoAtual = cotacoes.get(moeda);
        // Gera uma variação aleatória entre -5% e +5%
        double variacao = (rand.nextDouble() * 0.1) - 0.05;
        double novaCotacao = cotacaoAtual * (1 + variacao);
        // Atualiza a cotação da moeda com a nova cotação limitada entre 0 e o dobro do valor anterior
        cotacoes.put(moeda, Math.max(0, Math.min(novaCotacao, cotacaoAtual * 2)));
    }

    // Retorna as cotações atualizadas
    return cotacoes;
}

    
    
}
