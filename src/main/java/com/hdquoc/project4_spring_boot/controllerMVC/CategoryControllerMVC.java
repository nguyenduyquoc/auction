package com.hdquoc.project4_spring_boot.controllerMVC;

import com.hdquoc.project4_spring_boot.dto.category.CategoryCreateOrEditDTO;
import com.hdquoc.project4_spring_boot.dto.category.CategoryDTO;
import com.hdquoc.project4_spring_boot.service.category.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/v1/categories")
public class CategoryControllerMVC {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryControllerMVC(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }


    // GET ALL CATEGORIES
    @GetMapping("")
    public String getCategories(Model model) {
        Iterable<CategoryDTO> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/index";
    }
    //GET FORM ADD NEW CATEGORY
    @GetMapping("/add")
    public String showFormAddCategory(Model model) {
        model.addAttribute("newCategory", new CategoryCreateOrEditDTO());
        return "category/add";
    }

    // SAVE NEW CATEGORY
    @PostMapping("/add")
    public String saveCategory(@Valid @ModelAttribute("newCategory") CategoryCreateOrEditDTO newCategory, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "category/add";
        } else {
            categoryService.createCategory(newCategory);
            return "redirect:/v1/categories";
        }
    }


    // GET FORM EDIT CATEGORY
    @GetMapping("/edit")
    public String showFormEditCategory(@RequestParam("categoryId") Integer id, Model model) {
        Optional<CategoryCreateOrEditDTO> categoryEdit = categoryService.getCategoryCreatOrEditById(id);
        categoryEdit.ifPresent(value -> model.addAttribute("updateCategory", value));
        return "category/update";
    }

    // UPDATE CATEGORY
    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute("updateCategory") CategoryCreateOrEditDTO updateCategory, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "category/update";
        } else {
            categoryService.updateCategory(updateCategory.getId(), updateCategory);
            return "redirect:/v1/categories";
        }
    }



    // DELETE CATEGORY
    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("categoryId") Integer id){
        categoryService.deleteCategory(id);
        return "redirect:/v1/categories";
    }


}
