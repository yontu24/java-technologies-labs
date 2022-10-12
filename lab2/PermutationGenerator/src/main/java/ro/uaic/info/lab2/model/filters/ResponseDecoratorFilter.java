package ro.uaic.info.lab2.model.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@WebFilter(servletNames = "PermutationsServlet", urlPatterns = { "/*" })
public class ResponseDecoratorFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Initialize response decorator filter...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SimpleResponseWrapper wrapper = new SimpleResponseWrapper((HttpServletResponse) response);
		chain.doFilter(request, wrapper);

		String content = wrapper.toString();
		PrintWriter out = response.getWriter();

		String sizeParam = request.getParameter("size");
		String wordParam = request.getParameter("word");

		if (wordParam != null && sizeParam != null) {
			StringBuilder builder = new StringBuilder();
			String beforeH1 = content.substring(0, content.indexOf("Permutations"));
			String afterH1 = content.substring(content.indexOf("Permutations"), content.indexOf("</h1>"));
			String endHtml = content.substring(content.indexOf("</h1>"), content.length() - 1);

			builder.append(beforeH1);
			builder.append("<font color='purple'>");
			builder.append(afterH1);
			builder.append("</font>");
			builder.append(endHtml);

			// override HTML content
			content = builder.toString();
			builder = new StringBuilder();
			String beforeEndTable = content.substring(0, content.indexOf("</body>"));
			String afterEndTable = content.substring(content.indexOf("</body>"), content.length() - 1);

			builder.append(beforeEndTable);
			builder.append("<p><font color='").append(Integer.parseInt(sizeParam) % 2 == 0 ? "aquablue" : "red")
					.append("'>Done</font></p>");
			builder.append(afterEndTable);

			content = builder.toString();
		}

		out.write(content);
		out.close();
	}

	@Override
	public void destroy() {

	}

	private static class SimpleResponseWrapper extends HttpServletResponseWrapper {
		private final StringWriter output;

		public SimpleResponseWrapper(HttpServletResponse response) {
			super(response);
			output = new StringWriter();
		}

		@Override
		public PrintWriter getWriter() {
			// Hide the original writer
			return new PrintWriter(output);
		}

		@Override
		public String toString() {
			return output.toString();
		}
	}
}
