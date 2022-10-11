package ro.uaic.info.lab2.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.uaic.info.lab2.model.PermutationGenerator;

/**
 * PermutationsServlet
 */
@WebServlet(description = "A web application to display all the letter permutations of a given word inside a HTML table", urlPatterns = {
		"/permutations" })
public class PermutationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PermutationsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String word = request.getParameter("word");
		String size = request.getParameter("size");
		System.out.println("call doGet(); word = " + word + "; size = " + size);

		if (word != null && !word.isEmpty()) {
			word = word.trim();
			size = size.trim();

			CharSequence charSequence = word.subSequence(0, word.length());
			List<Character> chars = charSequence.chars().mapToObj(c -> (char) c).sorted().collect(Collectors.toList());

			List<String> permutationsList = new PermutationGenerator(Integer.parseInt(size), chars).getPermutations();

			request.setAttribute("permutations", permutationsList);
			response.setContentType("text/html");
			getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/input.jsp").forward(request, response);
		}
	}
}
