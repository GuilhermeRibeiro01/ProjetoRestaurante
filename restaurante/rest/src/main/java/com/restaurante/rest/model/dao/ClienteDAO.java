package com.restaurante.rest.model.dao;

import com.restaurante.rest.DbConfig;
import com.restaurante.rest.model.entidade.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements InterfaceDAO<Cliente> {

  private Connection con;

  public ClienteDAO() throws SQLException {
    this.con = DbConfig.getConnection();
  }

  @Override
  public void inserir(Cliente cliente) {
    String sql = "INSERT INTO Cliente(Nome) VALUES(?)";
    try (
      PreparedStatement pstmt = con.prepareStatement(
        sql,
        Statement.RETURN_GENERATED_KEYS
      )
    ) {
      pstmt.setString(1, cliente.getNome());

      int affectedRows = pstmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException(
          "Falha ao inserir cliente, nenhuma linha afetada."
        );
      }

      try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          cliente.setId(generatedKeys.getInt(1));
        } else {
          throw new SQLException("Falha ao obter o ID gerado para o cliente.");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void atualizar(Cliente cliente) {
    String sql = "UPDATE Cliente SET nome = ? WHERE id_cliente = ?";

    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, cliente.getNome());
      pstmt.setInt(2, cliente.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void excluir(int id) {
    Cliente cliente = buscarPorId(id);
    if (cliente == null) {
      System.out.println("Cliente com ID " + id + " não encontrado.");
      return;
    }

    // Remover os pedidos associados ao cliente
    String sqlExcluirPedidos = "DELETE FROM Pedido WHERE id_clientefk = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sqlExcluirPedidos)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    String sqlExcluirCliente = "DELETE FROM Cliente WHERE id_cliente = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sqlExcluirCliente)) {
      pstmt.setInt(1, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Cliente> listar() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM Cliente";
    try (Statement stmt = con.createStatement()) {
      try (ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
          int id = rs.getInt("id_cliente");
          String nome = rs.getString("nome");
          clientes.add(new Cliente(id, nome));
        }
        if (clientes.isEmpty()) {
          System.out.println("Nenhum cliente encontrado.");
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return clientes;
  }

  public Cliente buscarPorId(int id) {
    String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          String nome = rs.getString("nome");
          return new Cliente(id, nome);
        } else {
          System.out.println("Cliente com ID " + id + " não encontrado.");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
