package com.tsore.user.securite;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tsore.user.models.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  private JwtService jwtService;

  private UserDetailsService userDetailsService;

  public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = null;
    String username = null;
    // Jwt tokenDansLaBD = null;

    String authorization = request.getHeader("Authorization");

    if (authorization != null && authorization.startsWith("Bearer ")) {
      token = authorization.substring(7);
      // tokenDansLaBD = this.jwtService.tokenByValue(token);
      username = jwtService.extractUsername(token);
    }
    if (username != null
        // && tokenDansLaBD.getUtilisateur().getEmail().equals(username)
        && SecurityContextHolder.getContext().getAuthentication() == null) {
      User utilisateur = (User) userDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(utilisateur,
          token, utilisateur.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    filterChain.doFilter(request, response);
  }

}
