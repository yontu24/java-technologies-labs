package ro.uaic.info.documents.submission.documentssubmisson.controllers;

import org.primefaces.model.file.UploadedFile;
import ro.uaic.info.documents.submission.documentssubmisson.services.TimeFrameService;
import ro.uaic.info.documents.submission.documentssubmisson.services.UploadService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class UploadController {
    @Inject
    private TimeFrameService timeFrameService;

    @Inject
    private UploadService uploadService;

    public boolean upload(UploadedFile uploadedFile) throws IOException {
        if (timeFrameService.get()) {
            uploadService.upload(uploadedFile);
            return true;
        }
        return false;
    }
}
