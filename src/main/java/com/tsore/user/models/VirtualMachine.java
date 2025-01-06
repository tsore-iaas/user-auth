package com.tsore.user.models;

import java.sql.Date;

import com.tsore.user.enums.OperatingSystem;
import com.tsore.user.enums.VirtualMachineState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VirtualMachine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nom;
  @Enumerated(EnumType.STRING)
  private OperatingSystem operatingSystem;
  private String ipAddress;
  private String macAddress;
  private Date createdDate;
  @Enumerated(EnumType.STRING)
  private VirtualMachineState state;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne
  @JoinColumn(name = "type")
  private VirtualMachineType virtualMachineType;
}
