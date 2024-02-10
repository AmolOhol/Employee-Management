package com.employee.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.UserRepository;
import com.employee.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/") 
	public String redirectToLogin() {
		return "redirect:/login1";
	}
	
	@GetMapping("/login1") 
	public String loginPage() { 
		return "login1";
	}
	
	@GetMapping("/viewhome")
	public String viewIndexpage(Model m, Authentication authentication) {
		boolean isAdmin = false;
		
		if(authentication!=null) {
		for (GrantedAuthority authority : authentication.getAuthorities()) { 
			if (authority.getAuthority().equals("ADMIN")){ 
				isAdmin= true; 
				break;
			}
		}
	}
		
		m.addAttribute("isAdmin", isAdmin);
		
		return showEmployeeList(m);
	}
	
	@GetMapping("/user-role")
	public String userProfile(Model model, Principal principal) {

			String username = principal.getName(); // Get the currently logged-in user's 
			//String username =userService.getUsername(username); 
			
			String userRole =userService.getUserRoleByUsername (username); // Fetch user role from Db

			// Store user role in the session

			httpSession.setAttribute("userRole", userRole);
			httpSession.setAttribute("username", username);
			
			return "user_role";
	}
	
	@GetMapping("/employee")
	public String showEmployeeList(Model model) {
		List<Employee> employeeList = employeeRepo.findAll();
		model.addAttribute("employee", employeeList);
		return "home";
	}

	@GetMapping("/add1")
	public String addEmployeeForm (Model model) {

		model.addAttribute("employee", new Employee());
		return "add";
	}
			

	@PostMapping("/add1")
	public String addEmployee (@ModelAttribute Employee employee) {
		
		employeeRepo.save(employee);
		return "redirect:/viewhome";
	}
	
	@GetMapping("/edit/{employeeid}")
	public String editEmployeeForm(@PathVariable Long employeeid, Model model) {
		Employee employee =employeeRepo.findById(employeeid).orElse(null);
		model.addAttribute("employee", employee); 
		return "edit"; // Thymeleaf template name

	}
	
	@PostMapping("/edit/{employeeid}")
	public String editEmployee(@PathVariable Long employeeid, @ModelAttribute Employee updatedEmployee) { 
		Employee employee = employeeRepo.findById(employeeid).orElse(null);
	
		if (employee != null) {

			employee.setEmployeeName(updatedEmployee.getEmployeeName());
			employee.setGender(updatedEmployee.getGender());
			employee.setMobile_no(updatedEmployee.getMobile_no()) ;
			employee.setEmail (updatedEmployee.getEmail());
			employee.setSalary (updatedEmployee.getSalary());
			
			employeeRepo.save(employee);
			
		}
		return "redirect:/viewhome"; // Redirect to employee list page))
	}
	
	@GetMapping("/delete/{employeeid}")
	public String deleteProducts(@PathVariable(value="employeeid") long employeeid) {

			employeeRepo.deleteById(employeeid);

			return "redirect:/viewhome";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login1?logout";
	}


}
