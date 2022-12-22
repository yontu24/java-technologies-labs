package ro.uaic.info.documents.submission.documentssubmisson.filters;

import ro.uaic.info.documents.submission.documentssubmisson.models.User;
import ro.uaic.info.documents.submission.documentssubmisson.models.UserRole;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(filterName = "AuthorizationFilter", urlPatterns = { "*.xhtml" })
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

            User user = null;
            if (ses != null) {
                user = (User) ses.getAttribute("user");
            }

            if (reqURI.contains("/login.xhtml") || reqURI.contains("/register.xhtml")
                    || user != null && user.getRole() == UserRole.ADMIN && reqURI.contains("/admin.xhtml")
                    || user != null && reqURI.contains("/document.xhtml")
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
