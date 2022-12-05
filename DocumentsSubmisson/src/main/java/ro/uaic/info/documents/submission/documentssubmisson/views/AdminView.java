package ro.uaic.info.documents.submission.documentssubmisson.views;

import ro.uaic.info.documents.submission.documentssubmisson.controllers.AdminController;
import ro.uaic.info.documents.submission.documentssubmisson.models.Document;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AdminView implements Serializable {

    private List<Document> documentList;
    private Document selectedDocument;
    private String documentContent;

    @Inject
    private AdminController adminController;

    @PostConstruct
    public void init() {
        documentList = adminController.getAllUploadedDocuments();
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }

    public Document getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public void onRowToggle() {
        documentContent = adminController.getDocumentContent(selectedDocument);
    }
}
