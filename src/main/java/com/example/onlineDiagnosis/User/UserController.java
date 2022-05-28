package com.example.onlineDiagnosis.User;

import com.example.onlineDiagnosis.SharedClasses.ResponseHandler;
import com.example.onlineDiagnosis.User.exceptions.EmailAlreadyBeingUsedUserException;
import com.example.onlineDiagnosis.User.exceptions.UserNotFoundException;
import com.example.onlineDiagnosis.User.requests.RegistrationRequest;
import com.example.onlineDiagnosis.User.requests.UserUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/")
public class UserController extends ResponseHandler {

    private final UserService userService;

    @PostMapping("user/is-loged-in")
    public ResponseEntity<?> isLoggedIn() {
        // Search for user
        User loggedInUser = userService.loadUserFromJwt();
        if (loggedInUser == null || !loggedInUser.isPhoneValidated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body((new HashMap<>()).put("error", "You are not loged in"));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }

    @GetMapping("user")
    public ResponseEntity<?> getUserDetails(
            @RequestParam(required = false) String email
    ) {
        // Search for user
        User loggedInUser = userService.loadUserFromJwt();
        if (loggedInUser == null) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "You are not loged in");
        }
        if (!loggedInUser.isPhoneValidated()) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "You haven't validated your phone number");
        }

        // Return details of loged in user
        if (email == null || email.equals("")) {
            return createSuccessResponse(HttpStatus.ACCEPTED, userService.present(loggedInUser));
        }

        // Search user
        User userFromSearch;
        try {
            userFromSearch = userService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            return createErrorResponse("User not found");
        }
        return createSuccessResponse(HttpStatus.ACCEPTED, userService.present(userFromSearch));
    }

    @PostMapping("user")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) {

        // Check for empty fields
        if (
            request.getEmail() == null || request.getEmail().equals("") ||
            request.getPassword() == null || request.getPassword().equals("") ||
            request.getFirstName() == null || request.getFirstName().equals("") ||
            request.getLastName() == null || request.getLastName().equals("") ||
            request.getGender() == null || request.getGender().equals("") ||
            request.getYear() == null || request.getYear().equals("")
        ) {
            return createErrorResponse("Please fill in all the nesessary fields");
        }

        // Create new object
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setPhoneNumber(null);
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setRole(UserRole.USER);
        newUser.setPhoneValidated(false);
        newUser.setGender(request.getGender());
        newUser.setYear(request.getYear());

        // Try to sign in
        try {
            userService.signUpUser(newUser);
        } catch (EmailAlreadyBeingUsedUserException e) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body((new HashMap<>()).put("error", "E-mail already exists"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.present(newUser));
    }

    @CrossOrigin
    @PutMapping(value = "user")
    public ResponseEntity<?> updateUser(
        @RequestBody UserUpdateRequest request
    ) {
        // Get user from token
        User loggedInUser = userService.loadUserFromJwt();
        if (loggedInUser == null) {
            return createErrorResponse(HttpStatus.FORBIDDEN, "You are not loged in" );
        }

        loggedInUser.setFirstName(request.getFirstName());
        loggedInUser.setLastName(request.getLastName());
        loggedInUser.setPhoneNumber(request.getPhoneNumber());
        try {
            userService.updateUser(loggedInUser);
        } catch (UserNotFoundException e) {
            return createErrorResponse("Could not update user");
        }

        return createSuccessResponse(userService.present(loggedInUser));
    }

}

