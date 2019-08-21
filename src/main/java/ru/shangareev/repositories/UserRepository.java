package ru.shangareev.repositories;


import lombok.NoArgsConstructor;
import ru.shangareev.entities.User;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.List;

@Named
@NoArgsConstructor
@ApplicationScoped
public class UserRepository {

   @PersistenceContext(unitName = "ds")
   protected EntityManager entityManager;


   public void merge(User user){
        entityManager.merge(user);
   }

    public User findByLogin(String login) throws SQLException {
        User user = (User)entityManager.createQuery(
                "select user from User as user where user.login= ?1")
                .setParameter(1, login)
                .getSingleResult();
        return user;
    }

    public User findById(int id) throws SQLException {
        return entityManager.find(User.class, id);
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = entityManager.createQuery(
                "select user from User as user")
                .getResultList();
        return users;
    }

    public void delete(User user){
       entityManager.remove(user);
    }

}