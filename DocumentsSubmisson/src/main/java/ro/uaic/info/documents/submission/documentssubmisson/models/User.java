package ro.uaic.info.documents.submission.documentssubmisson.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class User implements Serializable {
    private UserRole role;

    @Size(min = 3, max = 32, message = "Name must have between 3 and 32 chars")
    @NotEmpty(message = "Name cannot be null")
    private String name;

    @Size(min = 3, message = "Password must have at least 3 chars")
    @NotEmpty(message = "Password cannot be null")
    private String password;

    private String sessionId;

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
