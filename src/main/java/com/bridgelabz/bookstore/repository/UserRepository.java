package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {

    @Query("SELECT u FROM UserData u WHERE u.email = :e")
    UserData getUserByEmail(@Param ("e") String email);

}
