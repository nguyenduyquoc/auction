package com.hdquoc.project4_spring_boot.service.category;

import com.hdquoc.project4_spring_boot.configuration.DateTimeUtils;
import com.hdquoc.project4_spring_boot.dao.ICategoryRepository;
import com.hdquoc.project4_spring_boot.dto.category.CategoryCreateOrEditDTO;
import com.hdquoc.project4_spring_boot.dto.category.CategoryDTO;
import com.hdquoc.project4_spring_boot.entity.Category;
import com.hdquoc.project4_spring_boot.service.CloudinaryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories= categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
            categoryDTO.setCreated_at(DateTimeUtils.formatLocalDateTime(category.getCreated_at()));
            categoryDTO.setUpdated_at(DateTimeUtils.formatLocalDateTime(category.getUpdated_at()));
            return categoryDTO;
        }).collect(Collectors.toList());
    }


    @Override
    public Optional<CategoryDTO> getCategoryById(Integer id) {
        return categoryRepository.findById(id).map(
                category -> modelMapper.map(category, CategoryDTO.class)
        );
    }

    @Override
    public Optional<CategoryCreateOrEditDTO> getCategoryCreatOrEditById(Integer id) {
        return categoryRepository.findById(id).map(
                category -> modelMapper.map(category, CategoryCreateOrEditDTO.class)
        );
    }
    @Override
    public void createCategory(CategoryCreateOrEditDTO categoryCreateDTO) {
        String iconUrl = cloudinaryService.uploadImage(categoryCreateDTO.getIcon());
        Category category = modelMapper.map(categoryCreateDTO, Category.class);
        category.setIcon(iconUrl);
        category.setCreated_at(LocalDateTime.now());
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Integer id, CategoryCreateOrEditDTO category) {
        Optional<Category> existedCategory = categoryRepository.findById(id);
        if(existedCategory.isPresent()){
            Category updateCategory = existedCategory.get();
            updateCategory.setName(category.getName());
            /*updateCategory.setIcon(category.getIcon());*/
            updateCategory.setUpdated_at(LocalDateTime.now());

            categoryRepository.save(updateCategory);
        } else {
            Category newCategory = modelMapper.map(category, Category.class);
            newCategory.setId(id);
            newCategory.setCreated_at(LocalDateTime.now());
            categoryRepository.save(newCategory);
        }
    }


    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
