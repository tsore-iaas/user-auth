package com.tsore.user.controllers;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsore.user.Services.UserService;
import com.tsore.user.dto.AuthentificationDTO;
import com.tsore.user.models.User;
import com.tsore.user.securite.JwtService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private UserService utilisateurService;

  private AuthenticationManager authenticationManager;

  private JwtService jwtService;

  @PostMapping("/signup")
  public void inscription(@RequestBody User utilisateur) {

    //log.info("inscription");

    this.utilisateurService.save(utilisateur);
  }

  @PostMapping("/signin")
  public Map<String, String> connexion(@RequestBody AuthentificationDTO authentificationDTO) {
    Map<String, String> jwt = null;
    final Authentication authentication = this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password()));

    if (authentication.isAuthenticated()) {
      jwt = this.jwtService.generate(authentificationDTO.username());
    }

    return jwt;
  }


  // Ajouter cette méthode dans la classe UserController
  @GetMapping(path = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> getUserInfo(@RequestHeader("Authorization") String token) {
    // Extraire le username du token JWT
    String username = this.jwtService.extractUsername(token.replace("Bearer ", ""));

    // Récupérer l'utilisateur par son username
    User user = this.utilisateurService.loadUserByName(username);

    // Retourner l'ID et le username
    return Map.of(
            "id", user.getId(),
            "username", user.getUsername()
    );
  }

}
