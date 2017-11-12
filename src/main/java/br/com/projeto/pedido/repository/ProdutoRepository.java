package br.com.projeto.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.pedido.entity.Produto;
/**
 * Repositorio para controlar dados de produto
 * 
 * @author paulo
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
