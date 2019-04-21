package com.projectmanager.projectmanagerservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanagerservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
