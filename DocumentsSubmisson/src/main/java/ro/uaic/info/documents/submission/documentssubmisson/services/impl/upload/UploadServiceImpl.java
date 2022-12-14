package ro.uaic.info.documents.submission.documentssubmisson.services.impl.upload;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import ro.uaic.info.documents.submission.documentssubmisson.services.UploadService;
import ro.uaic.info.documents.submission.documentssubmisson.util.session.SessionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UploadServiceImpl implements UploadService, Serializable {
    public static final String UPLOAD_DIRECTORY_PATH = "D:\\Projects\\upload";
    public static final String SUBMISSION_FILE_PATH = "D:\\Projects\\upload\\submission.txt";

    @Override
    public void upload(UploadedFile uploadedFile) throws IOException {
        saveContentOnDisk(uploadedFile);
    }

    private void saveContentOnDisk(UploadedFile uploadedFile) throws IOException {
        Path folder = Paths.get(UPLOAD_DIRECTORY_PATH);
        String filename = FilenameUtils.getBaseName(uploadedFile.getFileName());
        String extension = FilenameUtils.getExtension(uploadedFile.getFileName());
        Path file = Files.createTempFile(folder, filename + "-", "." + extension);

        try (InputStream input = uploadedFile.getInputStream()) {
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Uploaded file successfully saved in " + file.getFileName());

        saveSubmissionByFile(file.toFile());
    }

    private void saveSubmissionByFile(File file) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(SUBMISSION_FILE_PATH, true)))) {
            String[] tokens = file.getName().split("-");
            String uniqueId = tokens[tokens.length - 1].split("\\.")[0];

            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = localDate.format(formatter);

            out.write(file + "|" + uniqueId + "|" + SessionUtils.getUser().getName() + "|" + formattedDate + "\n");
            System.out.println("Submission `" + uniqueId + "` has been saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
