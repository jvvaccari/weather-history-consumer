package com.weather.weather_history_consumer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weather.weather_history_consumer.model.WeatherData;
import com.weather.weather_history_consumer.classes.WeatherInternalPublisher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Component
public class HistoryService {

    @Value("${history.file.path}")
    private String filePath;

    private final ObjectMapper objectMapper;
    private final WeatherInternalPublisher publisher;
    private File jsonFile;

    public HistoryService(WeatherInternalPublisher publisher) {
        this.publisher = publisher;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @PostConstruct
    public void init() {
        this.jsonFile = new File(filePath);
        this.jsonFile.getParentFile().mkdirs();
        publisher.registerListener(this::onDataReceived);
    }

    private void onDataReceived(String jsonData) {
        try {
            System.err.println("dados brutos: " + " \n" + jsonData);
            WeatherData[] dataArray = objectMapper.readValue(jsonData, WeatherData[].class);
            for (WeatherData weatherData : dataArray) {
                registerWeather(weatherData);
            }
        } catch (IOException e) {
            System.err.println("Erro ao converter JSON: " + e.getMessage());
        }
    }

    private synchronized void registerWeather(WeatherData weatherData) {
        try {
            weatherData.setRegisteredAt(LocalDateTime.now());
            List<WeatherData> existingData = readExistingData();
            existingData.add(weatherData);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, existingData);

            System.out.println("Novo dado armazenado em " + jsonFile.getPath());

        } catch (IOException e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }

    private List<WeatherData> readExistingData() {
        try {
            if (jsonFile.exists() && jsonFile.length() > 0) {
                WeatherData[] array = objectMapper.readValue(jsonFile, WeatherData[].class);
                return new ArrayList<>(List.of(array));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler histórico: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
