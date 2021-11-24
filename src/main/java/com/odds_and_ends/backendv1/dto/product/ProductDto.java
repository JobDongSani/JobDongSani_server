package com.odds_and_ends.backendv1.dto.product;

import com.odds_and_ends.backendv1.entity.product_waste.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class ProductDto {

    private String barcode;
    private String wasteType;

    public Product toEntity(){
        return Product.builder()
                .barcode(this.barcode)
                .waste_type(this.wasteType)
                .build();
    }

    public static ProductDto of(Product product){
        return new ProductDto(product.getBarcode(), product.getWaste_type());
    }
}
