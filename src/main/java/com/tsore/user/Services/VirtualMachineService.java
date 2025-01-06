package com.tsore.user.Services;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.tsore.user.dto.VirtualMachineDTO;
import com.tsore.user.enums.VirtualMachineState;
import com.tsore.user.models.User;
import com.tsore.user.models.VirtualMachine;
import com.tsore.user.models.VirtualMachineType;
import com.tsore.user.repositories.UserRepository;
import com.tsore.user.repositories.VirtualMachineRepository;
import com.tsore.user.repositories.VirtualMachineTypeRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class VirtualMachineService {

  private UserRepository userRepository;

  private VirtualMachineTypeRepository virtualMachineTypeRepository;

  private VirtualMachineRepository virtualMachineRepository;

  public VirtualMachine create(VirtualMachineDTO virtualMachineDTO) {
    VirtualMachine vm = new VirtualMachine();

    vm.setCreatedDate(new Date(0));
    vm.setIpAddress(virtualMachineDTO.getIpAddress());
    vm.setMacAddress(virtualMachineDTO.getMacAddress());
    vm.setNom(virtualMachineDTO.getNom());
    vm.setOperatingSystem(virtualMachineDTO.getOperatingSystem());
    vm.setState(VirtualMachineState.CREATED);
    User user = this.userRepository.findById(virtualMachineDTO.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));
    vm.setUser(user);

    VirtualMachineType virtualMachineType = this.virtualMachineTypeRepository
        .findById(virtualMachineDTO.getVirtualMachineTypeId())
        .orElseThrow(() -> new RuntimeException("Virtual Machine Type not found"));

    vm.setVirtualMachineType(virtualMachineType);

    this.virtualMachineRepository.save(vm);
    return vm;
  }

  public void delete(int id) {
    VirtualMachine vm = this.virtualMachineRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Virtual Machine not found"));
    this.virtualMachineRepository.delete(vm);
  }

}
