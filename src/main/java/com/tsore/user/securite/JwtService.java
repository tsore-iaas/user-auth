package com.tsore.user.securite;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.tsore.user.Services.UserService;
import com.tsore.user.models.User;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class JwtService {

  private UserService utilisateurService;

  private final String ENCRYPTION_KEY = "CmiiogvX6ILtIiRajQuYdYqh/Kh1Nou9BBMPVi/xgQslDTb3vJA2MPRgCM8/eGJr\n";

  public Map<String, String> generate(String username) {
    User utilisateur = (User) this.utilisateurService.loadUserByUsername(username);
    Map<String, String> jwt = this.generateJwt(utilisateur);

    // this.jwtRepository.save(jwtObject);

    return jwt;
  }

  public Map<String, String> generateJwt(User utilisateur) {
    Long currentTime = System.currentTimeMillis();
    Long expireTime = System.currentTimeMillis() + 30 * 60 * 1000;
    Map<String, Object> claims = Map.of(
        "nom", utilisateur.getNom(),
        Claims.EXPIRATION, new Date(expireTime),
        Claims.SUBJECT, utilisateur.getUsername());

    final String bearer = Jwts.builder()
        .setIssuedAt(new Date(currentTime))
        .setExpiration(new Date(expireTime))
        .setSubject(utilisateur.getUsername())
        .setClaims(claims)
        .signWith(this.getKey(), SignatureAlgorithm.HS256)
        .compact();
    return Map.of("Bearer", bearer);
  }

  private Key getKey() {
    final byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
    return Keys.hmacShaKeyFor(decoder);
  }

  public String extractUsername(String token) {
    return this.getAllClaims(token, Claims::getSubject);
  }

  public boolean isTokenExpired(String token) {
    Date expirationDate = this.getAllClaims(token, Claims::getExpiration);
    return expirationDate.before(new Date());
  }

  private <T> T getAllClaims(String token, Function<Claims, T> function) {
    Claims claims = this.getClaims(token);
    return function.apply(claims);
  }

  private Claims getClaims(String token) {
    return Jwts.parser()
        .setSigningKey(this.getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

}
