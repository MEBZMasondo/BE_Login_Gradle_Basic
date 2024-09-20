package com.example.belogin.loginOne.service;


import com.example.belogin.loginOne.util.dto.LoginDTO;
import com.example.belogin.loginOne.util.dto.UserDTO;
import com.example.belogin.loginOne.util.entity.User;
import com.example.belogin.loginOne.util.response.LoginResponse;

import java.util.List;

public interface UserService {

    String addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
    List<UserDTO> listAll();

}
