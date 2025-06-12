package br.com.totem.domain;

public class Totem {
    private int id;
    private int unidadeId;

    public Totem(int id, int unidadeId) {
        this.id = id;
        this.unidadeId = unidadeId;
    }

    public int getUnidadeId() {
        return unidadeId;
    }
}
