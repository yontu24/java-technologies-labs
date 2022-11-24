package ro.uaic.info.documents.submission.documentssubmisson.models;

public class ReviewerUser extends AbstractUser {
    public ReviewerUser() {
        role = UserRole.REVIEWER;
    }
}
