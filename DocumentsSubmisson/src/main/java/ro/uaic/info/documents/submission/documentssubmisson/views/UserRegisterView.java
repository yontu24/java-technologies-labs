package ro.uaic.info.documents.submission.documentssubmisson.views;

import ro.uaic.info.documents.submission.documentssubmisson.controllers.RegisterController;
import ro.uaic.info.documents.submission.documentssubmisson.models.User;
import ro.uaic.info.documents.submission.documentssubmisson.models.UserRole;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserRegisterView implements Serializable {
    private User user;
    private String role;

    @Inject
    private RegisterController registerController;

    @PostConstruct
    public void init() {
        user = new User();
        role = UserRole.AUTHOR.name();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void register() {
        String username = user.getName();
        String password = user.getPassword();
        boolean existsUser = registerController.isUserRegistered(user);

        if (username != null && password != null) {
            if (!existsUser) {
                registerController.registerUser(user);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Registered successfully.",
                                "You have been registered successfully."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "User already exists.",
                                "Please login or try new credentials."));
            }
        }
    }

    public void setUserRole() {
        if (role != null) {
            user.setRole(UserRole.valueOf(role));
        }
    }
}
