package com.example.onlineDiagnosis.User;

import com.example.onlineDiagnosis.SharedClasses.ModelPresenter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class UserPresenter implements ModelPresenter<User> {

    @Override
    public HashMap<String, Object> present(User user) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("id",user.getId());
        response.put("email",user.getEmail());
        response.put("phoneNumber",user.getPhoneNumber());
        response.put("firstName",user.getFirstName());
        response.put("lastName",user.getLastName());
        response.put("phoneValidated",user.isPhoneValidated());
        return response;
    }

    @Override
    public List<HashMap<String, Object>> presentMultiple(List<User> userList) {
        List<HashMap<String, Object>> userListToReturn = new ArrayList<>();
        for (User user : userList) {
            userListToReturn.add(present(user));
        }
        return userListToReturn;
    }

}


