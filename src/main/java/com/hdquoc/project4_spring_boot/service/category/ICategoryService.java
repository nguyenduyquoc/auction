package com.hdquoc.project4_spring_boot.service.category;

import com.hdquoc.project4_spring_boot.dto.category.CategoryCreateOrEditDTO;
import com.hdquoc.project4_spring_boot.dto.category.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICategoryService {

    List<CategoryDTO> getAllCategories();

    Optional<CategoryDTO> getCategoryById(Integer id);

    Optional<CategoryCreateOrEditDTO> getCategoryCreatOrEditById(Integer id);

    void createCategory(CategoryCreateOrEditDTO categoryCreateDTO);

    void updateCategory(Integer id, CategoryCreateOrEditDTO category);

    void deleteCategory(Integer id);

}
