package com.tsore.user.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tsore.user.Services.VirtualMachineService;
import com.tsore.user.dto.VirtualMachineDTO;
import com.tsore.user.models.VirtualMachine;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/vm")
@AllArgsConstructor
public class VirtualMachineController {

  private VirtualMachineService virtualMachineService;

  @PostMapping
  public ResponseEntity<VirtualMachine> createVM(@RequestBody VirtualMachineDTO virtualMachineDTO) {
    VirtualMachine vm = this.virtualMachineService.create(virtualMachineDTO);
    return new ResponseEntity<>(vm, HttpStatus.CREATED);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatusCode> deleteVM(@PathVariable int id) {
    this.virtualMachineService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
