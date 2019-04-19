package com.projectmanager.projectmanagerservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanager.projectmanagerservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
