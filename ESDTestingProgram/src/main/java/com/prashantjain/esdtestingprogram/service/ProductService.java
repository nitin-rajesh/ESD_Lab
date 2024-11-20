package com.prashantjain.esdtestingprogram.service;

import com.prashantjain.esdtestingprogram.dto.ProductEntityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService extends JpaRepository<ProductEntityRequest,Long> {

    List<ProductEntityRequest> findByProductName(String productName);

    List<ProductEntityRequest> findByPriceGreaterThan(Double price);

    @Query("SELECT p FROM ProductEntity p where p.price >= 15 and p.price <= 30 order by price")
    List<ProductEntityRequest> retrievePriceBetweenRange();

    @Query("SELECT p FROM ProductEntity p WHERE p.productName = :productName")
    List<ProductEntityRequest> findProductsByName(@Param("productName") String productName);

    @Query("UPDATE ProductEntity p SET p.price = :price WHERE p.productId = :productId")
    void updatePriceById(@Param("productId") Long productId, @Param("price") Double price);

    @Query("DELETE FROM ProductEntity p WHERE p.productName = :productName")
    void deleteByProductName(@Param("productName") String productName);

}
