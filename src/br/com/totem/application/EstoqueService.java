package br.com.totem.application;

import br.com.totem.domain.*;
import br.com.totem.infrastructure.FakeDatabase;

public class EstoqueService {
    private final Totem totem;
    private final Unidade unidade;
    private final FakeDatabase database;
    private static final int NIVEL_ACESSO_FUNCIONARIO = 1;

    public EstoqueService(int totemId) {
        this.database = new FakeDatabase();
        this.totem = database.getTotemById(totemId);
        if (this.totem == null) {
            throw new IllegalArgumentException("Totem não encontrado para o ID: " + totemId);
        }
        this.unidade = database.getUnidadeByTotemId(totem.getUnidadeId());
        if (this.unidade == null) {
            throw new IllegalArgumentException("Unidade não encontrada para o totem ID: " + totemId);
        }
    }

    public boolean autenticarFuncionario(String token) {
        Funcionario funcionario = database.getFuncionarioByToken(token);
        return funcionario != null && funcionario.getNivelAcesso() >= NIVEL_ACESSO_FUNCIONARIO;
    }

    public Funcionario getFuncionarioByToken(String token) {
        return database.getFuncionarioByToken(token);
    }

    public Insumo getInsumoByCodigo(int codigo) {
        return database.getInsumoByCodigo(codigo);
    }

    public void registrarEntrada(AlteracaoEntrada entrada) {
        for (MovimentacaoInsumo mov : entrada.getEntradas()) {
            unidade.adicionarInsumo(mov.getInsumo(), mov.getQuantidade());
        }
        database.salvarUnidade(unidade);
        entrada.registrar();
    }

    public void registrarSaida(AlteracaoSaida saida) {
        for (MovimentacaoInsumo mov : saida.getSaidas()) {
            unidade.removerInsumo(mov.getInsumo(), mov.getQuantidade());
        }
        database.salvarUnidade(unidade);
        saida.registrar();
    }

    public Totem getTotem() {
        return totem;
    }

    public Unidade getUnidade() {
        return unidade;
    }
}
