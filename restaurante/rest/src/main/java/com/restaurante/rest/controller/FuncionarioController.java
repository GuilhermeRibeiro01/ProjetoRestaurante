package com.restaurante.rest.controller;

import com.restaurante.rest.model.dao.FuncionarioDAO;
import com.restaurante.rest.model.entidade.Funcionario;


import java.util.List;

public class FuncionarioController {
    private FuncionarioDAO funcionarioDAO;

   
    public FuncionarioController(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = funcionarioDAO;
    }

    public void inserir(Funcionario funcionario) {
        funcionarioDAO.inserir(funcionario);
    }

    public List<Funcionario> listar() {
        return funcionarioDAO.listar();
    }

    public void excluir(int id) {
        funcionarioDAO.excluir(id);
    }

    public void atualizar(Funcionario funcionario) {
        funcionarioDAO.atualizar(funcionario);
    }
}
