package org.trl.service.impl;

import org.trl.service.DateTimeService;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@ApplicationScoped
public class DateTimeServiceImpl implements DateTimeService {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    @Override
    public Date toDate(LocalDateTime timestamp) {
        return Date.from(timestamp.atZone(ZoneId.systemDefault()).toInstant());
    }

}
