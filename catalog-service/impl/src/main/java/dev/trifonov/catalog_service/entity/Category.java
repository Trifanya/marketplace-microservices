package dev.trifonov.catalog_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @ManyToMany
    @JoinTable(name = "category_characteristic",
            joinColumns = @JoinColumn(name = "characteristic_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Feature> features;

    @ManyToMany
    @JoinTable(name = "category_relation",
            joinColumns = @JoinColumn(name = "child_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<Category> childCategories;

    @ManyToMany
    @JoinTable(name = "category_relation",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private List<Category> parentCategories;
}
