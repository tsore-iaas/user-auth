package com.tsore.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsore.user.models.VirtualMachine;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Integer> {

}
