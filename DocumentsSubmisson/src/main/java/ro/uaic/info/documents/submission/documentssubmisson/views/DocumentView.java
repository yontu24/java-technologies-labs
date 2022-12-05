package ro.uaic.info.documents.submission.documentssubmisson.views;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFilesWrapper;
import org.primefaces.shaded.commons.io.FilenameUtils;
import ro.uaic.info.documents.submission.documentssubmisson.models.User;
import ro.uaic.info.documents.submission.documentssubmisson.util.session.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Named
@SessionScoped
public class DocumentView implements Serializable {
    private User user;
    private UploadedFile uploadedFile;

    public static final String UPLOAD_DIRECTORY_PATH = "D:\\Projects\\upload";
    public static final String SUBMISSION_FILE_PATH = "D:\\Projects\\upload\\submission.txt";

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
        if (uploadedFile != null) {
            FacesMessage message = new FacesMessage("Successful", uploadedFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            saveContentOnDisk();
        }
    }

    private void saveSubmissionByFile(File file) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(SUBMISSION_FILE_PATH, true)))) {
            String[] tokens = file.getName().split("-");
            String uniqueId = tokens[tokens.length - 1].split("\\.")[0];

            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = localDate.format(formatter);

            out.write(file + "|" + uniqueId + "|" + user.getName() + "|" + formattedDate + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Submission `" + "" + "` has been saved.");
    }

    private void saveContentOnDisk() throws IOException {
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
}
