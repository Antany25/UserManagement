package com.example.usermanagement;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	@Test
	void testRegisterUser() {
		// Test registering a user
		User user = new User();
		user.setEmail("antanytom99@gmail.com");
		user.setPassword("password"); // Raw password before encoding

		System.out.println("Mail:"+user.getEmail()+" Password:"+user.getPassword());
		// Mock repository behavior
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
		when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword"); // Mock encoded password
		when(userRepository.save(any(User.class))).thenReturn(user);

		// Call the service method
		User savedUser = userService.registerUser(user);
		System.out.println("Mail:"+user.getEmail()+" Password:"+user.getPassword());

		// Assertions
		assertNotNull(savedUser);
		assertEquals("antanytom99@gmail.com", savedUser.getEmail());
		assertEquals("encodedPassword", savedUser.getPassword()); // Ensuring that the password is encoded
	}

	@Test
	void testValidateUser() {
		// Test validating user credentials
		User user = new User();
		user.setEmail("test@example.com");
		user.setPassword("encodedPassword"); // The encoded password from the database

		// Mock repository and password encoder behavior
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true); // Simulate correct password match

		// Call the service method
		assertTrue(userService.validateUser("test@example.com", "password"));

		// Testing for invalid password
		when(passwordEncoder.matches("wrongpassword", "encodedPassword")).thenReturn(false);
		assertFalse(userService.validateUser("test@example.com", "wrongpassword"));
	}
}
