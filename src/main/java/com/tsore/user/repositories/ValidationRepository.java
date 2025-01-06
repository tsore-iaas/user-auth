package com.tsore.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsore.user.models.Validation;
import java.util.Optional;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Integer> {
  Optional<Validation> findByCode(String code);
}
