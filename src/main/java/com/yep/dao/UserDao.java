package com.yep.dao;

import com.yep.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserInfo, Long> {

    UserInfo findByNameAndPassword(String name, String pwd);

    UserInfo findByEmail(String email);

    UserInfo findByEmailAndPassword(String email, String pwd);


}
