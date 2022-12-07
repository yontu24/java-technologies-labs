package ro.uaic.info.documents.submission.documentssubmisson.util.events.upload;

import ro.uaic.info.documents.submission.documentssubmisson.annotations.Logged;
import ro.uaic.info.documents.submission.documentssubmisson.annotations.Uploading;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import java.io.Serializable;

@Logged
@SessionScoped
public class UploadingHandler implements Serializable {
    public void uploading(@Observes @Uploading UploadingEvent uploadingEvent) {
        System.out.println("@Observes has been triggered: " + uploadingEvent.toString());
    }
}
