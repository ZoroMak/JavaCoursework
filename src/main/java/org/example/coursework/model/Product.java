package org.example.coursework.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = "javaschema", name = "product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dataArt")
    private int dataArt;
    private String name;
    private int cost;
    private String image;
    private String link;
    @Column(name = "productcol")
    private int productCol;
}
