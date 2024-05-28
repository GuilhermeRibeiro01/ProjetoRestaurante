package com.restaurante.rest.model.dao;

import com.restaurante.rest.DbConfig;
import com.restaurante.rest.model.entidade.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements InterfaceDAO<Pedido> {

    private Connection con;

    public PedidoDAO() throws SQLException {
        this.con = DbConfig.getConnection();
    }

    @Override
    public void inserir(Pedido pedido) {
        String sql =
          "INSERT INTO Pedido(data_pedido, status, id_clientefk, id_funcionariofk, id_pratofk) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDate(1, pedido.getDataPedido());
            pstmt.setString(2, pedido.getStatus());
            pstmt.setInt(3, pedido.getId_cliente());
            pstmt.setInt(4, pedido.getId_funcionario());
            pstmt.setInt(5, pedido.getId_prato());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idPedido = generatedKeys.getInt(1);
                    pedido.setId(idPedido);
                } else {
                    throw new SQLException(
                      "Falha ao obter o ID gerado para o pedido."
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Pedido com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Pedido pedido) {
        String sql =
          "UPDATE Pedido SET data_pedido = ?, status = ?, id_clientefk = ?, id_funcionariofk = ?, id_pratofk = ? WHERE id_pedido = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setDate(1, pedido.getDataPedido());
            pstmt.setString(2, pedido.getStatus());
            pstmt.setInt(3, pedido.getId_cliente());
            pstmt.setInt(4, pedido.getId_funcionario());
            pstmt.setInt(5, pedido.getId_prato());
            pstmt.setInt(6, pedido.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    int id_pedido = rs.getInt("id_pedido");
                    Date data_pedido = rs.getDate("data_pedido");
                    String status = rs.getString("status");
                    int id_cliente = rs.getInt("id_clientefk");
                    int id_funcionario = rs.getInt("id_funcionariofk");
                    int id_prato = rs.getInt("id_pratofk");

                    pedidos.add(
                      new Pedido(id_pedido, data_pedido, status, id_cliente, id_funcionario, id_prato)
                    );
                }
                if (pedidos.isEmpty()) {
                    System.out.println("Nenhum pedido encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public Pedido buscarPorId(int id) {
        Pedido pedido = null;
        String sql = "SELECT * FROM Pedido WHERE id_pedido = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Date data_pedido = rs.getDate("data_pedido");
                    String status = rs.getString("status");
                    int id_cliente = rs.getInt("id_clientefk");
                    int id_funcionario = rs.getInt("id_funcionariofk");
                    int id_prato = rs.getInt("id_pratofk");

                    pedido = new Pedido(id, data_pedido, status, id_cliente, id_funcionario, id_prato);
                } else {
                    System.out.println("Pedido com ID " + id + " não encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }
}
