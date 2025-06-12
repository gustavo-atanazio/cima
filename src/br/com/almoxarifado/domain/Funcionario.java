package br.com.almoxarifado.domain;

public class Funcionario {
    private int id;
    private int unidadeId;
    private String nome;
    private int nivelAcesso;
    private String token;

    public Funcionario(int id, int unidadeId, String nome, int nivelAcesso, String token) {
        this.id = id;
        this.unidadeId = unidadeId;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public String getToken() {
        return token;
    }

}
