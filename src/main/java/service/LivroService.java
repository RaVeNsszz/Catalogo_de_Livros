package main.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.model.Livro;
import main.java.repository.LivroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro salvarLivro(Livro livro) {
        return repository.save(livro);
    }

    public List<Livro> listarLivros() {
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Livro> buscarPorIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public void excluirLivro(Long id) {
        repository.deleteById(id);
    }
}