package ro.uaic.info.documents.submission.documentssubmisson.controllers;

import ro.uaic.info.documents.submission.documentssubmisson.views.UserLoginView;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NavigationController implements Serializable {

    @Inject
    private UserLoginView loginView;

    private static final String DOCUMENT_PAGE_NAME = "document";
    private static final String LOGIN_PAGE_NAME = "login";
    private static final String ADMIN_PAGE_NAME = "admin";
    private static final String REGISTER_PAGE_NAME = "register";

    public String documentPage() {
        return DOCUMENT_PAGE_NAME;
    }

    public String loginPage() {
        return LOGIN_PAGE_NAME;
    }

    public String adminPage() {
        return ADMIN_PAGE_NAME;
    }

    public String registerPage() {
        return REGISTER_PAGE_NAME;
    }

    public String logoutPage() {
        loginView.logout();
        return LOGIN_PAGE_NAME;
    }
}
