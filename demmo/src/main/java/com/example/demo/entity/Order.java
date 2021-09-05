package com.example.demo.entity;

import com.example.demo.entity.ennum.OrderPriority;
import com.example.demo.entity.ennum.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order implements Comparable<Order>{

    @SequenceGenerator(name = "ORDER_ID", sequenceName = "ORDER_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID")
    @Id
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    Product product;

    @ManyToOne
    Customer client;

    @Column(name = "ORDER_QUANTITE")
    private int quantite;

    @Column(name = "ORDER_STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "ORDER_PRIORITY")
    @Enumerated(EnumType.STRING)
    private OrderPriority priority;

    @Column(name = "ORDER_CREATED_DATE")
    private LocalDateTime created = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getClient() {
        return client;
    }

    public void setClient(Customer client) {
        this.client = client;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderPriority getPriority() {
        return priority;
    }

    public void setPriority(OrderPriority priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public int compareTo(Order o) {
        return this.getCreated().compareTo(o.getCreated());
    }

    @Override
    public boolean equals(Object o) {
        Order order = (Order) o;
        if (order.getId().longValue()==this.getId().longValue()){
            return true;
        }
        return false;
    }
}
