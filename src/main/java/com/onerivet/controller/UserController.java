package com.onerivet.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onerivet.entity.User;
import com.onerivet.repository.UserRepository;
import com.onerivet.security.SecurityConfiguration;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityConfiguration securityConfiguration;
	
	@GetMapping("/first")
	public String getFirst() {
		return "Welcome to 1Rivet";
	}
	
	@PostMapping("/new")
    public ResponseEntity<User> create(@RequestBody User product) {
		product.setPassword(new BCryptPasswordEncoder(10).encode(product.getPassword()));
        User savedProduct = userRepository.save(product);
        URI productURI = URI.create("/products/" + savedProduct.getId());
        
        //securityConfiguration.getPassword(product.getPassword());
       // new ResponseEntity<User>(savedProduct,HttpStatus.CREATED);
        
        System.out.println(productURI);
        return ResponseEntity.created(productURI).body(savedProduct);
    }
	
	
	@GetMapping("/get")
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	@GetMapping("/verify")
	public User getUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
/* Note:- 
 * In	 @EnableGlobleMethodSecurity(prePosttEnable=true)
 * 		@PreAuthorize("hasAuthority('ROLE_ADMIN')") on method
 * 		@PreAuthorize("hasRole('USER')")
 */
}
