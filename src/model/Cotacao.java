/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author kawan
 */
public class Cotacao {
     private Map<String, Double> cotacoes;
     
      public static Cotacao obterCotacaoAtual() {
        Cotacao cotacaoAtualizada = new Cotacao();
        cotacaoAtualizada.atualizarCotacoes(); // Atualiza as cotações

        return cotacaoAtualizada;
    }

    public Cotacao() {
        this.cotacoes = new HashMap<>();
        // Inicializa as cotações com valores iniciais
        this.cotacoes.put("Bitcoin", 50000.0); // Exemplo de valor inicial para o Bitcoin
        this.cotacoes.put("Ethereum", 3000.0); // Exemplo de valor inicial para o Ethereum
        this.cotacoes.put("Ripple", 1.5); // Exemplo de valor inicial para o Ripple
    }

    // Método para obter a cotação atual de uma determinada criptomoeda
    public double getCotacao(String moeda) {
        if (cotacoes.containsKey(moeda)) {
            return cotacoes.get(moeda);
        } else {
            // Se a moeda não existir, lança uma exceção ou retorna um valor padrão
            throw new IllegalArgumentException("Moeda não reconhecida: " + moeda);
        }
    }

    // Método para atualizar todas as cotações com variações aleatórias limitadas
    public void atualizarCotacoes() {
        Random rand = new Random();
        for (String moeda : cotacoes.keySet()) {
            double cotacaoAtual = cotacoes.get(moeda);
            // Gera uma variação aleatória entre -5% e +5%
            double variacao = (rand.nextDouble() * 0.1) - 0.05;
            double novaCotacao = cotacaoAtual * (1 + variacao);
            // Atualiza a cotação da moeda com a nova cotação limitada entre 0 e o dobro do valor anterior
            cotacoes.put(moeda, Math.max(0, Math.min(novaCotacao, cotacaoAtual * 2)));
        }
    }
}

