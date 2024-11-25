package main.java;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import main.java.model.Livro;
import main.java.service.LivroService;
import main.java.repository.LivroRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Configuração do EntityManagerFactory
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("catalogo_livros");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Instância do serviço
        LivroRepositoryImpl livroRepository = new LivroRepositoryImpl(entityManager);
        LivroService livroService = new LivroService(livroRepository);

        // 1. Criando e salvando um livro
        Livro livro1 = new Livro();
        livro1.setTitulo("O Senhor dos Anéis");
        livro1.setAutor("J.R.R. Tolkien");
        livro1.setGenero("Fantasia");
        livro1.setIsbn("978-0-345-33970-6");
        livro1.setDataPublicacao(LocalDate.of(1954, 7, 29));
        livro1.setPreco(new BigDecimal("49.90"));

        Livro livro2 = new Livro();
        livro2.setTitulo("1984");
        livro2.setAutor("George Orwell");
        livro2.setGenero("Ficção Distópica");
        livro2.setIsbn("978-0-452-28423-4");
        livro2.setDataPublicacao(LocalDate.of(1949, 6, 8));
        livro2.setPreco(new BigDecimal("39.90"));

        entityManager.getTransaction().begin();
        livroService.salvarLivro(livro1);
        livroService.salvarLivro(livro2);
        entityManager.getTransaction().commit();

        System.out.println("Livros salvos com sucesso!");

        // 2. Listando todos os livros
        System.out.println("\nLista de livros:");
        livroService.listarLivros().forEach(System.out::println);

        // 3. Buscando um livro pelo ISBN
        String isbnBusca = "978-0-452-28423-4";
        System.out.println("\nBuscando livro pelo ISBN: " + isbnBusca);
        Livro livroEncontrado = livroService.buscarPorIsbn(isbnBusca)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
        System.out.println("Livro encontrado: " + livroEncontrado);

        // 4. Atualizando um livro
        System.out.println("\nAtualizando o preço do livro...");
        livroEncontrado.setPreco(new BigDecimal("29.90"));
        entityManager.getTransaction().begin();
        livroService.salvarLivro(livroEncontrado);
        entityManager.getTransaction().commit();
        System.out.println("Livro atualizado: " + livroService.buscarPorId(livroEncontrado.getId()));

        // 5. Excluindo um livro
        System.out.println("\nExcluindo o livro com ID: " + livro2.getId());
        entityManager.getTransaction().begin();
        livroService.excluirLivro(livro2.getId());
        entityManager.getTransaction().commit();
        System.out.println("Livro excluído.");

        // 6. Listando os livros restantes
        System.out.println("\nLista de livros após exclusão:");
        livroService.listarLivros().forEach(System.out::println);

        // Fechando o EntityManager e o EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
