package com.repositories;

import com.dto.UserLogin;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface UserInfoRepository extends CrudRepository<UserLogin,String> {

    @Query("SELECT * FROM userLogin WHERE username = ?0 AND deleted = false ALLOW FILTERING")
    Optional<UserLogin> findById(String username);

}
