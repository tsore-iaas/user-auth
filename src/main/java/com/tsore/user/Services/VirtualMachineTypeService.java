package com.tsore.user.Services;

import org.springframework.stereotype.Service;

import com.tsore.user.dto.VirtualMachineTypeDTO;
import com.tsore.user.models.VirtualMachineType;
import com.tsore.user.repositories.VirtualMachineTypeRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class VirtualMachineTypeService {
  private VirtualMachineTypeRepository virtualMachineTypeRepository;

  public VirtualMachineType create(VirtualMachineTypeDTO virtualMachineTypeDTO) {
    VirtualMachineType virtualMachineType = new VirtualMachineType();
    virtualMachineType.setVcpuNumber(virtualMachineTypeDTO.getVcpuNumber());
    virtualMachineType.setRam(virtualMachineTypeDTO.getRam());
    virtualMachineType.setStorageSpace(virtualMachineTypeDTO.getStorageSpace());
    this.virtualMachineTypeRepository.save(virtualMachineType);
    return virtualMachineType;
  }

  public VirtualMachineType update(VirtualMachineTypeDTO virtualMachineTypeDTO, int id) {
    VirtualMachineType virtualMachineType = this.virtualMachineTypeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Virtual Machine Type not found"));

    virtualMachineType.setVcpuNumber(virtualMachineTypeDTO.getVcpuNumber());
    virtualMachineType.setRam(virtualMachineTypeDTO.getRam());
    virtualMachineType.setStorageSpace(virtualMachineTypeDTO.getStorageSpace());

    this.virtualMachineTypeRepository.save(virtualMachineType);
    return virtualMachineType;
  }
}
