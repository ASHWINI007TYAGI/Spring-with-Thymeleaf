package com.example.demo.repository;

import com.example.demo.entity.post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<post,Long> {
}