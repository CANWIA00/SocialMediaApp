package com.canwia.askquestion.service;

import com.canwia.askquestion.model.User;
import com.canwia.askquestion.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAllUsers() {
      return   userRepository.findAll();
    }

    public User createUser(User user) {
      return   userRepository.save(user);
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    public User updateUserById(Long userId, User user) {
        Optional<User> user1 = userRepository.findById(userId);
        if(user1.isPresent()){
            User userNew = user1.get();
            userNew.setUserName(user.getUserName());
            userNew.setPassword(user.getPassword());
            userRepository.save(userNew);
            return  userNew;
        }else {
            return null;
        }
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
