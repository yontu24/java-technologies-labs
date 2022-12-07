package ro.uaic.info.documents.submission.documentssubmisson.services;

import java.util.function.Supplier;

public interface TimeFrameService extends Supplier<Boolean> {
    int START_HOUR = 10;
    int END_HOUR = 20;
}
