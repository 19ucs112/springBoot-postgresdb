package com.Concise.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Concise.assessment.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

}
