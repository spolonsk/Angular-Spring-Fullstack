package com.spolonsk.daily.repository;

import com.spolonsk.daily.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
  List<Employee> findByEmailId(String emailId);
}
