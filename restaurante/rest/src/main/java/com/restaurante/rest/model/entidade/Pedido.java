package com.restaurante.rest.model.entidade;

import java.sql.Date;

public class Pedido {
    private int id;
    private Date dataPedido;
    private String status;
    private int id_cliente;
    private int id_funcionario;
    private int id_prato;  

    public Pedido(int id, Date dataPedido, String status, int id_cliente, int id_funcionario, int id_prato) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.status = status;
        this.id_cliente = id_cliente;
        this.id_funcionario = id_funcionario;
        this.id_prato = id_prato;  
    }
    public Pedido( Date dataPedido, String status, int id_cliente, int id_funcionario, int id_prato) {
      this.dataPedido = dataPedido;
      this.status = status;
      this.id_cliente = id_cliente;
      this.id_funcionario = id_funcionario;
      this.id_prato = id_prato;  
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getId_prato() {
        return id_prato; 
    }

    public void setId_prato(int id_prato) {
        this.id_prato = id_prato; 
    }
}
