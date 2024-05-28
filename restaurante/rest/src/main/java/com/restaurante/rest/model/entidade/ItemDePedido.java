package com.restaurante.rest.model.entidade;

public class ItemDePedido {
    private int idItemPedido;
    private int quantidade;
    private int idPedidoFK;
    private int idPratoFK; 

    public ItemDePedido(int idItemPedido, int quantidade, int idPedidoFK, int idPratoFK) {
        this.idItemPedido = idItemPedido;
        this.quantidade = quantidade;
        this.idPedidoFK = idPedidoFK;
        this.idPratoFK = idPratoFK;
    }
    public ItemDePedido( int quantidade, int idPedidoFK, int idPratoFK) {
        
        this.quantidade = quantidade;
        this.idPedidoFK = idPedidoFK;
        this.idPratoFK = idPratoFK;
    }

    
    public int getId() {
        return idItemPedido;
    }

    public void setId(int idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdPedidoFK() {
        return idPedidoFK;
    }

    public void setIdPedidoFK(int idPedidoFK) {
        this.idPedidoFK = idPedidoFK;
    }

    public int getIdPratoFK() {
        return idPratoFK;
    }

    public void setIdPratoFK(int idPratoFK) {
        this.idPratoFK = idPratoFK;
    }
}
