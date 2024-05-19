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

             cotacoes.put("bitcoin", 150.0); 
             cotacoes.put("ethereum", 50.0); 
             cotacoes.put("ripple", 1.4); 
        }

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

     public double getCotacao(String moeda) {

        String moedaLowerCase = moeda.toLowerCase();
        if (cotacoes.containsKey(moedaLowerCase)) {
            return cotacoes.get(moedaLowerCase);
        } else {
            throw new IllegalArgumentException("Moeda não reconhecida: " + moeda);
        }
        
    }

    public Map<String, Double> atualizarCotacoes() {
        Random rand = new Random();
        for (String moeda : cotacoes.keySet()) {
            double cotacaoAtual = cotacoes.get(moeda);
            double variacao = (rand.nextDouble() * 0.1) - 0.05;
            double novaCotacao = cotacaoAtual * (1 + variacao);
            cotacoes.put(moeda, Math.max(0, Math.min(novaCotacao, cotacaoAtual * 2)));
        }

        return cotacoes;
    }



    }
