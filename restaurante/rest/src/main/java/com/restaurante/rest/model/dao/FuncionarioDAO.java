package com.restaurante.rest.model.dao;

import com.restaurante.rest.DbConfig;
import com.restaurante.rest.model.entidade.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements InterfaceDAO<Funcionario> {

  private Connection con;

  public FuncionarioDAO() throws SQLException {
    this.con = DbConfig.getConnection();
  }

  @Override
  public void inserir(Funcionario funcionario) {
    String sql = "INSERT INTO Funcionario(Nome, Cargo) VALUES(?, ?)";
    try (
      PreparedStatement pstmt = con.prepareStatement(
        sql,
        Statement.RETURN_GENERATED_KEYS
      )
    ) {
      pstmt.setString(1, funcionario.getNome());
      pstmt.setString(2, funcionario.getCargo());

      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException(
          "Falha ao inserir funcionário, nenhuma linha afetada."
        );
      }

      try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          funcionario.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException(
            "Falha ao obter o ID gerado para o funcionário."
          );
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void atualizar(Funcionario funcionario) {
    String sql =
      "UPDATE Funcionario SET Nome = ?, Cargo = ? WHERE id_funcionario = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, funcionario.getNome());
      pstmt.setString(2, funcionario.getCargo());
      pstmt.setInt(3, funcionario.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void excluir(int id) {
    String sql = "DELETE FROM Funcionario WHERE id_funcionario = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        System.out.println("Funcionário com ID " + id + " não encontrado.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Funcionario> listar() {
    List<Funcionario> funcionarios = new ArrayList<>();
    String sql = "SELECT * FROM Funcionario";
    try (Statement stmt = con.createStatement()) {
      try (ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
          int id = rs.getInt("id_funcionario");
          String nome = rs.getString("Nome");
          String cargo = rs.getString("Cargo");
          funcionarios.add(new Funcionario(id, nome, cargo));
        }
        if (funcionarios.isEmpty()) {
          System.out.println("Nenhum funcionário encontrado.");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return funcionarios;
  }
}
