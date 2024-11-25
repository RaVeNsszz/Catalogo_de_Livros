package main.java.repository;

import main.java.model.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepository {
    Livro save(Livro livro);                     // Salvar ou atualizar um livro
    Optional<Livro> findById(Long id);           // Buscar livro por ID
    Optional<Livro> findByIsbn(String isbn);     // Buscar livro por ISBN
    List<Livro> findAll();                       // Listar todos os livros
    void deleteById(Long id);                    // Excluir livro por ID
}