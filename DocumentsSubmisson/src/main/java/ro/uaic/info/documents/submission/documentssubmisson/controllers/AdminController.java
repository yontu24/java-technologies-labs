package ro.uaic.info.documents.submission.documentssubmisson.controllers;

import ro.uaic.info.documents.submission.documentssubmisson.models.Document;
import ro.uaic.info.documents.submission.documentssubmisson.views.DocumentView;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AdminController {
    public List<Document> getAllUploadedDocuments() {
        List<Document> documentList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DocumentView.SUBMISSION_FILE_PATH))) {
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split("\\|");

                Document document = new Document();
                document.setPath(tokens[0]);
                document.setId(tokens[1]);
                document.setAuthor(tokens[2]);
                document.setDate(tokens[3]);
                documentList.add(document);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return documentList;
    }

    public String getDocumentContent(Document document) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(document.getPath()))) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
