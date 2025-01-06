package com.tsore.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VirtualMachineTypeDTO {
  private int vcpuNumber;
  private int ram;
  private int storageSpace;
}
