package ro.uaic.info.documents.submission.documentssubmisson.models;

public class AnnoymousUser extends AbstractUser {
    public AnnoymousUser() {
        role = UserRole.NO_ROLE;
    }
}
