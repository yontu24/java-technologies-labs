package ro.uaic.info.documents.submission.documentssubmisson.models;

public class AuthorUser extends AbstractUser {
    public AuthorUser() {
        role = UserRole.AUTHOR;
    }
}
