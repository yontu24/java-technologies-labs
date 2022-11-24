package ro.uaic.info.documents.submission.documentssubmisson.models;

public class AdminUser extends AbstractUser {
    public AdminUser() {
        role = UserRole.ADMIN;
    }
}
