package com.thr.repository;

import com.thr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>{

    //根据用户名查询用户
    @Query(value = "select * from t_user where user_name=?1",nativeQuery = true)
    public User findByUserName(String userName);

    //根据用户ID删除用户
    @Modifying
    @Query(value = "delete from t_user where id=?1",nativeQuery = true)
    public void deleteById(Integer id);
}
