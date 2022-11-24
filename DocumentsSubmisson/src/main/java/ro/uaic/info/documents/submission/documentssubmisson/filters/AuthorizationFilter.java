package ro.uaic.info.documents.submission.documentssubmisson.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            HttpSession ses = servletRequest.getSession(false);

            String reqURI = servletRequest.getRequestURI();
            if (reqURI.contains("/login.xhtml")
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.contains("/public/")
                    || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else
                servletResponse.sendRedirect(servletRequest.getContextPath() + "/pages/login.xhtml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
