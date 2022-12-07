package ro.uaic.info.documents.submission.documentssubmisson.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Inherited
@Retention(RUNTIME)
@Target({METHOD, TYPE, PARAMETER, FIELD})
public @interface Watching {
}
