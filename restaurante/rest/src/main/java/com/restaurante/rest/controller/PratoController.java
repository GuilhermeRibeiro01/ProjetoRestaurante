package com.restaurante.rest.controller;

import com.restaurante.rest.model.dao.PratoDAO;
import com.restaurante.rest.model.entidade.Prato;

import java.util.List;

public class PratoController {
    private PratoDAO pratoDAO;

    public PratoController(PratoDAO pratoDAO) {
        this.pratoDAO = pratoDAO;
    }

    public void inserir(Prato prato) {
        pratoDAO.inserir(prato);
    }

    public List<Prato> listar() {
        return pratoDAO.listar();
    }

    public void excluir(int id) {
        pratoDAO.excluir(id);
    }

    public void atualizar(Prato prato) {
        pratoDAO.atualizar(prato);
    }

    public Prato buscarPorId(int id) {
        return pratoDAO.buscarPorId(id);
    }

}
