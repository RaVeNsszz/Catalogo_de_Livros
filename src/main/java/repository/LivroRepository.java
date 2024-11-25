package main.java.repository;

import main.java.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Consulta personalizada para buscar por ISBN
    Optional<Livro> findByIsbn(String isbn);
}