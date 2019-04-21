package com.projectmanager.projectmanagerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanagerservice.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
