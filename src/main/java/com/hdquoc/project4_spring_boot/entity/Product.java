package com.hdquoc.project4_spring_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First Name must not be blank")
    @Size(min = 2, message = "Product Name must be at least 2 characters")
    private String name;

    @Column(nullable = false)
    private String thumbnail;
    private String description;
    private String detail;

    @Column(nullable = false)
    private DecimalFormat startingPrice;

    @Column(nullable = false)
    private DecimalFormat reservePrice;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updated_at;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean is_deleted;
}
