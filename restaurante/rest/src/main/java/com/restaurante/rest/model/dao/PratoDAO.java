package com.restaurante.rest.model.dao;

import com.restaurante.rest.DbConfig;
import com.restaurante.rest.model.entidade.Prato;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PratoDAO implements InterfaceDAO<Prato> {

  private Connection con;

  public PratoDAO() throws SQLException {
    this.con = DbConfig.getConnection();
  }

  @Override
  public void inserir(Prato entidade) {
    String sql = "INSERT INTO Prato(nome, descricao, preco) VALUES(?, ?, ?)";
    try (
      PreparedStatement pstmt = con.prepareStatement(
        sql,
        Statement.RETURN_GENERATED_KEYS
      )
    ) {
      pstmt.setString(1, entidade.getNome());
      pstmt.setString(2, entidade.getDescricao());
      pstmt.setDouble(3, entidade.getPreco());
      pstmt.executeUpdate();

      ResultSet generatedKeys = pstmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        entidade.setId(generatedKeys.getInt(1));
      } else {
        throw new SQLException("Falha ao obter o ID gerado para o prato.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void excluir(int id) {
    String sql = "DELETE FROM Prato WHERE id_prato = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        System.out.println("Prato com ID " + id + " não encontrado.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void atualizar(Prato prato) {
    String sql =
      "UPDATE Prato SET nome = ?, descricao = ?, preco = ? WHERE id_prato = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, prato.getNome());
      pstmt.setString(2, prato.getDescricao());
      pstmt.setDouble(3, prato.getPreco());
      pstmt.setInt(4, prato.getId());
      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        System.out.println("Prato com ID " + prato.getId() + " não encontrado.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Prato> listar() {
    List<Prato> pratos = new ArrayList<>();
    String sql = "SELECT * FROM Prato";
    try (Statement stmt = con.createStatement()) {
      try (ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
          int id_prato = rs.getInt("id_prato");
          String nome = rs.getString("nome");
          String descricao = rs.getString("descricao");
          double preco = rs.getDouble("preco");

          Prato prato = new Prato(nome, descricao, preco);
          prato.setId(id_prato);

          pratos.add(prato);
        }
        if (pratos.isEmpty()) {
          System.out.println("Nenhum prato encontrado.");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return pratos;
  }

  public Prato buscarPorId(int idPrato) {
    String sql = "SELECT * FROM Prato WHERE id_prato = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setInt(1, idPrato);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          String nome = rs.getString("nome");
          String descricao = rs.getString("descricao");
          double preco = rs.getDouble("preco");
          Prato prato = new Prato(nome, descricao, preco);
          prato.setId(idPrato);

          return prato;
        } else {
          System.out.println("Prato com ID " + idPrato + " não encontrado.");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
