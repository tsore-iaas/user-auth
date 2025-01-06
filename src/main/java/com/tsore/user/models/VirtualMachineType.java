package com.tsore.user.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VirtualMachineType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int vcpuNumber;
  private int ram;
  private int storageSpace;

  @OneToMany(mappedBy = "virtualMachineType", cascade = CascadeType.ALL)
  private List<VirtualMachine> virtualMachine;
}
