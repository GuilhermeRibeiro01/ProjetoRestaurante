package com.restaurante.rest.model.dao;

import com.restaurante.rest.DbConfig;
import com.restaurante.rest.model.entidade.ItemDePedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDePedidoDAO implements InterfaceDAO<ItemDePedido> {

    private Connection con;

    public ItemDePedidoDAO() throws SQLException {
        this.con = DbConfig.getConnection();
    }

    @Override
    public void inserir(ItemDePedido itemDePedido) {
        String sql = "INSERT INTO ItemDePedido(quantidade, id_pedidofk, id_pratofk) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, itemDePedido.getQuantidade());
            pstmt.setInt(2, itemDePedido.getIdPedidoFK());
            pstmt.setInt(3, itemDePedido.getIdPratoFK());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idItemDePedido = generatedKeys.getInt(1);
                    itemDePedido.setId(idItemDePedido);
                } else {
                    throw new SQLException(
                      "Falha ao obter o ID gerado para o item de pedido."
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM ItemDePedido WHERE id_item_de_pedido = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Item de pedido com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(ItemDePedido itemDePedido) {
        String sql =
          "UPDATE ItemDePedido SET quantidade = ?, id_pedidofk = ?, id_pratofk = ? WHERE id_item_de_pedido = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, itemDePedido.getQuantidade());
            pstmt.setInt(2, itemDePedido.getIdPedidoFK());
            pstmt.setInt(3, itemDePedido.getIdPratoFK());
            pstmt.setInt(4, itemDePedido.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ItemDePedido> listar() {
        List<ItemDePedido> itensDePedido = new ArrayList<>();
        String sql = "SELECT * FROM ItemDePedido";
        try (Statement stmt = con.createStatement()) {
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    int id_item_de_pedido = rs.getInt("id_item_de_pedido");
                    int quantidade = rs.getInt("quantidade");
                    int id_pedidofk = rs.getInt("id_pedidofk");
                    int id_pratofk = rs.getInt("id_pratofk");

                    itensDePedido.add(
                      new ItemDePedido(id_item_de_pedido, quantidade, id_pedidofk, id_pratofk)
                    );
                }
                if (itensDePedido.isEmpty()) {
                    System.out.println("Nenhum item de pedido encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itensDePedido;
    }

    public List<ItemDePedido> listarItensPorPedido(int idPedido) {
        List<ItemDePedido> itensDePedido = new ArrayList<>();
        String sql = "SELECT * FROM ItemDePedido WHERE id_pedidofk = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idPedido);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id_item_de_pedido = rs.getInt("id_item_de_pedido");
                    int quantidade = rs.getInt("quantidade");
                    int id_pedidofk = rs.getInt("id_pedidofk");
                    int id_pratofk = rs.getInt("id_pratofk");

                    itensDePedido.add(
                      new ItemDePedido(id_item_de_pedido, quantidade, id_pedidofk, id_pratofk)
                    );
                }
                if (itensDePedido.isEmpty()) {
                    System.out.println("Nenhum item de pedido encontrado para o pedido com ID " + idPedido);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itensDePedido;
    }
}
