package com.greatlearning.customerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.customerManagement.model.Customer;
import com.greatlearning.customerManagement.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomers(Model model) {
		
		List<Customer> customers= customerService.findAll();
		
		model.addAttribute("Customers", customers);
		
		return "list_Customer";
		}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// create model attribute to bind form data
		Customer customer = new Customer();

		model.addAttribute("Customer", customer);

		return "Customer-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

		// get the Student from the service
		Customer customer =customerService.findById(id);

		// set Student as a model attribute to Pre-populate the form
		model.addAttribute("Customer", customer);

		// send over to our form
		return "Customer-form";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println(id);
		Customer thecustomer;
		if (id != 0) {
			thecustomer = customerService.findById(id);
			thecustomer.setFirstName(firstName);
			thecustomer.setLastName(lastName);
			thecustomer.setEmail(email);
		} else
			thecustomer = new Customer(firstName,lastName, email);
		;
		// save the Student
		customerService.save(thecustomer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customers/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {

		// delete the Student
		customerService.deleteById(id);

		// redirect to /Students/list
		return "redirect:/customers/list";

	}
}
