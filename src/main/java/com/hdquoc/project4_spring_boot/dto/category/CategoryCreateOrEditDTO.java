package com.hdquoc.project4_spring_boot.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class CategoryCreateOrEditDTO {

    private int id;

    @NotBlank(message = "Category name must not be blank")
    private String name;

    @NotNull(message = "Icon must not be null")
    private MultipartFile icon;

    private LocalDateTime created_at;
}
