package com.appsdeveloperblog.estore.ProductService.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;


import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductEntity implements Serializable {
    /*
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(unique = true)
    @EqualsAndHashCode.Include
    private String productId;
    @Column(unique=true)
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
