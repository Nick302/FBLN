package com.example.freelancefbln.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Email
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    //для отчета еще доступ личного, надо подумать и роля
    @OneToMany(mappedBy = "user")
    private List<Report> reportList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;
}
