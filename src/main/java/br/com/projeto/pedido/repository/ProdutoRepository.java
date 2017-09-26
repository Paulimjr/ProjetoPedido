package br.com.projeto.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projeto.pedido.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
