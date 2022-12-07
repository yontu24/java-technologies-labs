package ro.uaic.info.documents.submission.documentssubmisson.services.impl.upload;

import ro.uaic.info.documents.submission.documentssubmisson.annotations.Logged;
import ro.uaic.info.documents.submission.documentssubmisson.decorators.SubmissionDecorator;
import ro.uaic.info.documents.submission.documentssubmisson.services.UploadService;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

@SessionScoped
public class UploadServiceFactory implements Serializable {

    @Produces
    public UploadService getUploadService() {
        return new UploadServiceImpl();
//        return new SubmissionDecorator();
    }

    @Produces @Logged
    public UploadService getDecoratedUploadService() {
        return new SubmissionDecorator();
    }
}
