package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.CategoryDTO;
import com.devsuperior.dscommerce.dto.ProductConsultDTO;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Category;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DataBaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductConsultDTO> findAll(String name, Pageable pageable){
        Page<Product> products = repository.searchByName(name, pageable);
        return products.map( p -> new ProductConsultDTO(p));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product productEntity = new Product();
        copyDtoToEntity(dto, productEntity);

        productEntity = repository.save(productEntity);

        return new ProductDTO(productEntity);

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){

        try{
            Product productEntity = repository.getReferenceById(id);
            copyDtoToEntity(dto, productEntity);
            productEntity = repository.save(productEntity);
            return new ProductDTO(productEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()){
            Category cat = new Category();
            cat.setId(catDto.getId());
            entity.getCategories().add(cat);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Falha de integridade");
        }
    }
}
