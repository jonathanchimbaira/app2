package com.student.sec;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDao extends CrudRepository<DAOUser, String> {
    DAOUser findByUserid(String username);
}