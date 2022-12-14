package ro.uaic.info.documents.submission.documentssubmisson.services;

import java.util.List;

public interface SubmissionService<T, E> {
    E getDocument(String id);
    void addDocument(T document);
    void replaceDocument(T document);
    void deleteDocument(String documentId);
    List<E> getDocumentsUploadedByUser(String userId);
    E convertToEntity(T t);
}
