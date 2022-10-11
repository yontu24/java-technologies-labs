package ro.uaic.info.lab2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Application
 */
@WebServlet(description = "A web application to display all the letter permutations of a given word inside a HTML table", urlPatterns = {
		"/Application", "/permutations" })
public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Application() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("word");
		String nextPage = (name != null && !name.isEmpty() ? "/result.jsp" : "/input.jsp");

		int[] values = { 1, 2, 3, 4, 5, 6 };
		request.setAttribute("array", values);

		getServletContext().getRequestDispatcher(nextPage).forward(request, response);
	}
}
