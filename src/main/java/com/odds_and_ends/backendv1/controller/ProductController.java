package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.dto.product.ProductDto;
import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    private CommonResponse<Long> createProduct(@RequestBody ProductDto productDto){
        return productService.saveProduct(productDto);
    }

    @GetMapping("/product")
    private CommonResponse<ProductDto> createProduct(@RequestParam String barcode){
        return productService.findProduct(barcode);
    }
}
