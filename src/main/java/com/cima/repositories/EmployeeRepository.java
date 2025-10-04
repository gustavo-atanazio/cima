package com.cima.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cima.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}