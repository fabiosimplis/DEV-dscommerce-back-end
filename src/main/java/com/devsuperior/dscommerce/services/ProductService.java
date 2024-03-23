package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
    public Page<ProductDTO> findAll(Pageable pageable){
        Page<Product> products = repository.findAll(pageable);
        return products.map( p -> new ProductDTO(p));

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

        Product productEntity = repository.getReferenceById(id);
        copyDtoToEntity(dto, productEntity);

        productEntity = repository.save(productEntity);

        return new ProductDTO(productEntity);

    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
