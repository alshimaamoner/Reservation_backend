package com.org.service;

import java.math.BigInteger;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.dao.UserDao;
import com.org.exceptions.RecordAlreadyPresentException;
import com.org.exceptions.RecordNotFoundException;
import com.org.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<?> createUser(User newUser) {
        // TODO Auto-generated method stub
        try {
            userDao.save(newUser);
            return new ResponseEntity<User>(newUser, HttpStatus.OK);
        } catch (RecordAlreadyPresentException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User updateUser(User updateUser) {
        // TODO Auto-generated method stub
        Optional<User> findUserById = userDao.findById(updateUser.getId());
        if (findUserById.isPresent()) {
            userDao.save(updateUser);
        } else
            throw new RecordNotFoundException(
                    "User with Id: " + updateUser.getId() + " not exists!!");
        return updateUser;
    }

    @Override
    public String deleteUser(Long UserId) {
        // TODO Auto-generated method stub
        Optional<User> findBookingById = userDao.findById(UserId);
        if (findBookingById.isPresent()) {
            userDao.deleteById(UserId);
            return "User Deleted!!";
        } else
            throw new RecordNotFoundException("User not found for the entered UserID");
    }

    @Override
    public Iterable<User> displayAllUser() {
        //System.out.println("display");
        // TODO Auto-generated method stub
        List<User> users = (List<User>) userDao.findAll();
        System.out.println(users.size());
        return userDao.findAll();
    }

    @Override
    public ResponseEntity<?> findUserById(Long userId) {
        // TODO Auto-generated method stub
        Optional<User> findById = userDao.findById(userId);
        try {
            if (findById.isPresent()) {
                User findUser = findById.get();
                return new ResponseEntity<User>(findUser, HttpStatus.OK);
            } else
                throw new RecordNotFoundException("No record found with ID " + userId);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<User> findByUsername(String username, String password) {
        System.out.println("Find");
        return userDao.findByName(username, password);
    }

}
