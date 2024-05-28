package com.restaurante.rest.view;

import com.restaurante.rest.controller.PratoController;
import com.restaurante.rest.model.entidade.Prato;
import java.util.List;
import java.util.Scanner;

public class MenuPratoView {

  private PratoController pratoController;
  private Scanner scanner;

  public MenuPratoView(PratoController pratoController, Scanner scanner) {
    this.pratoController = pratoController;
    this.scanner = scanner;
  }

  public void iniciar() {
    while (true) {
      System.out.println("\n---------------------------");
      System.out.println("## GERENCIAR PRATOS ##\n");
      System.out.println("1. Inserir Prato");
      System.out.println("2. Listar Pratos");
      System.out.println("3. Excluir Prato");
      System.out.println("4. Atualizar Prato");
      System.out.println("5. Voltar ao menu inicial\n");
      System.out.print("Digite a opção desejada: ");
      int opcao = scanner.nextInt();
      scanner.nextLine();
      System.out.println("\n");
      switch (opcao) {
        case 1:
          System.out.println("Digite o nome do prato:");
          String nomePrato = scanner.nextLine();
          System.out.println("Digite o preço do prato:");
          double preco = scanner.nextDouble();
          scanner.nextLine();
          System.out.println("Digite a descrição do prato:");
          String descricao = scanner.nextLine();

          Prato prato = new Prato(nomePrato, descricao, preco);
          pratoController.inserir(prato);

          break;
        case 2:
          List<Prato> pratos = pratoController.listar();
          for (Prato pratoLista : pratos) {
            System.out.println(
              "ID: " +
              pratoLista.getId() +
              ", Nome: " +
              pratoLista.getNome() +
              ", Preço: " +
              pratoLista.getPreco() +
              ", Descrição: " +
              pratoLista.getDescricao()
            );
          }

          break;
        case 3:
          System.out.println("Digite o id do prato:");
          int id = scanner.nextInt();
          scanner.nextLine();
          pratoController.excluir(id);
          break;
        case 4:
          System.out.println("Digite o id do prato:");
          int idAtualizar = scanner.nextInt();
          scanner.nextLine();

          Prato pratoExistente = pratoController.buscarPorId(idAtualizar);
          if (pratoExistente == null) {
            System.out.println("Prato não encontrado.");
            break;
          }

          System.out.println("Digite o novo nome do prato:");
          String novoNome = scanner.nextLine();
          System.out.println("Digite o novo preço do prato:");
          double novoPreco = scanner.nextDouble();
          scanner.nextLine();
          System.out.println("Digite a nova descrição do prato:");
          String novaDescricao = scanner.nextLine();

          pratoExistente.setNome(novoNome);
          pratoExistente.setPreco(novoPreco);
          pratoExistente.setDescricao(novaDescricao);
          pratoController.atualizar(pratoExistente);
          break;
        case 5:
          return;
        default:
          System.out.println("Opção inválida!");
      }
    }
  }
}
