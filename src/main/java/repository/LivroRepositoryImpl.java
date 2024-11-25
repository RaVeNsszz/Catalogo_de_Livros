package main.java.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.java.model.Livro;

import java.util.List;
import java.util.Optional;

public class LivroRepositoryImpl implements LivroRepository {

    private final EntityManager entityManager;

    public LivroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Livro save(Livro livro) {
        if (livro.getId() == null) {
            entityManager.persist(livro); // Inserir novo livro
        } else {
            entityManager.merge(livro);  // Atualizar livro existente
        }
        return livro;
    }

    @Override
    public Optional<Livro> findById(Long id) {
        Livro livro = entityManager.find(Livro.class, id);
        return Optional.ofNullable(livro);
    }

    @Override
    public Optional<Livro> findByIsbn(String isbn) {
        TypedQuery<Livro> query = entityManager.createQuery(
            "SELECT l FROM Livro l WHERE l.isbn = :isbn", Livro.class);
        query.setParameter("isbn", isbn);

        List<Livro> resultados = query.getResultList();
        return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
    }

    @Override
    public List<Livro> findAll() {
        TypedQuery<Livro> query = entityManager.createQuery(
            "SELECT l FROM Livro l", Livro.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Livro livro = entityManager.find(Livro.class, id);
        if (livro != null) {
            entityManager.remove(livro);
        }
    }
}