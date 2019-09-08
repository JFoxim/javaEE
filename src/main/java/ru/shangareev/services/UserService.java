package ru.shangareev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shangareev.entities.User;
import ru.shangareev.repositories.UserRepository;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@Stateful(name = "userService")
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UserRepository userRepository;

    public UserService(){
    }

    public void merge(User user){
        userRepository.merge(user);
    }

    public User findByLogin(String login){
        User user = null;
        try{
            user = userRepository.findByLogin(login);
        }
        catch (Exception err){
            logger.info(err.getMessage());
        }
        return user;
    }

    public User findById(int id) {
        User user = null;
        try{
             user = userRepository.findById(id);
        }
        catch (Exception err){
            logger.info(err.getMessage());
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try{
            userList = userRepository.getAllUsers();
        }
        catch (Exception err){
            logger.info(err.getMessage());
        }
        return userList;
    }

    public void delete(User user){
        userRepository.delete(user);
    }
}
