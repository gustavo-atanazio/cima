package br.com.totem.domain;

import java.time.LocalDateTime;

public abstract class Alteracao {
    protected int id;
    protected Funcionario funcionario;
    protected Totem totem;
    protected LocalDateTime dataHora;

    public Alteracao(Funcionario funcionario, Totem totem) {
        this.funcionario = funcionario;
        this.totem = totem;
        this.dataHora = LocalDateTime.now();
    }

    public abstract void registrar();
    public abstract String getResumo();

    public abstract void adicionarMovimentacao(MovimentacaoInsumo mov);
}
