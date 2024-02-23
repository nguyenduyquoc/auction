package com.hdquoc.project4_spring_boot.dao;

import com.hdquoc.project4_spring_boot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
