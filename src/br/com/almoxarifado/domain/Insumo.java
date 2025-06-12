package br.com.almoxarifado.domain;

public class Insumo {
    private int id;
    private String nome;

    public Insumo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Insumo insumo = (Insumo) o;
        return id == insumo.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

}
