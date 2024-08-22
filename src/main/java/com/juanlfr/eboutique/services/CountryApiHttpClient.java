package com.juanlfr.eboutique.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanlfr.eboutique.entities.CountryDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class CountryApiHttpClient {

    public List<CountryDTO> getCountriesList(){

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://restcountries.com/v3.1/all?fields=name"))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                        .build().send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, CountryDTO.class));

        } catch (IOException | URISyntaxException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
