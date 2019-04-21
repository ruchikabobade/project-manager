package com.projectmanager.projectmanagerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanagerservice.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
