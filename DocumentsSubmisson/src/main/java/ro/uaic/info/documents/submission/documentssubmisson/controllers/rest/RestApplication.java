package ro.uaic.info.documents.submission.documentssubmisson.controllers.rest;

import ro.uaic.info.documents.submission.documentssubmisson.controllers.rest.resources.SubmissionResource;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
@ApplicationScoped
public class RestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(SubmissionResource.class);
        return s;
    }
}
