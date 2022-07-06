package com.Concise.assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Concise.assessment.exception.ResourceNotFoundException;
import com.Concise.assessment.models.Employee;
import com.Concise.assessment.repository.EmployeeRepo;

@RestController
@RequestMapping("/concise-assessment/api/v1/")
public class EmployeeController 
{
	@Autowired
	private EmployeeRepo e;
	@PostMapping("/save")
	public Employee saveEmployee(@RequestBody Employee em)
	{
		return e.save(em);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Employee>> getEmployees()
	{
		List<Employee> em = e.findAll();
		return ResponseEntity.ok(em);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> FindEmployeeById(@PathVariable Integer id)
	{
		Employee em = e.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
		return ResponseEntity.ok(em);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> UpdateEmployeeById(@PathVariable Integer id,@RequestBody Employee emp)
	{
		Employee em = e.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not Found"));
		if(emp.getFirst_name()!=null)
		{
			em.setFirst_name(emp.getFirst_name());
		}
		if(emp.getLast_name()!=null)
		{
			em.setLast_name(emp.getLast_name());
		}
		if(emp.getGender()!=null)
		{
			em.setGender(emp.getGender());
		}
		if(emp.getAddress()!=null)
		{
			em.setAddress(emp.getAddress());
		}
		if(emp.getSalary()!=null)
		{
			em.setSalary(emp.getSalary());
		}
		e.save(em);
		return ResponseEntity.ok(em);
	}
	@DeleteMapping("/delete/{id}")
	public String DeleteEmployeeById(@PathVariable Integer id)
	{
		Employee em = e.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not Found"));
		e.deleteById(id);
		return "Employee with id"+id+" Deleted Successfully";
	}
}


