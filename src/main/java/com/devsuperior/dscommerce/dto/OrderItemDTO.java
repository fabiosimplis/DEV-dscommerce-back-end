package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.OrderItem;

public class OrderItemDTO {
    private Long productID;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDTO(Long productID, String nome, Double price, Integer quantity) {
        this.productID = productID;
        this.name = nome;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemDTO(OrderItem entity) {
        productID = entity.getProduct().getId();
        name = entity.getProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
    }

    public Long getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubTotal(){
        return quantity * price;
    }
}