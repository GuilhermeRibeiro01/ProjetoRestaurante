package com.restaurante.rest.view;

import com.restaurante.rest.controller.ItemDePedidoController;
import com.restaurante.rest.controller.PedidoController;
import com.restaurante.rest.model.entidade.ItemDePedido;
import com.restaurante.rest.model.entidade.Pedido;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class MenuPedidoView {

  private PedidoController pedidoController;
  private ItemDePedidoController itemDePedidoController;
  private Scanner scanner;

  public MenuPedidoView(
    PedidoController pedidoController,
    Scanner scanner,
    ItemDePedidoController itemDePedidoController
  ) {
    this.pedidoController = pedidoController;
    this.scanner = scanner;
    this.itemDePedidoController = itemDePedidoController;
  }

  public void iniciar() {
    while (true) {
      System.out.println("\n---------------------------");
      System.out.println("## GERENCIAR PEDIDOS ##\n");
      System.out.println("1. Inserir Pedido");
      System.out.println("2. Listar Pedidos");
      System.out.println("3. Excluir Pedido");
      System.out.println("4. Atualizar Pedido");
      System.out.println("5. Voltar ao menu inicial\n");
      System.out.print("Digite a opção desejada: ");
      int opcao = scanner.nextInt();
      System.out.println("---------------------------\n");
      scanner.nextLine();

      switch (opcao) {
        case 1:
          System.out.println("Digite o ID do cliente:");
          int idCliente = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Digite o ID do prato selecionado:");
          int idPrato = scanner.nextInt();
          System.out.println("Digite o ID do funcionário:");
          int idFuncionario = scanner.nextInt();
          System.out.println("Digite a quantidade de Itens:");
          int quantidade = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Digite o status do pedido:");
          String status = scanner.nextLine();
          System.out.println("Digite a data do pedido (formato: dd/MM/yyyy):");
          String dataStr = scanner.nextLine();

          SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
          Date data = null;

          try {
            data = new Date(dateFormat.parse(dataStr).getTime());
          } catch (ParseException e) {
            System.out.println(
              "Formato de data inválido. Use o formato dd/MM/yyyy."
            );
            continue;
          }

          Pedido novoPedido = new Pedido(
            0,
            data,
            status,
            idCliente,
            idFuncionario,
            idPrato
          );
          pedidoController.inserir(novoPedido);
          int idPedidoGerado = novoPedido.getId();
          ItemDePedido itemDePedido = new ItemDePedido(
            quantidade,
            idPedidoGerado,
            idPrato
          );

          itemDePedidoController.inserir(itemDePedido);
          System.out.println("Pedido inserido com sucesso.");
          break;
        case 2:
          List<Pedido> pedidos = pedidoController.listar();
          for (Pedido pedido : pedidos) {
            System.out.println(
              "ID Pedido: " +
              pedido.getId() +
              ", Status: " +
              pedido.getStatus() +
              ", Data: " +
              pedido.getDataPedido() +
              ", ID Cliente: " +
              pedido.getId_cliente() +
              ", ID Funcionário: " +
              pedido.getId_funcionario() +
              ", ID Prato: " +
              pedido.getId_prato()
            );
          }

          break;
        case 3:
          System.out.println("Digite o id do pedido:");
          int id = scanner.nextInt();
          if (pedidoController.buscarPorId(id) == null) {
            System.out.println("Pedido não encontrado.");
          } else {
            pedidoController.excluir(id);
            System.out.println("Pedido excluído com sucesso.");
          }
          break;
        case 4:
          System.out.println("Digite o id do pedido:");
          int idAtualizar = scanner.nextInt();
          scanner.nextLine();

          Pedido pedidoExistente = pedidoController.buscarPorId(idAtualizar);
          if (pedidoExistente == null) {
            System.out.println("Pedido não encontrado.");
            break;
          }

          System.out.println("Digite o novo status do pedido:");
          String novoStatus = scanner.nextLine();

          Pedido pedidoAtualizado = new Pedido(
            idAtualizar,
            pedidoExistente.getDataPedido(),
            novoStatus,
            pedidoExistente.getId_cliente(),
            pedidoExistente.getId_funcionario(),
            pedidoExistente.getId_prato()
          );
          pedidoController.atualizar(pedidoAtualizado);
          break;
        case 5:
          return;
        default:
          System.out.println("Opção inválida!");
      }
    }
  }
}
