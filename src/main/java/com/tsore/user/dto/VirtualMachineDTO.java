package com.tsore.user.dto;

import java.sql.Date;

import com.tsore.user.enums.OperatingSystem;
import com.tsore.user.enums.VirtualMachineState;
import com.tsore.user.models.VirtualMachineType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VirtualMachineDTO {

  private String nom;
  private OperatingSystem operatingSystem;
  private String ipAddress;
  private String macAddress;
  private int userId;
  private int virtualMachineTypeId;
}
