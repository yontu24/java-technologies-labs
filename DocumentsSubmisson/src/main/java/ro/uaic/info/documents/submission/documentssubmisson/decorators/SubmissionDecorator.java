package ro.uaic.info.documents.submission.documentssubmisson.decorators;

import org.primefaces.model.file.UploadedFile;
import ro.uaic.info.documents.submission.documentssubmisson.services.UploadService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;

@Decorator
public class SubmissionDecorator implements UploadService, Serializable {
    @Inject
    @Delegate
    @Any
    private UploadService service;

    @Override
    public void upload(UploadedFile uploadedFile) throws IOException {
        System.out.println("PRE");
        service.upload(uploadedFile);
        System.out.println("POST");
    }
}
