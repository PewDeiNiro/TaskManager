package com.pewde.taskmanager.service;

import com.pewde.taskmanager.entity.User;
import com.pewde.taskmanager.exception.*;
import com.pewde.taskmanager.mapper.AuthRequestMapper;
import com.pewde.taskmanager.repository.UserRepository;
import com.pewde.taskmanager.request.auth.AuthRequest;
import com.pewde.taskmanager.request.auth.TokenRequest;
import com.pewde.taskmanager.response.AuthResponse;
import com.pewde.taskmanager.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRequestMapper authRequestMapper;

    public AuthResponse signUp(AuthRequest request) {
        if (request.getUsername().equalsIgnoreCase("root")){
            throw new RootUserNotAllowedException();
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyTakenException();
        }
        User user = authRequestMapper.mapAuthRequestToUser(request);
        userRepository.saveAndFlush(user);
        Token token = Token.generateToken(user.getId());
        return new AuthResponse(user.getId(), token.toString());
    }

    public AuthResponse signIn(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserDoesNotExistException::new);
        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidPasswordException();
        }
        Token token = Token.generateToken(user.getId());
        return new AuthResponse(user.getId(), token.toString());
    }

    public AuthResponse extendToken(TokenRequest request, String token){
        token = Token.getTokenFromAuthorization(token);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserDoesNotExistException::new);
        if (!Token.checkAuthentication(user.getId(), token)){
            throw new InvalidTokenException();
        }
        Token.extendToken(user.getId(), token);
        return new AuthResponse(user.getId(), token);
    }

    public ResponseEntity<String> signOut(TokenRequest request, String token){
        token = Token.getTokenFromAuthorization(token);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserDoesNotExistException::new);
        if (!Token.checkAuthentication(user.getId(), token)){
            throw new InvalidTokenException();
        }
        Token.expireToken(user.getId(), token);
        return new ResponseEntity<>("Выход успешно выполнен", HttpStatus.OK);
    }

}
