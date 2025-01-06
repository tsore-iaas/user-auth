package com.tsore.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tsore.user.Services.VirtualMachineTypeService;
import com.tsore.user.dto.VirtualMachineTypeDTO;
import com.tsore.user.models.VirtualMachineType;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/vmType")
@AllArgsConstructor
public class VirtualMachineTypecontroller {
  private VirtualMachineTypeService virtualMachineTypeService;

  @PostMapping
  public ResponseEntity<VirtualMachineType> createVMType(@RequestBody VirtualMachineTypeDTO virtualMachineTypeDTO) {
    VirtualMachineType vmType = this.virtualMachineTypeService.create(virtualMachineTypeDTO);

    return new ResponseEntity<>(vmType, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<VirtualMachineType> updateVMType(@PathVariable int id,
      @RequestBody VirtualMachineTypeDTO virtualMachineTypeDTO) {
    VirtualMachineType vmType = this.virtualMachineTypeService.update(virtualMachineTypeDTO, id);
    return new ResponseEntity<>(vmType, HttpStatus.OK);
  }

}
