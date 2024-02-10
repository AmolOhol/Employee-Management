package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import com.employee.entity.Employee;
import com.employee.entity.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);
}
