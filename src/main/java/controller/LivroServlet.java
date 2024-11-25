package main.java.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import main.java.model.Livro;
import main.java.repository.LivroRepositoryImpl;
import main.java.service.LivroService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private LivroService livroService;

	@Override
    public void init() throws ServletException {
        super.init();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("catalogo_livros");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        LivroRepositoryImpl livroRepository = new LivroRepositoryImpl(entityManager);
        livroService = new LivroService(livroRepository);
    }
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Livro> livros = livroService.listarLivros();
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println(livrosToJson(livros));
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Criar um novo livro
		String titulo = req.getParameter("titulo");
		String autor = req.getParameter("autor");
		String genero = req.getParameter("genero");
		String isbn = req.getParameter("isbn");

		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setAutor(autor);
		livro.setGenero(genero);
		livro.setIsbn(isbn);

		livroService.salvarLivro(livro);

		resp.setStatus(HttpServletResponse.SC_CREATED);
		resp.getWriter().println("Livro criado com sucesso!");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Excluir um livro pelo ID
		String idParam = req.getParameter("id");
		if (idParam != null) {
			Long id = Long.parseLong(idParam);
			livroService.excluirLivro(id);
			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().println("ID do livro é obrigatório!");
		}
	}

	private String livrosToJson(List<Livro> livros) {
        StringBuilder json = new StringBuilder("[");
        for (Livro livro : livros) {
            json.append("{")
                .append("\"id\":").append(livro.getId()).append(",")
                .append("\"titulo\":\"").append(livro.getTitulo()).append("\",")
                .append("\"autor\":\"").append(livro.getAutor()).append("\",")
                .append("\"genero\":\"").append(livro.getGenero()).append("\",")
                .append("\"isbn\":\"").append(livro.getIsbn()).append("\"")
                .append("},");
        }
        if (json.length() > 1) json.deleteCharAt(json.length() - 1); // Remove a última vírgula
        json.append("]");
        return json.toString();
    }

    @Override
    public void destroy() {
        // Liberação de recursos
        livroService = null;
    }
}
