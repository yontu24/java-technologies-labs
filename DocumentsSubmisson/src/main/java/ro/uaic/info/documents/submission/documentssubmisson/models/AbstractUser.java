package ro.uaic.info.documents.submission.documentssubmisson.models;

import java.io.Serializable;

public abstract class AbstractUser implements Serializable {
    protected UserRole role;
    private String name;
    private String password;
    private String sessionId;

    public AbstractUser() {
        this.role = UserRole.NO_ROLE;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
