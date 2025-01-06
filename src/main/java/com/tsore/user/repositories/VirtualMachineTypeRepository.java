package com.tsore.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsore.user.models.VirtualMachineType;

public interface VirtualMachineTypeRepository extends JpaRepository<VirtualMachineType, Integer> {

}
