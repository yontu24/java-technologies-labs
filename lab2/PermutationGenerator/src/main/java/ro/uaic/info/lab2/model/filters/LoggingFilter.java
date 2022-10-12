package ro.uaic.info.lab2.model.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = "PermutationsServlet", urlPatterns = { "/*" })
public class LoggingFilter implements Filter {

	private FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
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
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	private void beforeRequest(ServletRequest request, ServletResponse response) {
		filterConfig.getServletContext().log("Before");
	}

	private void afterRequest(ServletRequest request, ServletResponse response) {
		filterConfig.getServletContext().log("After");
	}
}
