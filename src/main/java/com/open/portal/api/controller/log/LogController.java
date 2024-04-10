package com.open.portal.api.controller.log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.log.Log;
import com.open.portal.service.log.LogService;

import lombok.RequiredArgsConstructor;
import static org.springframework.http.ResponseEntity.*;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {
    @Autowired
    private final LogService logService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Log log) {
        Log newLog = logService.create(log);
        URI location = URI.create(String.format("/log/%d", newLog.getId()));

        return created(location).build();
    }
    
    @GetMapping
    public ResponseEntity<List<Log>> read() {
        List<Log> logs = logService.read();

        return ok(logs);
    }

    @GetMapping("/{idLog}")
    public ResponseEntity<Log> readById(@PathVariable Integer idLog) {
        Log log = logService.readById(idLog);

        return ok(log);
    }
}