package kg.aiu.acmecorp.entity;


import jakarta.persistence.*;
import kg.aiu.acmecorp.enums.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity(name = "product")
@Table(name = Product.TABLE_NAME)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends BaseEntity {

    public static final String TABLE_NAME = "products";
    public static final String SEQUENCE_NAME = TABLE_NAME + "_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @Column(name = "id")
    Long id;

    @Column(name = "name",nullable = false)
    String name;

    @Column(name = "price",nullable = false,columnDefinition = "NUMERIC(10,2)")
    BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "category",nullable = false)
    Category category;

    @Column(name = "description",nullable = false)
    String description;

    @Override
    public Long getId() {
        return id;
    }
}
