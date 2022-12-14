package ro.uaic.info.documents.submission.documentssubmisson.services.impl.submission;

import ro.uaic.info.documents.submission.documentssubmisson.entities.DocumentEntity;
import ro.uaic.info.documents.submission.documentssubmisson.models.Document;
import ro.uaic.info.documents.submission.documentssubmisson.repo.DocumentRepository;
import ro.uaic.info.documents.submission.documentssubmisson.services.SubmissionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RequestScoped
public class SubmissionServiceImpl implements SubmissionService<Document, DocumentEntity> {
    @Inject
    private DocumentRepository documentRepository;

    @Override
    public DocumentEntity getDocument(String id) {
        return documentRepository.get(id);
    }

    @Override
    public void addDocument(Document document) {
        documentRepository.save(this.convertToEntity(document));
    }

    @Override
    public void replaceDocument(Document document) {
        DocumentEntity documentEntity = convertToEntity(document);
        documentEntity.setDate(Date.valueOf(LocalDate.now()));
        documentRepository.update(documentEntity);
    }

    @Override
    public void deleteDocument(String documentId) {
        DocumentEntity documentEntity = getDocument(documentId);
        documentRepository.delete(documentEntity);
    }

    @Override
    public List<DocumentEntity> getDocumentsUploadedByUser(String userId) {
        return documentRepository.getData(userId);
    }

    @Override
    public DocumentEntity convertToEntity(Document document) {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setAuthor(document.getAuthor());
        documentEntity.setUniqueid(document.getId());
        documentEntity.setDate(Date.valueOf(document.getDate()));
        return documentEntity;
    }

}
