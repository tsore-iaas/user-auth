package com.tsore.user.models;

import java.time.Instant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "validation")
public class Validation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private Instant creation;
  private Instant expire;
  private Instant activation;

  private String code;
  @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE })
  private User utilisateur;
}
