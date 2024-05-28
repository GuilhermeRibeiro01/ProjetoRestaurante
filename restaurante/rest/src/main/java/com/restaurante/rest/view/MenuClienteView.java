package com.restaurante.rest.view;

import com.restaurante.rest.controller.ClienteController;
import com.restaurante.rest.model.entidade.Cliente;
import java.util.List;
import java.util.Scanner;

public class MenuClienteView {

  private ClienteController clienteController;
  private Scanner scanner;

  public MenuClienteView(ClienteController clienteController, Scanner scanner) {
    this.clienteController = clienteController;
    this.scanner = scanner;
  }

  public void iniciar() {
    while (true) {
      System.out.println("\n---------------------------");
      System.out.println("## GERENCIAR CLIENTES ##\n");
      System.out.println("1. Inserir Cliente");
      System.out.println("2. Listar Clientes");
      System.out.println("3. Excluir Cliente");
      System.out.println("4. Atualizar Cliente");
      System.out.println("5. Voltar ao menu inicial\n");
      System.out.print("Digite a opção desejada:");
      int opcao = scanner.nextInt();
      scanner.nextLine();
      System.out.println("\n");
      switch (opcao) {
        case 1:
          System.out.println("Digite o nome do cliente:");
          String nome = scanner.nextLine();
          clienteController.inserir(new Cliente(nome));
          break;
        case 2:
        List<Cliente> clientes = clienteController.listar();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente registrado.");
        } else {
            System.out.println("CLIENTES:");
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nome: " + cliente.getNome());
            }
        }
        
          break;
        case 3:
          System.out.println("Digite o id do cliente:");
          int id = scanner.nextInt();
          clienteController.excluir(id);
          break;
        case 4:
          System.out.println("Digite o id do cliente:");
          int idAtualizar = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Digite o novo nome do cliente:");
          String novoNome = scanner.nextLine();
          Cliente cliente = new Cliente(novoNome);
          cliente.setId(idAtualizar);
          clienteController.atualizar(cliente);
          break;
        case 5:
          return;
        default:
          System.out.println("Opção inválida!");
      }
    }
  }
}
