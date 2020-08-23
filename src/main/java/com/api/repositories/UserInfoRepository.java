package com.api.repositories;

import com.api.dto.LoginResponse;
import com.api.dto.UserLogin;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CassandraRepository<UserLogin,String> {

    @Query("SELECT * from userlogin where username = ?0 ALLOW FILTERING")
    LoginResponse findOneById(String username);

}
