package ro.uaic.info.lab2.model.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(servletNames = "PermutationsServlet", urlPatterns = { "/*" })
public class LoggingFilter implements Filter {

	private FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		System.out.println("Initialize logging filter...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			beforeRequest(request, response);
			chain.doFilter(request, response);
		} finally {
			afterRequest(request, response);
		}
	}

	@Override
	public void destroy() {
		filterConfig = null;
	}

	private void beforeRequest(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;

		StringBuilder builder = new StringBuilder();
		builder.append("Before request:\n");
		builder.append("> Character encoding: ").append(request.getCharacterEncoding()).append("\n");
		builder.append("> Protocol: ").append(request.getProtocol()).append("\n");
		builder.append("> Port: ").append(request.getServerPort()).append("\n");
		builder.append("> Method: ").append(req.getMethod()).append("\n");

		filterConfig.getServletContext().log(builder.toString());
	}

	private void afterRequest(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;

		StringBuilder builder = new StringBuilder();
		builder.append("After request:\n");
		builder.append("> Session ID: ").append(req.getSession().getId()).append("\n");
		builder.append("> Session attributes:\n");

		Enumeration<String> attributes = request.getAttributeNames();
		while (attributes.hasMoreElements()) {
			String attribute = attributes.nextElement();
			if (attribute != null)
				builder.append("> ").append(attribute).append(":").append(request.getAttribute(attribute));
		}
		filterConfig.getServletContext().log(builder.toString());
	}
}
