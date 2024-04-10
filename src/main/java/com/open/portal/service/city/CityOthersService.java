package com.open.portal.service.city;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.portal.domain.city.CityOthers;
import com.open.portal.repository.CityOthersRepository;

@Service
public class CityOthersService {
    @Autowired
    private CityOthersRepository cityOthersRepository;

    public CityOthers create(CityOthers cityOthers) {
        CityOthers cityAlreadyCreated = cityOthersRepository.findByName(cityOthers.getName()).orElse(null);

        if (Objects.nonNull(cityAlreadyCreated)) {
            return cityAlreadyCreated;
        }

        return cityOthersRepository.save(cityOthers);
    }
}