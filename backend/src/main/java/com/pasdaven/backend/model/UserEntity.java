package com.pasdaven.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "rest-template")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public UserEntity() {
    }

    public UserEntity(Integer id, String name, String email, ProductEntity product) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}
