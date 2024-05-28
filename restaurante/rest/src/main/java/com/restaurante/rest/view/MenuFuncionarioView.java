package com.restaurante.rest.view;

import com.restaurante.rest.controller.FuncionarioController;
import com.restaurante.rest.model.entidade.Funcionario;
import java.util.List;
import java.util.Scanner;

public class MenuFuncionarioView {

  private FuncionarioController funcionarioController;
  private Scanner scanner;

  public MenuFuncionarioView(
    FuncionarioController funcionarioController,
    Scanner scanner
  ) {
    this.funcionarioController = funcionarioController;
    this.scanner = scanner;
  }

  public void iniciar() {
    while (true) {
      System.out.println("\n---------------------------");
      System.out.println("## GERENCIAR FUNCIONÁRIOS ##\n");
      System.out.println("1. Inserir Funcionário");
      System.out.println("2. Listar Funcionários");
      System.out.println("3. Excluir Funcionário");
      System.out.println("4. Atualizar Funcionário");
      System.out.println("5. Voltar ao menu inicial\n");
      System.out.print("Digite a opção desejada: ");
      int opcao = scanner.nextInt();
      scanner.nextLine();
      System.out.println("\n");
      switch (opcao) {
        case 1:
          System.out.println("Digite o nome do funcionário:");
          String nome = scanner.nextLine();
          System.out.println("Digite o cargo do funcionário:");
          String cargo = scanner.nextLine();
          funcionarioController.inserir(new Funcionario(nome, cargo));
          break;
        case 2:
          List<Funcionario> funcionarios = funcionarioController.listar();
          for (Funcionario funcionario : funcionarios) {
            System.out.println(
              "ID: " +
              funcionario.getId() +
              ", Nome: " +
              funcionario.getNome() +
              ", Cargo: " +
              funcionario.getCargo()
            );
          }

          break;
        case 3:
          System.out.println("Digite o id do funcionário:");
          int id = scanner.nextInt();
          funcionarioController.excluir(id);
          break;
        case 4:
          System.out.println("Digite o id do funcionário:");
          int idAtualizar = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Digite o novo nome do funcionário:");
          String novoNome = scanner.nextLine();
          System.out.println("Digite o novo cargo do funcionário:");
          String novoCargo = scanner.nextLine();
          Funcionario funcionario = new Funcionario(novoNome, novoCargo);
          funcionario.setId(idAtualizar);
          funcionarioController.atualizar(funcionario);
          break;
        case 5:
          return;
        default:
          System.out.println("Opção inválida!");
      }
    }
  }
}
