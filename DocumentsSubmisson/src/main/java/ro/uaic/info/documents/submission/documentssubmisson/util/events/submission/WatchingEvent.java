package ro.uaic.info.documents.submission.documentssubmisson.util.events.submission;

import org.primefaces.model.file.UploadedFile;
import ro.uaic.info.documents.submission.documentssubmisson.annotations.Logged;
import ro.uaic.info.documents.submission.documentssubmisson.models.Document;
import ro.uaic.info.documents.submission.documentssubmisson.models.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Logged
@SessionScoped
public class WatchingEvent implements Serializable {
    private User user;
    private Document document;

    public WatchingEvent() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "SubmissionEvent{The following doc [" + document + "] has been submitted by [" + user.getName() + "]}";
    }
}
