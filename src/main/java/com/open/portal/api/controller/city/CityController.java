package com.open.portal.api.controller.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.city.City;
import com.open.portal.service.city.CityService;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.ResponseEntity.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> read() {
        List<City> Citys = cityService.read();

        return ok(Citys);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<City>> readAll() {
        List<City> Citys = cityService.readAll();

        return ok(Citys);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<Void> active(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        cityService.active(id, token);

        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        cityService.delete(id, token);

        return noContent().build();
    }
}