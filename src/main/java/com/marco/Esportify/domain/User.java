package com.marco.Esportify.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "\"user\"")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String twitter;
    private String instagram;
    private String country;
    private Role role;

    @ManyToMany(mappedBy = "members")
    private List<Organization> organizations;
}