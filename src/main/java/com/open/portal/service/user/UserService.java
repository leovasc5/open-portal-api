package com.open.portal.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.open.portal.api.exception.http.ConflictException;
import com.open.portal.api.exception.http.NoContentException;
import com.open.portal.domain.user.User;
import com.open.portal.domain.user.data.UserAdminViewDto;
import com.open.portal.mapper.UserMapper;
import com.open.portal.repository.UserRepository;
import com.open.portal.service.auth.JwtService;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserAdminViewDto> readByAdmin() {
        List<UserAdminViewDto> users = userRepository.findAllByDeletedByIsNull().stream().map(UserMapper::toUserAdminViewDto).toList();
        users.stream().findFirst().orElseThrow(() -> new NoContentException("There are no users registered."));
        
        return users;
    }

    public UserAdminViewDto readById(Integer id) {
        UserAdminViewDto user = userRepository.findByIdAndDeletedByIsNull(id).
            map(UserMapper::toUserAdminViewDto).
                orElseThrow(() -> new NoContentException("User not found."));
        
        return user;
    }

    public User readDomainById(Integer id) {
        return userRepository.findByIdAndDeletedByIsNull(id).
            orElseThrow(() -> new NoContentException("User not found.")); 
    }

    public UserAdminViewDto update(Integer id, User userModified) {
        User user = userRepository.findByIdAndDeletedByIsNull(id).orElseThrow(() -> new NoContentException("User not found."));

        user.setName(userModified.getName());

        if (userModified.getPassword() == null || userModified.getPassword().isEmpty() || userModified.getPassword().equals(user.getPassword())) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(userModified.getPassword()));
        }

        if (userModified.getPhoneNumber() == null || userModified.getPhoneNumber().isEmpty() || userModified.getPhoneNumber().equals(user.getPhoneNumber())) {
            user.setPhoneNumber(user.getPhoneNumber());
        } else {
            Boolean phoneNumberExists = userRepository.findByPhoneNumber(userModified.getPhoneNumber()).isPresent();

            if (phoneNumberExists) {
                throw new ConflictException("Phone is already registered.");
            }
            
            user.setPhoneNumber(userModified.getPhoneNumber());
        }

        user.setIsReceiver(userModified.getIsReceiver());
        userRepository.save(user);

        return UserMapper.toUserAdminViewDto(user);
    }

    public Void delete(Integer id, String token) {
        User user = userRepository.findByIdAndDeletedByIsNull(id).orElseThrow(() -> new NoContentException("User not found."));
        String username = jwtService.extractUserName(token.substring(7));
        User deletedBy = userRepository.findByEmail(username).orElseThrow(() -> new NoContentException("Admin not found."));
        
        user.setDeletedBy(deletedBy);
        userRepository.save(user);

        return null;
    }
}
