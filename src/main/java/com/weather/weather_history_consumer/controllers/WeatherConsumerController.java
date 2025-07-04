package com.weather.weather_history_consumer.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/history")
public class WeatherConsumerController {

    @Value("${history.file.path}")
    private String filePath;

    @GetMapping(value = "/download", produces = "application/json")
    public ResponseEntity<Resource> downloadHistory() {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=weather-history.json")
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
