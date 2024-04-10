package com.open.portal.api.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.open.portal.domain.user.User;
import com.open.portal.domain.user.data.UserAdminViewDto;
import com.open.portal.service.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserAdminViewDto>> readByAdmin() {
        List<UserAdminViewDto> users = userService.readByAdmin();
        
        return ok(users);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<UserAdminViewDto> readById(@PathVariable Integer idUser) {
        UserAdminViewDto user = userService.readById(idUser);
        
        return ok(user);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<UserAdminViewDto> update(@PathVariable Integer idUser, @RequestBody User user) {
        UserAdminViewDto userUpdated = userService.update(idUser, user);
        
        return ok(userUpdated);
    }
    
    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable Integer idUser, @RequestHeader("Authorization") String token) {
        userService.delete(idUser, token);
        
        return noContent().build();
    }
}
