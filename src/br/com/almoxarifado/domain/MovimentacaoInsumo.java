package br.com.almoxarifado.domain;

public class MovimentacaoInsumo {
    private Insumo insumo;
    private int quantidade;

    public MovimentacaoInsumo(Insumo insumo, int quantidade) {
        this.insumo = insumo;
        this.quantidade = quantidade;
    }

    public String getResumo() {
        return insumo.getNome() + " - " + quantidade + " unidade(s)";
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
