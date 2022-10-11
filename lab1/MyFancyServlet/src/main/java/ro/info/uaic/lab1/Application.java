package ro.info.uaic.lab1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.info.uaic.lab1.util.PermutationGenerator;

@WebServlet(name = "ProccessWord", urlPatterns = { "/word" })
public class Application extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Application() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String word = request.getParameter("word").trim();
		String size = request.getParameter("size").trim();

		System.out.println("call doGet(); name = " + word + "; size = " + size);

		CharSequence charSequence = word.subSequence(0, word.length());
		List<Character> chars = charSequence.chars().mapToObj(c -> (char) c).sorted().collect(Collectors.toList());

		PermutationGenerator generator = new PermutationGenerator(Integer.parseInt(size), chars);
		List<String> permutationsList = generator.getPermutations();

		response.setContentType("text/html");

		PrintWriter out = new PrintWriter(response.getWriter());
		out.println("<html><body><h1>" + chars + "</h1>");
		out.println(permutationsList);
		out.println("</body></html>");
		out.close();
	}
}
