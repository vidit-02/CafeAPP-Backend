package com.example.CafeAPP.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name="Product.getAllProducts", query = "select new com.example.CafeAPP.wrapper.ProductWrapper(p.id,p.name, p.description, p.price, p.status, p.category.id, p.category.name) from Product p")

@NamedQuery(name="Product.updateProductStatus", query = "update Product p set p.status=:status where p.id=:id")

@NamedQuery(name="Product.getProductByCategory", query = "select new com.example.CafeAPP.wrapper.ProductWrapper(p.id,p.name, p.description, p.price) from Product p where p.category.id=:id and p.status='true'" )

@NamedQuery(name = "Product.getProductById", query = "select new com.example.CafeAPP.wrapper.ProductWrapper(p.id,p.name, p.description, p.price, p.status, p.category.id, p.category.name) from Product p where p.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="product")
public class Product implements Serializable {

    public static final long serialVersionUid = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer Id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) //this means when we fetch all products it won't fetch all Category columns unless we specifically fetch a category
    //many products to one category
    @JoinColumn(name = "category_fk", nullable = false) //every product need to have a category
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;

}
