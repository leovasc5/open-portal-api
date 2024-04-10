package com.open.portal.service.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.portal.api.exception.http.ForbiddenException;
import com.open.portal.api.exception.http.NotFoundException;
import com.open.portal.domain.city.City;
import com.open.portal.domain.user.Permission;
import com.open.portal.domain.user.User;
import com.open.portal.repository.CityRepository;
import com.open.portal.repository.UserRepository;
import com.open.portal.service.auth.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public List<City> read() {
        List<City> cities = cityRepository.findAllByIsActiveTrue();

        if (cities.isEmpty()) {
            throw new NotFoundException("No cities actives.");
        }

        return cities;
    }

    public List<City> readAll() {
        List<City> cities = cityRepository.findAll();

        if (cities.isEmpty()) {
            throw new NotFoundException("No cities found.");
        }

        return cities;
    }

    public void active(Integer id, String token) {
        log.info("Starting city activation");

        City city = cityRepository.findById(id).orElseThrow(() -> new NotFoundException("City not found."));

        String username = jwtService.extractUserName(token.substring(7));
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getPermission() != Permission.ADMIN) {
            log.error("User does not have permission", ForbiddenException.class);
            throw new ForbiddenException("User does not have permission");
        }

        city.setActive(true);
        cityRepository.save(city);
    }

    public void delete(Integer id, String token) {
        log.info("Starting city deletion");

        City city = cityRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new NotFoundException("City not found."));

        String username = jwtService.extractUserName(token.substring(7));
        User user = userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));

        if (user.getPermission() != Permission.ADMIN) {
            log.error("User does not have permission", ForbiddenException.class);
            throw new ForbiddenException("User does not have permission");
        }

        city.setActive(false);
        cityRepository.save(city);
    }
}
