package ro.uaic.info.documents.submission.documentssubmisson.views;

import ro.uaic.info.documents.submission.documentssubmisson.controllers.NavigationController;
import ro.uaic.info.documents.submission.documentssubmisson.controllers.RegisterController;
import ro.uaic.info.documents.submission.documentssubmisson.models.AbstractUser;
import ro.uaic.info.documents.submission.documentssubmisson.models.AnnoymousUser;
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

    private AbstractUser user;
    private boolean registering = false;
    private String buttonName = "Login";

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public boolean isRegistering() {
        return registering;
    }

    public void setRegistering(boolean registering) {
        this.registering = registering;
    }

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(AbstractUser user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        user = new AnnoymousUser();
    }

    public void login() throws IOException {
        String username = user.getName();
        String password = user.getPassword();
        boolean existsUser = registerController.isUserRegistered(user);
        boolean success = false;

        if (username != null && password != null) {
            FacesContext context = FacesContext.getCurrentInstance();

            if (!existsUser && registering) {
                registerController.registerUser(user);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Registered successfully.",
                                "You have been registered successfully."));
                success = true;
            } else if (existsUser && !registering) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", username);
                success = true;

                if (username.equals("admin") && password.equals("admin"))
                    context.getExternalContext().redirect(navigationController.adminPage() + ".xhtml");
                else
                    context.getExternalContext().redirect(navigationController.documentPage() + ".xhtml");
            }
        }

        if (!success) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
        }
    }

    public void logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
    }

    public void checkAuth() {
        registering = !registering;

        if (!registering)
            buttonName = "Register";
    }

    public void setUserRole() {

    }
}
