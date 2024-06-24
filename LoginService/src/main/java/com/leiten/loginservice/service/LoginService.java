package com.leiten.loginservice.service;

import com.leiten.loginservice.requestDTO.LoginRequestDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {

     String login(LoginRequestDTO requestDTO, HttpServletRequest request);

}
