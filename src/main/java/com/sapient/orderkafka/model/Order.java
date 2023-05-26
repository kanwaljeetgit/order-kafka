package com.sapient.orderkafka.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String remarks;

    private Double amount;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "OrderMapping", joinColumns= @JoinColumn(name="orderId", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="orderDetailsId", referencedColumnName="id"))
    private List<OrderDetails> orderDetailsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<OrderDetails> getOrderDetailsList() {
        return orderDetailsList;
    }

    public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
        this.orderDetailsList = orderDetailsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(remarks, order.remarks) && Objects.equals(amount, order.amount) && Objects.equals(orderDetailsList, order.orderDetailsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, remarks, amount, orderDetailsList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", remarks='" + remarks + '\'' +
                ", amount=" + amount +
                ", orderDetailsList=" + orderDetailsList +
                '}';
    }

}
