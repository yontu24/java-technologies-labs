package ro.uaic.info.documents.submission.documentssubmisson.util.session;

import ro.uaic.info.documents.submission.documentssubmisson.models.User;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static boolean isUserLoggedIn() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        return session.getAttribute("user") != null;
    }

    public static User getUser() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        return (User) session.getAttribute("user");
    }
}
