package com.restaurante.rest.model.dao;

import java.util.List;

public interface InterfaceDAO<T> {
  void inserir(T entidade);
  List<T> listar();
  void excluir(int id);
  void atualizar(T entidade);
}
