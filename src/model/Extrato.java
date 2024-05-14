package model;

import java.util.List;

public class Extrato {
    private String nome;
    private String cpf;
    private List<Operacao> operacoes;

    public Extrato(String nome, String cpf, List<Operacao> operacoes) {
        this.nome = nome;
        this.cpf = cpf;
        this.operacoes = operacoes;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Operacao> getOperacoes() {
        return operacoes;
    }
}
