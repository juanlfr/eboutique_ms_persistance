package com.juanlfr.eboutique.dao;

import com.juanlfr.eboutique.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

//Accept calls from web browser scripts (JS) for this origin (protocol, hostname, port)
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
    //va a exponer este endpoint => http://localhost:8080/api/products/search/findByCategoryId?id=<id>
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
    //va a exponer este endpoint => http://localhost:8080/api/products/search/findByNameContaining?name=<name>
    Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);
}
