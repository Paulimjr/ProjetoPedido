package br.com.projeto.pedido.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.pedido.entity.Categoria;
import br.com.projeto.pedido.entity.Produto;
/**
 * Repositorio para controlar dados de produto
 * 
 * @author paulo
 *
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

     /**
      * Buscar de produtos com paginação
      * 
      * @param nome
      * @param categorias
      * @param pageRequest
      * @return
      */
	 @Transactional(readOnly=true)
	 @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	 Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	 
}
