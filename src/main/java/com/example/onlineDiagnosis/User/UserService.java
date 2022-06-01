package com.example.onlineDiagnosis.User;

import com.example.onlineDiagnosis.User.emun.GENDER;
import com.example.onlineDiagnosis.User.exceptions.EmailAlreadyBeingUsedUserException;
import com.example.onlineDiagnosis.User.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserPresenter presenter;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("user with email %s not found", email));
        }
        return user;
    }

    public void signUpUser(User user) throws EmailAlreadyBeingUsedUserException {
        boolean userExists = userRepository.findByEmail(user.getEmail()) != null;

        if (userExists) {
            throw new EmailAlreadyBeingUsedUserException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void updateUser(User userToUpdate) throws UserNotFoundException {
        // Search for user
        User existingUser = userRepository.findById(userToUpdate.getId()).orElse(null);
        if(existingUser == null){
            throw new UserNotFoundException("User was not found while trying to update user");
        }

        existingUser.setEmail(userToUpdate.getEmail());
        existingUser.setFirstName(userToUpdate.getFirstName());
        existingUser.setLastName(userToUpdate.getLastName());
        existingUser.setPhoneNumber(userToUpdate.getPhoneNumber());
        existingUser.setPhoneValidated(userToUpdate.isPhoneValidated());
        userRepository.save(existingUser);
    }


    public User loadUserFromJwt(){
        Object loggedInUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(loggedInUserEmail == null || loggedInUserEmail.equals("")){
            return null;
        }
        return this.loadUserByUsername((String)loggedInUserEmail);
    }

    public HashMap<String, Object> present(User user){
        return presenter.present(user);
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("Invalid user id");
        }
        return user.get();
    }

    public String genderToStringMan(GENDER g){
        return switch (g) {
            case male -> "man";
            case female -> "woman";
            default -> "man";
        };
    }
    public String genderToStringMale(GENDER g){
        return switch (g) {
            case male -> "male";
            case female -> "female";
            default -> "male";
        };
    }

}
