package com.open.portal.api.controller.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.city.CityOthers;
import com.open.portal.service.city.CityOthersService;
import static org.springframework.http.ResponseEntity.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/city/others")
@RequiredArgsConstructor
public class CityOthersController {
    @Autowired
    private CityOthersService cityOthersService;

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody CityOthers cityOthers) {
        CityOthers cityCreated = cityOthersService.create(cityOthers);

        return created(null).body(cityCreated.getId());
    }
}