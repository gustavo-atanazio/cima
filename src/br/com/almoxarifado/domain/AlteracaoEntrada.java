package br.com.almoxarifado.domain;
import java.util.ArrayList;
import java.util.List;

public class AlteracaoEntrada extends Alteracao {
    private List<MovimentacaoInsumo> entradas;

    public AlteracaoEntrada(Funcionario funcionario, Totem totem) {
        super(funcionario, totem);
        this.entradas = new ArrayList<>();
    }

    public void adicionarMovimentacao(MovimentacaoInsumo mov) {
        entradas.add(mov);
    }

    @Override
    public void registrar() {
        // Implementação futura da lógica para persistir as entradas no banco de dados ou outro armazenamento
        System.out.println(getResumo());
    }

    @Override
    public String getResumo() {
        StringBuilder sb = new StringBuilder("Entrada por " + funcionario.getNome() + ":\n");
        for (MovimentacaoInsumo ent : entradas) {
            sb.append(ent.getResumo()).append("\n");
        }
        return sb.toString();
    }

    public List<MovimentacaoInsumo> getEntradas() {
        return entradas;
    }
}