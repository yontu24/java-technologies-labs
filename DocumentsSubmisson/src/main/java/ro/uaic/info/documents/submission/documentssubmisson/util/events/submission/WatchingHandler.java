package ro.uaic.info.documents.submission.documentssubmisson.util.events.submission;

import ro.uaic.info.documents.submission.documentssubmisson.annotations.Logged;
import ro.uaic.info.documents.submission.documentssubmisson.annotations.Watching;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import java.io.Serializable;
import java.time.LocalDateTime;

@Logged
@SessionScoped
public class WatchingHandler implements Serializable {
    public void watching(@Observes @Watching WatchingEvent watchingEvent) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Admin " + watchingEvent.getUser().getName() + " viewed document " + watchingEvent.getDocument().getId() + " at " + localDateTime);
    }
}
