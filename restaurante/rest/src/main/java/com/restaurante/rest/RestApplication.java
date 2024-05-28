package com.restaurante.rest;

import com.restaurante.rest.controller.ClienteController;
import com.restaurante.rest.controller.FuncionarioController;
import com.restaurante.rest.controller.ItemDePedidoController;
import com.restaurante.rest.controller.PedidoController;
import com.restaurante.rest.controller.PratoController;
import com.restaurante.rest.model.dao.ClienteDAO;
import com.restaurante.rest.model.dao.FuncionarioDAO;
import com.restaurante.rest.model.dao.ItemDePedidoDAO;
import com.restaurante.rest.model.dao.PedidoDAO;
import com.restaurante.rest.model.dao.PratoDAO;
import com.restaurante.rest.view.MenuClienteView;
import com.restaurante.rest.view.MenuFuncionarioView;
import com.restaurante.rest.view.MenuInicialView;
import com.restaurante.rest.view.MenuPedidoView;
import com.restaurante.rest.view.MenuPratoView;
import java.sql.SQLException;
import java.util.Scanner;

public class RestApplication {

  public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);
    

    // instâncias das classes DAOs
    ClienteDAO clienteDAO = new ClienteDAO();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    PedidoDAO pedidoDAO = new PedidoDAO();
    PratoDAO pratoDAO = new PratoDAO();
    ItemDePedidoDAO itemPedidoDAO = new ItemDePedidoDAO();

    // instâncias dos controllers
    ClienteController clienteController = new ClienteController(clienteDAO);
    FuncionarioController funcionarioController = new FuncionarioController(funcionarioDAO);
    PedidoController pedidoController = new PedidoController(pedidoDAO,itemPedidoDAO);
    PratoController pratoController = new PratoController(pratoDAO);
    ItemDePedidoController itemDePedidoController = new ItemDePedidoController(itemPedidoDAO);

    // instâncias dos menus
    MenuClienteView menuCliente = new MenuClienteView(
      clienteController,
      scanner
    );
    MenuFuncionarioView menuFuncionario = new MenuFuncionarioView(
      funcionarioController,
      scanner
    );
    MenuPedidoView menuPedido = new MenuPedidoView(pedidoController, scanner, itemDePedidoController);
    MenuPratoView menuPrato = new MenuPratoView(pratoController, scanner);

    // menu inicial
    MenuInicialView menuInicial = new MenuInicialView(
      scanner,
      menuCliente,
      menuFuncionario,
      menuPedido,
      menuPrato
    );

    // Iniciar sistema
    menuInicial.iniciar();
  }
}
