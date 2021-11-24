package com.odds_and_ends.backendv1.entity.product_waste;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "product_waste") @Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Builder
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "waste_type")
    private String wasteType;

    @Column(name = "product_barcode")
    private String barcode;
}
