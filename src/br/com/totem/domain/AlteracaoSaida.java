package br.com.totem.domain;

import java.util.ArrayList;
import java.util.List;

public class AlteracaoSaida extends Alteracao {
    private List<MovimentacaoInsumo> saidas;

    public AlteracaoSaida(Funcionario funcionario, Totem totem) {
        super(funcionario, totem);
        this.saidas = new ArrayList<>();
    }

    public void adicionarMovimentacao(MovimentacaoInsumo mov) {
        saidas.add(mov);
    }

    @Override
    public void registrar() {
        // Implementação futura da lógica para persistir as saídas no banco de dados ou outro armazenamento
        System.out.println(getResumo());
    }

    @Override
    public String getResumo() {
        StringBuilder sb = new StringBuilder("Saída por " + funcionario.getNome() + ":\n");
        for (MovimentacaoInsumo sai : saidas) {
            sb.append(sai.getResumo()).append("\n");
        }
        return sb.toString();
    }

    public List<MovimentacaoInsumo> getSaidas() {
        return saidas;
    }
}

