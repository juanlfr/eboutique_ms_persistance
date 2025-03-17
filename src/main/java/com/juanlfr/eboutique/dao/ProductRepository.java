package com.juanlfr.eboutique.dao;

import com.juanlfr.eboutique.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    //exposes this endpoint => http://localhost:8080/api/products/search/findByCategoryId?id=<id>
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
    //exposes this endpoint => http://localhost:8080/api/products/search/findByNameContaining?name=<name>
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
    //exposes this endpoint => http://localhost:8080/api/products/search/findByUnitPriceLessThanEqual?price=<price>
    Page<Product> findByUnitPriceLessThanEqual(@Param("price") Double price, Pageable pageable);
}
