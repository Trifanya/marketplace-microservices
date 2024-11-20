package dev.trifonov.catalog_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "characteristic")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @ManyToMany
    @JoinTable(name = "category_characteristic",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "product_characteristic",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id"))
    private List<Characteristic> characteristics;
}
