package com.open.portal.service.log;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.portal.api.exception.http.NoContentException;
import com.open.portal.domain.log.Log;
import com.open.portal.repository.LogRepository;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public Log create(Log log) {
        log.setDateTime(LocalDateTime.now());

        return logRepository.save(log);
    }

    public Log readById(Integer idLog) {
        return logRepository.findById(idLog).orElseThrow(() -> new NoContentException("Log not found."));
    }

    public List<Log> read() {
        List<Log> logs = logRepository.findAll();
        logs.stream().findFirst().orElseThrow(() -> new NoContentException("There are no logs registered."));

        return logs;
    }
}
