package com.org.dao;
import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.org.model.User;;

public interface UserDao extends CrudRepository<User, Long> {
    @Query("from User where name=?1 and password=?2")
    Optional<User> findByName(String name, String password);
}
