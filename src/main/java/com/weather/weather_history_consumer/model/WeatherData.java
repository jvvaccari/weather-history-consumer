package com.weather.weather_history_consumer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @JsonProperty("temperature2m")
    private double temperature;

    @JsonProperty("apparentTemperature")
    private double apparentTemperature;

    @JsonProperty("relativeHumidity2m")
    private int relativeHumidity;

    @JsonProperty("precipitationProbability")
    private int probabilityOfPrecipitation;

    @JsonProperty("precipitation")
    private double precipitation;

    @JsonProperty("rain")
    private double rain;

    @JsonProperty("snowfall")
    private double snowfall;

    @JsonProperty("pressureMsl")
    private double seaLevelPressure;

    @JsonProperty("cloudCover")
    private int cloudCover;

    @JsonProperty("windSpeed10m")
    private double windSpeed;

    @JsonProperty("windGusts")
    private double windGusts;

    @JsonProperty("visibility")
    private double visibility;

    @JsonProperty("time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime registeredAt;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;


    // getters e setters

    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public double getApparentTemperature() { return apparentTemperature; }
    public void setApparentTemperature(double apparentTemperature) { this.apparentTemperature = apparentTemperature; }

    public int getRelativeHumidity() { return relativeHumidity; }
    public void setRelativeHumidity(int relativeHumidity) { this.relativeHumidity = relativeHumidity; }

    public int getProbabilityOfPrecipitation() { return probabilityOfPrecipitation; }
    public void setProbabilityOfPrecipitation(int probabilityOfPrecipitation) { this.probabilityOfPrecipitation = probabilityOfPrecipitation; }

    public double getPrecipitation() { return precipitation; }
    public void setPrecipitation(double precipitation) { this.precipitation = precipitation; }

    public double getRain() { return rain; }
    public void setRain(double rain) { this.rain = rain; }

    public double getSnowfall() { return snowfall; }
    public void setSnowfall(double snowfall) { this.snowfall = snowfall; }

    public double getSeaLevelPressure() { return seaLevelPressure; }
    public void setSeaLevelPressure(double seaLevelPressure) { this.seaLevelPressure = seaLevelPressure; }

    public int getCloudCover() { return cloudCover; }
    public void setCloudCover(int cloudCover) { this.cloudCover = cloudCover; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public double getWindGusts() { return windGusts; }
    public void setWindGusts(double windGusts) { this.windGusts = windGusts; }

    public double getVisibility() { return visibility; }
    public void setVisibility(double visibility) { this.visibility = visibility; }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
