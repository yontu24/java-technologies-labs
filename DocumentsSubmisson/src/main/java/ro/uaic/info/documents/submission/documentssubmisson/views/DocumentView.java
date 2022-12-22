package ro.uaic.info.documents.submission.documentssubmisson.views;

import org.primefaces.model.file.UploadedFile;
import ro.uaic.info.documents.submission.documentssubmisson.annotations.Uploading;
import ro.uaic.info.documents.submission.documentssubmisson.controllers.UploadController;
import ro.uaic.info.documents.submission.documentssubmisson.models.User;
import ro.uaic.info.documents.submission.documentssubmisson.util.events.upload.UploadingEvent;
import ro.uaic.info.documents.submission.documentssubmisson.util.session.SessionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
@RolesAllowed({"author", "reviewer"})
public class DocumentView implements Serializable {
    private User user;
    private UploadedFile uploadedFile;

    @Inject
    private UploadController uploadController;

    @Inject
    @Uploading
    private Event<UploadingEvent> uploadingEvent;

    @PostConstruct
    public void init() {
        user = (User) SessionUtils.getSession().getAttribute("user");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void upload() throws IOException {
        boolean success;
        FacesMessage message;

        if (uploadedFile != null) {
            success = uploadController.upload(uploadedFile);

            UploadingEvent uploadingEvent = new UploadingEvent();
            uploadingEvent.setSuccess(success);
            uploadingEvent.setUploadedFile(uploadedFile);

            this.uploadingEvent.fire(uploadingEvent);

            if (success)
                message = new FacesMessage("Successful", uploadedFile.getFileName() + " is uploaded.");
            else
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Failed", "Submission are allowed only between 10AM and 16PM.");
        } else
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "File is missing. Submit a valid file.");

        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
