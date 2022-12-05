package ro.uaic.info.documents.submission.documentssubmisson.models;

public enum UserRole {
    NO_ROLE(0), ADMIN(1), AUTHOR(2), REVIEWER(3);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public static UserRole getById(int id) {
        for (UserRole role : values()) {
            if (role.id == id)
                return role;
        }
        return NO_ROLE;
    }
}
