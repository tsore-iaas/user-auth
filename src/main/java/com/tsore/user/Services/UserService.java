package com.tsore.user.Services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tsore.user.enums.TypeRole;
import com.tsore.user.models.Role;
import com.tsore.user.models.User;
import com.tsore.user.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class UserService implements UserDetailsService {
  private UserRepository userRepository;
  private BCryptPasswordEncoder passwordEncoder;

  public void save(User user) {
    Optional<User> optUser = this.userRepository.findByUsername(user.getUsername());
    if (optUser.isPresent()) {
      throw new RuntimeException("Your  email already used");
    }

    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    Role role = new Role();
    role.setLibelle(TypeRole.USER);
    user.setRole(role);

    user = this.userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("Any user for this entry"));
  }

  // Ajouter dans UserService.java
  public User loadUserByName(String username) {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

}
