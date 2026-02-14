package com.cement.app.inventory.service;

import com.cement.app.inventory.dto.CreateProductRequest;
import com.cement.app.inventory.entity.Product;
import com.cement.app.inventory.repository.ProductRepository;
import com.cement.app.common.util.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    
    private Long getCurrentTenant() {
        Long tenantId = TenantContext.getTenant();
        return (tenantId != null) ? tenantId : 1L;
    }

    public Product createProduct(CreateProductRequest request) {

        Long enterpriseId = TenantContext.getTenant();

        if (productRepository.existsByEnterpriseIdAndProductNameIgnoreCase(
                enterpriseId, request.getProductName())) {
            throw new RuntimeException("Product already exists");
        }

        Product product = new Product();
        product.setEnterpriseId(enterpriseId);
        product.setProductName(request.getProductName());
        product.setUnit(request.getUnit());
        
     // 🔥 Set enterprise ID (VERY IMPORTANT)
        Long tenantId = getCurrentTenant();

        product.setEnterpriseId(tenantId);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findByEnterpriseIdAndStatus(getCurrentTenant(), "ACTIVE");
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus("INACTIVE");
        productRepository.save(product);
    }
}
