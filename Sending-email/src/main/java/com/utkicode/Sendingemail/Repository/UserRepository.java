package com.utkicode.Sendingemail.Repository;

import com.utkicode.Sendingemail.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

}
