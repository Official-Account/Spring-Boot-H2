package com.h2.dao;

import com.h2.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDao  extends JpaRepository<Employee, Integer> {

    List<Employee> findByDepartment(String dept);

}
