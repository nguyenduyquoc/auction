package com.hdquoc.project4_spring_boot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "First Name must not be blank")
    @Size(min = 2, message = "First Name must be at least 2 characters")
    private String first_name;

    @NotBlank(message = "Last Name must not be blank")
    @Size(min = 2, message = "Last Name must be at least 2 characters")
    private String last_name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Phone Number must not be blank")
    private String phone_number;

    private String avatar;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String address;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products;

}
