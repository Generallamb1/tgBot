package ru.Max.tgBot.Entities;

import jakarta.persistence.*;

@Entity
public class OrderProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private ClientOrder clientOrder;

    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private Long countProduct;

    public Long getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(Long countProduct) {
        this.countProduct = countProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ClientOrder getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
