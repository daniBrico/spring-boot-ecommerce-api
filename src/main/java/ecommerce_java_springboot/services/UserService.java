package ecommerce_java_springboot.services;

import ecommerce_java_springboot.models.UserModel;
import ecommerce_java_springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel setUser(UserModel user) {
        return userRepository.save(user);
    }
}
