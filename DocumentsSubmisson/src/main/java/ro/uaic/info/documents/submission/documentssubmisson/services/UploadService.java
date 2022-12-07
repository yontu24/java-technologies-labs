package ro.uaic.info.documents.submission.documentssubmisson.services;

import org.primefaces.model.file.UploadedFile;

import java.io.IOException;
import java.io.Serializable;

public interface UploadService extends Serializable {
    void upload(UploadedFile uploadedFile) throws IOException;
}
