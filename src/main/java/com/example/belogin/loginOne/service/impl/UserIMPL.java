package com.example.belogin.loginOne.service.impl;

import com.example.belogin.loginOne.repo.UserRepo;
import com.example.belogin.loginOne.service.UserService;
import com.example.belogin.loginOne.util.dto.LoginDTO;
import com.example.belogin.loginOne.util.dto.UserDTO;
import com.example.belogin.loginOne.util.entity.User;
import com.example.belogin.loginOne.util.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getUsername(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepo.save(user);
        return user.getName();
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        //String message = "";
        User u = userRepo.findByUsername(loginDTO.getUsername());
        /*
        if(u.getName() == "" || u.getPassword() == "")
            return new LoginResponse("Empty", false);
         */
        if(u != null) {
            String password = loginDTO.getPassword();

            // TODO : encoded password
            //String encodedPassword= u.getPassword();
            //Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            // TODO : remove this and add encoded password
            String encodedPassword= u.getPassword();
            boolean isPwdRight = password.equals(encodedPassword);

            if(isPwdRight) {
                Optional<User> user = userRepo.findOneByUsernameAndPassword(loginDTO.getUsername() ,encodedPassword);
                if(user.isPresent()) {
                    return new LoginResponse("Login Successful", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Wrong Credentials", false);
            }
        } else {
            return new LoginResponse("User does not exist", false);
        }
    }

    @Override
    public List<UserDTO> listAll() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getUsername());
            userDTOS.add(userDTO);
        }

//        List<UserDTO> userDTOS = users.stream()
//                .map(user -> new UserDTO(user.getId(), user.getName(), user.getUsername()))
//                .collect(Collectors.toList());

        return userDTOS;
        //return List.of();
    }

}
