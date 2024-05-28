package com.restaurante.rest.controller;

import com.restaurante.rest.model.dao.ItemDePedidoDAO;
import com.restaurante.rest.model.dao.PedidoDAO;
import com.restaurante.rest.model.entidade.Pedido;


import java.util.List;

public class PedidoController {
    private PedidoDAO pedidoDAO;
   
    
    public PedidoController(PedidoDAO pedidoDAO,ItemDePedidoDAO itemPedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    public void inserir(Pedido pedido) {
        pedidoDAO.inserir(pedido);
    }

    public List<Pedido> listar() {
        return pedidoDAO.listar();
    }

    public void excluir(int id) {
        pedidoDAO.excluir(id);
    }

    public void atualizar(Pedido pedido) {
        pedidoDAO.atualizar(pedido);
    }

    public Pedido buscarPorId(int idAtualizar) {
        return pedidoDAO.buscarPorId(idAtualizar);
    }
    
}
