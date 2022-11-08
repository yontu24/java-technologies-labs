package ro.uaic.info.app.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("navigationController")
@SessionScoped
public class NavigationController implements Serializable {

    private static final String VIEW_PAGE_NAME = "view";
    private static final String EDIT_PAGE_NAME = "edit";

    public String viewPage() {
        return VIEW_PAGE_NAME;
    }

    public String editPage() {
        return EDIT_PAGE_NAME;
    }
}
