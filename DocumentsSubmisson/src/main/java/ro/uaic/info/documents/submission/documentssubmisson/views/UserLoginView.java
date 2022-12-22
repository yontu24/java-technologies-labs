package ro.uaic.info.documents.submission.documentssubmisson.views;

import ro.uaic.info.documents.submission.documentssubmisson.controllers.NavigationController;
import ro.uaic.info.documents.submission.documentssubmisson.controllers.RegisterController;
import ro.uaic.info.documents.submission.documentssubmisson.annotations.Logged;
import ro.uaic.info.documents.submission.documentssubmisson.models.User;
import ro.uaic.info.documents.submission.documentssubmisson.util.session.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class UserLoginView implements Serializable {
    @Inject
    private NavigationController navigationController;

    @Inject
    private RegisterController registerController;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        user = new User();
    }

    @Logged
    public void login() throws IOException {
        String username = user.getName();
        String password = user.getPassword();
        FacesContext context = FacesContext.getCurrentInstance();

        boolean existsUser = registerController.isUserRegistered(user);

        if (isUserLoggedIn()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Session currently in use",
                    "You are already logged in. Please logout."));
        } else if (existsUser) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("user", user);

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Registered successfully.",
                            "You have been registered successfully."));

            if (username.equals("admin") && password.equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918"))
                context.getExternalContext().redirect(navigationController.adminPage() + ".xhtml");
            else
                context.getExternalContext().redirect(navigationController.documentPage() + ".xhtml");
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
        }
    }

    @Logged
    public void logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
    }

    public boolean isUserLoggedIn() {
        return SessionUtils.isUserLoggedIn();
    }
}
