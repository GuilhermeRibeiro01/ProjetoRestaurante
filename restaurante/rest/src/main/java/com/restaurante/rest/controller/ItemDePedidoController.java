package com.restaurante.rest.controller;

import com.restaurante.rest.model.dao.ItemDePedidoDAO;
import com.restaurante.rest.model.entidade.ItemDePedido;

import java.sql.SQLException;
import java.util.List;

public class ItemDePedidoController {
    private ItemDePedidoDAO itemDePedidoDAO;

    public ItemDePedidoController(ItemDePedidoDAO itemDePedidoDAO) throws SQLException {
        this.itemDePedidoDAO =  itemDePedidoDAO;
    }

    public void inserir(ItemDePedido itemDePedido) {
        itemDePedidoDAO.inserir(itemDePedido);
    }

    public void atualizar(ItemDePedido itemDePedido) {
        itemDePedidoDAO.atualizar(itemDePedido);
    }

    public void excluir(int id) {
        itemDePedidoDAO.excluir(id);
    }

    public List<ItemDePedido> listar() {
        return itemDePedidoDAO.listar();
    }

    public List<ItemDePedido> listarItensPorPedido(int id) {
        
        return itemDePedidoDAO.listarItensPorPedido(id);
    }
}
