package ro.uaic.info.documents.submission.documentssubmisson.services.impl.time;

import ro.uaic.info.documents.submission.documentssubmisson.services.TimeFrameService;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.time.LocalDateTime;

@ApplicationScoped
public class TimeFrameServiceImpl implements TimeFrameService, Serializable {
    @Override
    public Boolean get() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int hour = localDateTime.getHour();
        return hour >= START_HOUR && hour < END_HOUR;
    }
}
