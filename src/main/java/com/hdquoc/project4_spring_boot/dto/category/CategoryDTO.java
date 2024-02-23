package com.hdquoc.project4_spring_boot.dto.category;

import lombok.Data;

@Data
public class CategoryDTO {

    private int id;

    private String name;

    private String icon;

    private String created_at;

    private String updated_at;

    private boolean is_deleted;
}
