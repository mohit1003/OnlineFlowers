package com.online.flowers.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.flowers.model.CustomerModel;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.model.ShopModel;
import com.online.flowers.repo.CustomerRepo;
import com.online.flowers.repo.FlowersRepo;
import com.online.flowers.repo.ShopRepo;
import com.online.flowers.service.FlowersService;
import com.online.flowers.util.AuthRequest;
import com.online.flowers.util.JwtUtil;

@RestController
@RequestMapping(path = "/flowers")
@CrossOrigin(origins = "*")
public class NoAuthController {

	@Autowired
	private CustomerRepo _custRepo;

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private FlowersService flowersService;

	@Autowired
	private FlowersRepo _repo;

	@Autowired
	private ShopRepo _shoprepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	int registerFlag = 0;

	int loginFlag = 0;

	@PostMapping(value = "/register")
	public ResponseEntity<String> registerUser(@RequestBody CustomerModel customer) {
		Optional.ofNullable(customer).ifPresent(customerToSave -> {
			if (_custRepo.existsById(customer.getEmail())) {
				this.registerFlag = 2;
			} else {
				customerToSave.setPassword(passwordEncoder.encode(customerToSave.getPassword()));
				_custRepo.save(customerToSave);
				this.registerFlag = 1;
			}
		});

		if (registerFlag == 1) {
			return new ResponseEntity<String>("User is registered", HttpStatus.CREATED);
		} else {
			registerFlag = 0;
			return new ResponseEntity<String>("User not registered/ already exists", HttpStatus.CONFLICT);
		}

	}

	@PostMapping(value = "/login")
	public ResponseEntity<String> userLogin(@RequestBody CustomerModel customer) {

		Optional.ofNullable(customer).ifPresent(customerToSave -> {
			if (_custRepo.existsById(customer.getEmail())) {
				this.loginFlag = 1;
			}
		});

		if (loginFlag == 1) {
			return new ResponseEntity<String>("user is logged in", HttpStatus.OK);
		}
		loginFlag = 0;
		return new ResponseEntity<String>("Invalid Username or Password", HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid username/password");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtutil.generateToken(authentication);
		if (token.isEmpty()) {
			return new ResponseEntity<String>("Error generating token", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllFlowers")
	public ResponseEntity<?> list() {
		List<FlowersModel> list = flowersService.list();
		if (list.isEmpty()) {
			return new ResponseEntity<String>("No flowers returned", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<List<FlowersModel>>(list, HttpStatus.OK);
	}

	public Optional<FlowersModel> getImage(Integer id) {
		return _repo.findById(id);
	}

	@GetMapping(value = "/getAllShops")
	public List<ShopModel> getShop() {
		return _shoprepo.findAll();
	}

}
