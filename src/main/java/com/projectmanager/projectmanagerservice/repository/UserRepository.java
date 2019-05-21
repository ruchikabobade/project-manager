package com.projectmanager.projectmanagerservice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanagerservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 public List<User> findAllByFirstName(@Param(value = "firstName") String firstName);
 public User findByProjectId(@Param(value = "projectId") Long projectId);
}
