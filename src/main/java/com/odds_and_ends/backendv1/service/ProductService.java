package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.dto.product.ProductDto;
import com.odds_and_ends.backendv1.entity.product_waste.Product;
import com.odds_and_ends.backendv1.entity.product_waste.ProductRepository;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public CommonResponse<Long> saveProduct(ProductDto productDto){
        Product savedProduct = productRepository.save(productDto.toEntity());
        return new CommonResponse<>(201, "성공하셨습니다.", savedProduct.getId());
    }
}
