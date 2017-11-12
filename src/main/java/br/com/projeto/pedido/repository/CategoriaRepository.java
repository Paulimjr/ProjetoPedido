package br.com.projeto.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.pedido.entity.Categoria;

/**
 * Repositorio para controlar dados de categoria
 * 
 * @author paulo
 *
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	

}
