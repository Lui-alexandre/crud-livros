package br.edu.umc.livros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.umc.livros.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Query Method: o Spring gera o SQL a partir do nome do método
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
