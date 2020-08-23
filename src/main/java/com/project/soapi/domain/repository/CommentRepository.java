package com.project.soapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.soapi.domain.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
