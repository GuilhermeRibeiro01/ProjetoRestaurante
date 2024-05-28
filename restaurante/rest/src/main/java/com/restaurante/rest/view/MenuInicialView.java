package com.restaurante.rest.view;

import java.util.Scanner;

public class MenuInicialView {

  private Scanner scanner;
  private MenuClienteView menuClienteView;
  private MenuFuncionarioView menuFuncionarioView;
  private MenuPedidoView menuPedidoView;
  private MenuPratoView menuPratoView;

  public MenuInicialView(
    Scanner scanner,
    MenuClienteView menuClienteView,
    MenuFuncionarioView menuFuncionarioView,
    MenuPedidoView menuPedidoView,
    MenuPratoView menuPratoView
  ) {
    this.scanner = scanner;
    this.menuClienteView = menuClienteView;
    this.menuFuncionarioView = menuFuncionarioView;
    this.menuPedidoView = menuPedidoView;
    this.menuPratoView = menuPratoView;
  }

  public void iniciar() {
    while (true) {
      System.out.println("\n---------------------------");
      System.out.println("## MENU INICIAL RESTAURANTE ##\n");
      System.out.println("1. Gerenciar Clientes");
      System.out.println("2. Gerenciar Funcionários");
      System.out.println("3. Gerenciar Pedidos");
      System.out.println("4. Gerenciar Pratos");
      System.out.println("5. Sair\n");
      System.out.print("Digite a opção desejada: ");
      int opcao = scanner.nextInt();  
      scanner.nextLine();
      switch (opcao) {
        case 1:
          menuClienteView.iniciar();
          break;
        case 2:
          menuFuncionarioView.iniciar();
          break;
        case 3:
          menuPedidoView.iniciar();
          break;
        case 4:
          menuPratoView.iniciar();
          break;
        case 5:
          System.out.println("Saindo...");
          System.exit(0);
        default:
          System.out.println("Opção inválida. Por favor, tente novamente.");
      }
    }
  }
}
