package ro.uaic.info.documents.submission.documentssubmisson.util.events.upload;

import org.primefaces.model.file.UploadedFile;
import ro.uaic.info.documents.submission.documentssubmisson.annotations.Logged;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Logged
@SessionScoped
public class UploadingEvent implements Serializable {

    private UploadedFile uploadedFile;
    private boolean success;

    public UploadingEvent() {
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "UploadingEvent{" +
                "uploadedFile=" + uploadedFile +
                ", success=" + success +
                '}';
    }
}
