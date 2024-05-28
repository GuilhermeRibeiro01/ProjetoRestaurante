package com.restaurante.rest.controller;

import com.restaurante.rest.model.dao.ClienteDAO;
import com.restaurante.rest.model.entidade.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) throws SQLException {
        this.clienteDAO = new ClienteDAO();
    }

    public void inserir(Cliente cliente) {
        clienteDAO.inserir(cliente);
    }

    public void atualizar(Cliente cliente) {
        Cliente clienteExistente = clienteDAO.buscarPorId(cliente.getId());
        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        clienteDAO.atualizar(cliente);
    }

    public void excluir(int id) {
        Cliente clienteExistente = clienteDAO.buscarPorId(id);
        if (clienteExistente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
        clienteDAO.excluir(id);
    }

    public List<Cliente> listar() {
        return clienteDAO.listar();
    }
}
