package com.juanlfr.eboutique.ressources;

import com.juanlfr.eboutique.services.CountryApiHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/externalApi/")
@CrossOrigin("http://localhost:4200")
public class BoutiqueResource {

    @Autowired
    CountryApiHttpClient client;

    @GetMapping("/countries")
    public List<String> getCountries(){
        List<String> countriesNames = new ArrayList<>();
        client.getCountriesList().forEach(country -> countriesNames.add(country.getName().getCommon()));
        return  countriesNames;
    }

}
