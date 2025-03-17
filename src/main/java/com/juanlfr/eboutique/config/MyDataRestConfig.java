package com.juanlfr.eboutique.config;

import com.juanlfr.eboutique.entities.Product;
import com.juanlfr.eboutique.entities.ProductCategory;

import jakarta.persistence.EntityManager;

import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private final EntityManager ENTITY_MANAGER;

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        ENTITY_MANAGER = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
        // disable HTTP methods for Product and ProductCategory: PUT, POST, DELETE and PATCH
        disableHttpMethods(Product.class, config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
        //To Expose the id from the entities
        exposeIds(config);
        //Config cors mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
    }
    private void disableHttpMethods(Class<?> theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        //Get a list of all entity clases
        Set<EntityType<?>> entities = ENTITY_MANAGER.getMetamodel().getEntities();
        //Create an empty array for the entities's classes
        List<Class<?>> entityClasses = new ArrayList<>();
        //Get the entity types
        for (EntityType<?> entityType : entities) {
            entityClasses.add(entityType.getJavaType());
        }
        //Expose entities id
        Class<?>[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
